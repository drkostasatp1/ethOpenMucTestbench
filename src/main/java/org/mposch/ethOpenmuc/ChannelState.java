package org.mposch.ethOpenmuc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.GasCounter;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.mposch.ethOpenmuc.gui.tableModels.TableModelChannelState;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Channel;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.simpleChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.stereotype.Controller;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.Keys;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * This Class will represent the State of OpenMuc Channels (like a simple
 * cache). Persiodic updates can be merged into the state. The state provides a
 * means of deciding if a RecordEntry is new and needs to be updated and
 * transmitted to the Blockchain This can easily be decided by comparing the
 * Record Timesatmps, or if the record key is not present
 * 
 * @author mposch
 */
@Controller
public class ChannelState extends Observable {
	@Autowired
	private TableModelChannelState		tableModelChannelState;
	@Autowired
	private ContractBean				contractBean;
	@Autowired
	private GuiController				gui;
	@Autowired
	private GasCounter					gasCounter;
	private HashMap<String, Channel>	data;
	// Store indexes to priovide a consistend ordering..
	private ArrayList<String>			indexes;

	/**
	 * 
	 */
	public ChannelState() {
		this.data = new HashMap<String, Channel>();
		this.indexes = new ArrayList();
	}

	private boolean needsUpdate(Channel value) {
		String key = getKey(value);
		// System.out.println("Check update requirements for:" + key);
		Channel stored = data.get(key);

		if (stored == null) return true;
		if ((stored.getRecord().getTimestamp()) < (value.getRecord().getTimestamp())) return true;
		return false;
	}

	public Channel getChannelAtIndex(int index) {
		return this.data.get(indexes.get(index));
	}

	public void clear() {
		this.data.clear();
		this.indexes.clear();
	}

	/**
	 * Updates a value in the channel State. If a channel is already present,
	 * only timestamp and value are updated! This method is synchronized.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized int put(Channel value) {
		String key = getKey(value);
		// Only if the string key is not present, add a new index.
		if (this.data.containsKey(key))
		{

			// THE VALUE IS ALREADY PRESENT, UPDATE THE CHANNEL STATE
			Channel update;
			// Update Timestamp and Value
			update = this.data.get(key);
			// Set the Value, if the timestamp of the new value is higher
			// (newer)
			// if (value.getRecord().getTimestamp() >
			// update.getRecord().getTimestamp())
			{
				// indicate a new value is ready for transaction
				update.setBlockchainStatus(value.getBlockchainStatus());
				update.getRecord().setValue(value.getRecord().getValue());
				// Set the Timestamp
				update.getRecord().setTimestamp(value.getRecord().getTimestamp());
				// Leave the other fields alone!

				// UPDATE THE TABLE
				int firstRow = indexes.indexOf(key);
				int lastRow = firstRow;
				// tableModelChannelState.fireTableRowsUpdated(firstRow,
				// lastRow);
			}
		}
		else
		{
			// INSERT A NEW VALUE
			// Add a totally new Value, with a new index so the Hashmap is
			// iterable.
			this.indexes.add(key);
			this.data.put(key, value);
		}
		return indexes.indexOf(key);
	}

	private String getKey(Channel value) {
		String key = value.getSource() + "." + value.getId();
		return key;
	}

	public synchronized void postAllOnBlockChain() {

		Set channels = this.data.entrySet();
		for (Channel c : this.data.values())
		{
			if ((c.isSyncToBlockchain() == true) && (c.getBlockchainStatus().equals("READY")))
				postChannelOnBlockchain(c);
			// Update the channel limits as reported by the smart contract
			try
			{
				Int256 tmp256;
				Double tmpDouble = new Double (c.getRecord().getValue());
				long tmpLong = Math.round(tmpDouble.doubleValue());
				
				tmp256 = new Int256(tmpLong);
				Int256 retVal;
				retVal = contractBean.getContract().getBoundedValue(tmp256).get();
				c.setLimitedValue(retVal);
			}
			catch (Exception e)
			{
				gui.Error("Error during limiting: " + e.getMessage());
			}

		}
	}

	/**
	 * THis will post a value onto the blockchain, if it is ready to broadcast
	 * and not in a pending state.
	 * 
	 * @param c
	 */
	public void postChannelOnBlockchain(Channel c) {

		CompletableFuture.supplyAsync(() -> {
			try
			{
				System.out.println("ChannelState.postChannelOnBlockchain(): " + c.getId() + "Channel State"
						+ c.getBlockchainStatus());
				c.setBlockchainStatus("PENDING");
				tableModelChannelState.fireTableDataChanged();
				Address adr = generateAddress(c);
				String data = c.toJsonString();
				TransactionReceipt tr;
				tr = contractBean.mergeStringFast(adr, data).get();

				c.setBlockchainStatus("TRANSACTION COMPLETE");
				c.setFailedTransacitons(0);
				c.accumulateGasSpent(tr.getGasUsed());

				tableModelChannelState.fireTableDataChanged();
			}
			catch (InterruptedException | ExecutionException | IOException e)
			{

				gui.Error("Error During Transaction: " + e.getMessage());
				// Reset the Blockchain state, try to re transmit:
				int failedTransactions = c.getFailedTransacitons();
				// Retry the transaction for some times
				if (failedTransactions <= Config.TRANSACTION_RETRY)
				{
					c.setBlockchainStatus("READY");
					c.setFailedTransacitons(failedTransactions + 1);
				}
				else c.setBlockchainStatus("FAILED");

				tableModelChannelState.fireTableDataChanged();
				return false;
			}
			return true;
		}

		); // Completable future

	}

	// This could be a starting point to fire the table update, but i want
	// to update the table only if blockchain events occur
	//

	/**
	 * This method will take a channel and generate an apropirate storage
	 * address for it, based on the Channel id.
	 * 
	 * @param c
	 * @return
	 */
	public Address generateAddress(Channel c) {
		MessageDigest md = null;
		byte[] digest = null;
		byte[] truncatedDigest = new byte[Config.ADDRESSLENGTH];
		BigInteger finalHash = null;

		try
		{
			md = MessageDigest.getInstance("SHA-256");
			String text = c.getId();
			md.update(text.getBytes("UTF-8")); // Trigger hash calculation
			digest = md.digest();
			for (int i = 0; i < Config.ADDRESSLENGTH; i++)
				truncatedDigest[i] = digest[i];
			finalHash = new BigInteger(truncatedDigest);
			Address adr = new Address(finalHash);
			return adr;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			gui.Error("Hash Calculation failed" + e.getMessage());
			String hashString = Integer.toHexString(c.getId().hashCode());
			Address adr = new Address(hashString);
			return adr;
		}

	}

	public ArrayList<String> getAllId() {
		ArrayList<String> retVal = new ArrayList();
		for (int i = 0; i < this.getSize(); i++)
		{
			retVal.add(this.getChannelAtIndex(i).getId());
		}
		return retVal;
	}

	public Channel get(String key) {
		return this.data.get(key);
	}

	public int getSize() {
		return this.data.size();
	}

	public boolean contains(String key) {
		return this.data.containsKey(key);
	}

}
