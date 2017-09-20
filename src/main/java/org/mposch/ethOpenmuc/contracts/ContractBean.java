package org.mposch.ethOpenmuc.contracts;

import java.awt.Color;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.mposch.ethOpenmuc.gui.tableModels.ContractTableModelRaw;
import org.mposch.solidityContractWrapper.RecordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * This class provides a wrapper around the RecordValues wrapper, which wraps
 * the actual solidity contract. It provides some extra features and is
 * controlled by the Spring framwwork
 * 
 * @author mposch
 *
 */

@Component
public class ContractBean {
	private RecordStorage				contract;

	@Autowired
	private BlockChainProviderList		blockChainProviderList;

	@Autowired
	private GuiController				gui;

	@Autowired
	private MainWindow					mainWindow;
	@Autowired
	private ContractTableModelRaw		contractTableModelRaw;

	private HashMap<Address, String>	simpleCache;
	private ArrayList<Address>			indexCache;

	/**
	 * Create the smart contract in the constructor /*
	 * 
	 */

	public ContractBean() {
		this.simpleCache = new HashMap<>();
		this.indexCache = new ArrayList<>();
	}

	public void loadContract(String address) {
		try
		{
			contract = RecordStorage.load(address, blockChainProviderList.getWeb3j(),
					blockChainProviderList.getCredentials(), RecordStorage.GAS_PRICE, RecordStorage.GAS_LIMIT);

			// An Observable emmits events
			// Therfore

			// contractObservable =
			// contract.logNewStringEventObservable(DefaultBlockParameter.valueOf("earliest"),
			// DefaultBlockParameter.valueOf("latest"));
			// will emit events
			// contractObservable.subscribe(contractTableModelRaw);
			if (this.isValid())
			{
				gui.Success("SUCCESS: Loaded Contract Version: " + contract.version().get().toString());

			}
			// Finally place the Contract address in the Adress text field

			Utf8String utf8String = contract.version().get();
			String t = utf8String.toString();
			if (gui.queryIsOwner() == true)
			{
				t = t + " OWNER";
			}
			// Check if i am the owner
			mainWindow.getTxtContractValidity().setText(t);
			mainWindow.getTxtContractValidity().setBackground(Color.GREEN);
			// Query Owner:

			// Kick of the Table models to initialize the Table
			// First fill up the cache by querying all elements of the Contract
			this.rebuildCache();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			this.gui.Error("Not possible to load and validate Contract: " + e.getMessage());
			this.contract = null;

		}

	}

	public void loadContract() {

		CompletableFuture.supplyAsync(() -> {
			this.loadContract(mainWindow.getTxtStorageLocation().getText());
			return true;
		});

	}

	/**
	 * Return a smart contract wrapper. Usually, this method will be called by
	 * the Spring framework.
	 * 
	 * @return
	 */
	public RecordStorage getContract() {
		try
		{
			if (this.isValid() == false)
			{
				// System.out.println("CONTRACT INVALID!");
				this.loadContract();
			}
		}
		catch (Exception e)
		{
			gui.Error("Could not create or load contract: " + e.getMessage());
			return null;
		}
		// Return a valid Contract wrapper:
		return contract;
	}

	public String deployNewContract() {
		try
		{

			Credentials ce = blockChainProviderList.getCredentials();

			int selection = gui.getBcProviderSelection();

			contract = RecordStorage.deploy(blockChainProviderList.getWeb3j(), ce, contract.GAS_PRICE,
					contract.GAS_LIMIT, BigInteger.ZERO).get();

			if (this.isValid())
			{
				gui.Success("DEPLOYED NEW CONTRACT AT: " + contract.getContractAddress());
				this.mainWindow.getTxtStorageLocation().setText(contract.getContractAddress());
				this.loadContract();
				return contract.getContractAddress();
			}
			else
			{
				throw new Exception("Contract is not Valid.");
			}

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			gui.Error("ERROR during contract creation: " + e.getMessage());
			return null;
		}

		// TODO Store new contract address.

	}

	/**
	 * @return
	 * @throws IOException
	 */
	public boolean isValid() {
		if (this.contract == null) return false;
		else try
		{
			return this.contract.isValid();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			return false;
		}
	}

