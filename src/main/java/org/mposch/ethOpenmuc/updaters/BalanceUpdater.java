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
 * This component updates the Balance field, and calculates the Conversion Rate if necessary. 
 * 
 */
@Component
public class BalanceUpdater implements Runnable {
	@Autowired
	private BlockChainProviderList		blockChainProviderList;
	@Autowired
	private MainWindow					mainWindow;
	@Autowired
	private CurrencyComboBoxModel currencyComboBoxModel;
	private ScheduledExecutorService	sheduler;
	private BigInteger balance;
	
	
	public BalanceUpdater() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The Run method is called periodically, to update the balance.
	 * 
	 */
	@Override
	public void run() {
		Web3j web3j = blockChainProviderList.getWeb3j();
		Credentials ce = blockChainProviderList.getCredentials();
		double result = 0;
		EthGetBalance ethbal;
		try
		{
			mainWindow.getTxtEthereumAccount().setText(ce.getAddress());
			String adr = mainWindow.getTxtEthereumAccount().getText();
			
			ethbal = web3j.ethGetBalance(adr, DefaultBlockParameterName.LATEST).send();
			balance = ethbal.getBalance();
			if (balance.compareTo(BigInteger.ZERO) == 0)
			{
				// We Are Broke.
				mainWindow.getTxtBalance().setBackground(Color.RED);
			}else
			{
				mainWindow.getTxtBalance().setBackground(Color.GREEN);
				// Calculate the balance in the selected currency
			}
			
			updateTextField();	
		}
		catch (Exception e)
		{
			mainWindow.getTxtBalance().setBackground(Color.RED);
			mainWindow.getTxtBalance().setText("Could not get Balance");
		}

	}


	
	/**
	 * Updates the textfield of the main window
	 */
	public void updateTextField() {
		double result;
		result = balance.doubleValue()*this.currencyComboBoxModel.getCurrentRate();
		mainWindow.getTxtBalance().setText(Double.toString(result));
	}
/**
 * Stops the update timer.
 */
	public void stopUpdater() {

		if (sheduler != null) sheduler.shutdown();
	}
/**
 * Starts the update timer
 */
	public void sheduleUpdater() {
		sheduler = Executors.newScheduledThreadPool(1);
		sheduler.scheduleAtFixedRate(() -> {
			this.run();

		}, 0, Config.BALANCEUPDATERATE, SECONDS);

	}

	public BigInteger getBalance() {
		return balance;
	}


}
