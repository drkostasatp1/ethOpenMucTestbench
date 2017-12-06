package org.mposch.ethOpenmuc;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.EventQueue;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.mposch.ethOpenmuc.contracts.BlockChainProviderList;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.updaters.OpenMucFetcher;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.JsonChannelList;
import org.mposch.solidityContractWrapper.RecordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
/**
 * This class holds Configutarion Objects, that are used Globally.
 * @author mposch
 *
 */
@Configuration
public class Config {

	// Define the time between update calls to openmuc
	public static final long	OPENMUCUPDATERATE		= 1;
	public static final long	BALANCEUPDATERATE		= 10;
	public static final long	BLOCKCHAINUPDATERATE	= 10;
	public static final long	TRANSACTION_RETRY		= 3;

	// Query the exchange rates every two minutes
	public static final long	EXCHANGERATEUPDATE		= 120;
	// Changing this value will alter the hash calculatio for stored values.
	// Therfore, all stored items will be lost

	public static final int		ADDRESSLENGTH			= 16;

	private JsonChannelList		theList;

	// This tells spring spring which class to begin, and which method to call
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public JsonChannelList getTheList() {
		return theList;
	}


}
