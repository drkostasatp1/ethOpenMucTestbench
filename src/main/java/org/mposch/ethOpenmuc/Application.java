package org.mposch.ethOpenmuc;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import org.mposch.ethOpenmuc.contracts.BlockChainProviderList;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.mposch.ethOpenmuc.gui.tableModels.CurrencyComboBoxModel;
import org.mposch.ethOpenmuc.gui.tableModels.TableModelChannelState;
import org.mposch.ethOpenmuc.updaters.BalanceUpdater;
import org.mposch.ethOpenmuc.updaters.OpenMucFetcher;
import org.mposch.ethOpenmuc.updaters.RateUpdater;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Record;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.simpleChannel;
import org.mposch.solidityContractWrapper.RecordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import rx.Subscription;

@SpringBootApplication
@EnableScheduling
@EnableAsync
/**
 * The Application Class is the main class of the application. 
 * It contains a main method, that in turn creates a new Spring application. 
 * Spring manages the lifetime of all objects in this code.
 * 
 * @author mposch
 *
 */
public class Application {
	// No additional configuration is required
	@Autowired
	GuiController					gui;
	@Autowired
	private TableModelChannelState	tableModelChannelState;
	@Autowired
	private ChannelState			channelState;
	@Autowired
	private BalanceUpdater			balanceUpdater;

	@Autowired
	private OpenMucFetcher			openMucFetcher;
	@Autowired 
	private CurrencyComboBoxModel currencyComboBoxModel;
	@Autowired
	private RateUpdater rateUpdater;
	/**
	 * The main method creates a new Spring application. In order to use a gui, the headless option has to be passed as well.
	 * @param args
	 */

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).headless(false).web(false).run(args);
	}

	/**
	 * Spring uses Beans to manage the Lifetime of an Objects. Beans are Created and destroyed by the Spring framework. 
	 * The annotation @Bean tell the framework that the following method should be identified as Bean. 
	 * This Bean customises the Spring thread executor pool.
	 * @return
	 */
	@Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Ethereum Transactions");
        executor.initialize();
        return executor;
    }
	/**
	 * This bean is executed after the framework initialisation, and is used to call gui initialisation, 
	 * and to created the regular sheduler tasks required. 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public CommandLineRunner run() throws Exception {

		return args -> {
			System.out.println("COMMANDLINE RUNNER STARTED");
			gui.initGui();
			gui.updateGui();
			
			// Setup the walletfile
			// mainWindow.getTextFieldWalletFile().setText("/Volumes/UserData/mposch/Documents/FH/MasterThesis_MatthiasPosch/java/MT_Experiment4/walletFile");
			// Setup the passwd
			// mainWindow.getPwdWalletPassword().setText("qaywsxedc");
			// For Development, setup the gui fields
			// mainWindow.getTxtPrivateKey().setText("8ec4182356fcdc854d65ee86421190310102dc4219addf3de1229c4c2cb873b3");
			// Setup Observers
			channelState.addObserver(tableModelChannelState);
			balanceUpdater.sheduleUpdater();
			rateUpdater.sheduleUpdater();
			gui.Success("Application Initialized");
			
		};

	}

}