	/**
	 * @param adr
	 * @return
	 */
	public boolean contains(Address adr) {
		Bool answer;
		try
		{
			answer = contract.contains(adr).get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			return false;
		}
		// Return is the Contract contains a specific Address
		return answer.getValue();
	}

	/**
	 * This will check is an address is taken, and than decide if a String shall
	 * be updated ot inserted Important: The method will block until the
	 * Transaction is confirmed.
	 * 
	 * @param adr
	 * @param str
	 * @return The transaction receipt, containing the hash of the transaction
	 *         for example
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public TransactionReceipt mergeStringBlocking(Address adr, String str)
			throws InterruptedException, ExecutionException {

		if (contains(adr) == true)
		{
			return contract.updateString(adr, new Utf8String(str)).get();
		}
		else return contract.setString(adr, new Utf8String(str)).get();
	}

	/**
	 * This method will initiate a merge transaction, without waiting for the
	 * confirmation.
	 * 
	 * @param adr
	 * @param str
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	// Tell the Spring framework that this method will run in an extra Thread
	@Async
	public CompletableFuture<TransactionReceipt> mergeStringFast(Address adr, String str)
			throws InterruptedException, ExecutionException {
		TransactionReceipt transactionReceipt;
		// The following methods will block long:
		if (contains(adr) == true)
		{
			transactionReceipt = contract.updateString(adr, new Utf8String(str)).get();
		}
		else transactionReceipt = contract.setString(adr, new Utf8String(str)).get();
		return CompletableFuture.completedFuture(transactionReceipt);
	}

	/**
	 * This method will "delete" all items in the Contract. They will be still
	 * present in the blockchain, but simply forgotten.
	 * 
	 * @return Number of deleted items
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Async
	public void removeAll() {

		//  CompletableFuture.supplyAsync(() -> {
			int i = 0;
			Address adr;
			try
			{

				while (contract.getCount().get().getValue().intValue() != 0)
				{
					i++;
					adr = contract.getStringAtIndex(new Uint256(0)).get();
					System.out.println("Deleting (" + i + "):" + adr.toString() + ", " + contract.getString(adr).get());
					contract.deleteString(adr).get();
				}
				gui.Success("Deleted Items:" + i);
			}
			catch (Exception e)
			{
				gui.Error("Error while deleting: " + e.getMessage());
			}
	//		return i;
	//	});

	}

	/**
	 * This method will simply query all items stored in the Smart contract and
	 * put them into a simple cache.
	 */
	private void rebuildCache() {
		// Iterate over all Contract items and add them to the temporary cache.
		int max;
		this.simpleCache.clear();
		this.indexCache.clear();
		try
		{
			max = contract.getCount().get().getValue().intValue();
			Address adr;
			String value = null;
			for (int i = 0; i < max; i++)
			{
				adr = contract.getStringAtIndex(new Uint256(i)).get();
				value = contract.getString(adr).get().toString();
				this.simpleCache.put(adr, value);
				this.indexCache.add(adr);
			}
		}
		catch (InterruptedException | ExecutionException e)
		{
			gui.Error("Failed to build cache: " + e.getMessage());
		}
		contractTableModelRaw.fireTableDataChanged();
	}

	public String getStringAtIndexBlocking(int index) {
		try
		{
			String retVal;
			Address adr = contract.getStringAtIndex(new Uint256(index)).get();
			retVal = contract.getString(adr).get().toString();
			return retVal;
		}
		catch (InterruptedException | ExecutionException e)
		{
			// TODO Auto-generated catch block
			return null;
		}

	}

	public Future<Utf8String> getStingAtIndexFuture(int index) {
		Address adr;
		try
		{
			adr = contract.getStringAtIndex(new Uint256(index)).get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			gui.Error("Error Getting String from Contract: " + e.getMessage());
			return null;
		}
		return contract.getString(adr);
	}

	/**
	 * Provides a means to simply get cached values of the smart contract, to
	 * enable faster table redraw.
	 * 
	 * @param index
	 * @return
	 */
	public String getStringAtIndexCached(int index) {
		Address adr = indexCache.get(index);
		return simpleCache.get(adr);
	}

	public Address getAddressfromIndex(int index) {
		return indexCache.get(index);
	}

	public int getCacheSize() {
		return this.simpleCache.size();
	}

}// end class
