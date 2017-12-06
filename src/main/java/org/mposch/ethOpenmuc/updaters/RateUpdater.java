package org.mposch.ethOpenmuc.updaters;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.Color;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.mposch.ethOpenmuc.Config;
import org.mposch.ethOpenmuc.EthereumConversionRate.EthereumConversionRate;
import org.mposch.ethOpenmuc.contracts.BlockChainProviderList;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.gui.tableModels.CurrencyComboBoxModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import rx.Subscription;

/**
 * @author mposch
 *
 */
@Component
public class RateUpdater implements Runnable {
	@Autowired
	private MainWindow					mainWindow;
	@Autowired
	private CurrencyComboBoxModel currencyComboBoxModel;
	@Autowired
	private GuiController guiController;
	
	
	private ScheduledExecutorService	sheduler;
	private BigInteger balance;
	public RateUpdater() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		try
		{
			currencyComboBoxModel.updateRatesBlocking();
			guiController.Success("Updated Exchange rates from Kraken");
		}
		catch (Exception e)
		{
			guiController.Error("Could not update exchange rates");
		
		}
   }
/**
 * Stops the update timer for the Rate Updater
 */
	public void stopUpdater() {

		if (sheduler != null) sheduler.shutdown();
	}
/**
 * Starts the update timer for the rate
 */
	public void sheduleUpdater() {
		sheduler = Executors.newScheduledThreadPool(1);
		sheduler.scheduleAtFixedRate(() -> {
			this.run();

		}, 0, Config.EXCHANGERATEUPDATE, SECONDS);

	}

	public BigInteger getBalance() {
		return balance;
	}


}
