package org.mposch.ethOpenmuc.updaters;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.mposch.ethOpenmuc.ChannelState;
import org.mposch.ethOpenmuc.Config;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.mposch.ethOpenmuc.gui.tableModels.ContractTableModelRaw;
import org.mposch.ethOpenmuc.gui.tableModels.TableModelChannelState;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Channel;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.simpleChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Performs regular updateby calling the smart contract and querying the stores parameter values. 
 * Calling the contract is free and can therefore be perfomred regularly.
 * 
 * @author mposch
 *
 */
@Component
public class BlockChainFetcher implements Runnable {
	@Autowired
	private ChannelState				channelState;
	@Autowired
	private ContractBean				contractBean;
	@Autowired
	private TableModelChannelState		tableModelChannelState;
	@Autowired
	private GuiController				gui;
	private ScheduledExecutorService	sheduler;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Iterate over the contract to get stored values
		System.out.println("BlockChainFetcher.run()");
		long start, end;
		start = System.currentTimeMillis();
		try
		{
			Channel c;
			// Try to load a contract if it is not already done
			if (contractBean.isValid() == false) contractBean.loadContract();
			if (contractBean.isValid() == false)
				throw new Exception("Contract not Valid. Create or Load a Storage Contract.");
			Uint256 maxUint256 = contractBean.getContract().getCount().get();
			int max = maxUint256.getValue().intValue();
			for (int i = 0; i < max; i++)
			{
				String JSONString = contractBean.getStringAtIndexBlocking(i);
				// This initiates a JSON Parser
				try
				{
					ObjectMapper om = new ObjectMapper();
					// The Parser needs two things: The JSON, and the properly
					// annotated class:
					c = om.readValue(JSONString, Channel.class);
					// if the value is stored using simple storage, the reord
					// field wikll be null:
					if (c.getRecord() == null)
					{
						c = om.readValue(JSONString, simpleChannel.class);
					}
					// c.toString();
					c.setSource("BLOCKCHAIN");
					this.channelState.put(c);
				}
				catch (JsonParseException | JsonMappingException jse)
				{
					// The paring of the channel failed. Try to parse a simple
					// channel
					try
					{

						ObjectMapper om = new ObjectMapper();
						c = om.readValue(JSONString, simpleChannel.class);
						c.setSource("BLOCKCHAIN");
						this.channelState.put(c);
					}

					catch (JsonParseException | JsonMappingException jse2)
					{
						gui.equals("Json Parsing error: " + jse2.getMessage());
					}
				}

			}

			System.out.println("BlockChainFetcher.run(): END ");

			end = System.currentTimeMillis();
			System.out.print("Runtime: " + Long.toString(end - start) + "ms");

			tableModelChannelState.fireTableDataChanged();
			// Post values on the Blockchain

			this.channelState.postAllOnBlockChain();
		}
		catch (Exception e)
		{
			gui.Error("Exception at BlockChainFetcher.run(): " + e.getMessage());
			end = System.currentTimeMillis();
			System.out.print("Runtime: " + Long.toString(end - start) + "ms");
		}
	}

	public void stopUpdater() {

		if (sheduler != null) sheduler.shutdown();
	}

	public void sheduleUpdater() {
		System.out.println("Start Blockchain Updater scheduled..");
		sheduler = Executors.newScheduledThreadPool(1);
		sheduler.scheduleAtFixedRate(() -> {
			this.run();

		}, 0, Config.BLOCKCHAINUPDATERATE, SECONDS);
	}
}
