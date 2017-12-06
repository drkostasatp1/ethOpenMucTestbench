package org.mposch.ethOpenmuc.contracts;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import org.bouncycastle.util.encoders.UrlBase64;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.infura.InfuraHttpService;
import org.web3j.protocol.ipc.UnixIpcService;

/**
 * @author mposch
 * This class provides utillity methods to provide user selection of different Blockchain providers. The user can for example select a local Ethereum node, 
 * or a remote Service like Infura.
 * 
 */
@Component
public class BlockChainProviderList extends ArrayList<String> implements ComboBoxModel {
	@Autowired
	MainWindow				mainWindow;
	@Autowired
	private GuiController	guiController;
	private Web3j			web3j;
	private String			lastConnectionEndpoint;
	String					selection	= null;

	// private ArrayList<BcProvider> data;
	public BlockChainProviderList() {
		this.add("http://localhost:8545");
		this.add("https://ropsten.infura.io/cSOwB0UnVQUlOb7ycFS5");
		String home = System.getProperty("user.home");
		this.add(home + "/Library/Ethereum/rinkeby/geth.ipc");
		this.add(home +"/Library/Ethereum/testnet/geth.ipc");
		this.add(home +"/Library/Ethereum/geth.ipc");
	}
/**
 * This metod will return a fresh web3j instance in any case.
 * @return
 */
	public Web3j refreshWeb3j() {
		this.web3j = null;
		this.lastConnectionEndpoint = "";
		return this.getWeb3j();
	}

	/**
	 * This method will return a new instance of Web3j, or re-use an already
	 * existing connection if the url is not changed.
	 * 
	 * @return
	 */
	public Web3j getWeb3j() {
		
		String connectTo;
		connectTo = (String) mainWindow.getComboBoxBcProvider().getSelectedItem();

		if ((web3j == null) || (!lastConnectionEndpoint.equals(connectTo)))
			return this.getWeb3j((String) mainWindow.getComboBoxBcProvider().getSelectedItem());
		else return this.web3j;
	}
/**
 * This method actually instanciates a Web3j instance, according to the url different versions of web3j will be created. 
 * Additionally the gui elemets displaying the connection state will be updated
 * @param url
 * @return
 */
	public Web3j getWeb3j(String url) {
		mainWindow.getTxtVersionInfo().setText("Not Connected");
		if ((url == null) || (url.isEmpty()))
		{
			// Create default service
			web3j = Web3j.build(new HttpService());
		}
		else if (url.contains("infura"))
		{
			// Create Infura service
			web3j = Web3j.build(new InfuraHttpService(url));
		}
		else if (url.contains("ipc"))
		{
			 web3j = Web3j.build(new UnixIpcService(url));
		}
		else
		{
			web3j = Web3j.build(new HttpService(url));
		}
		// Async call to update the version info..
		
		web3j.web3ClientVersion().observable().subscribe(x -> {
			String clientVersion;
			clientVersion = x.getWeb3ClientVersion();
			mainWindow.getTxtVersionInfo().setText(clientVersion);
		});

		this.guiController.Success("Connected to Ethereum Node");
		if (web3j == null) guiController.Error("Could not Connect to Ethereum Node");
		this.lastConnectionEndpoint = url;
		return web3j;

	}
/**
 * Will return the Credentials, depending on user selection (Private key or Ehtereum Wallet file)
 * @return
 */
	public Credentials getCredentials() {
		Credentials retVal = null;
		try
		{
			if (mainWindow.getLblAccountPrivateKey().isSelected())
			{
				// Use the Private Key
				retVal = Credentials.create(mainWindow.getTxtPrivateKey().getText());
			}
			else
			{
				// Use The Wallet File and Password
				char[] pwdarray = mainWindow.getPwdWalletPassword().getPassword();
				String pwd = new String(pwdarray);
				String file = mainWindow.getTextFieldWalletFile().getText();
				retVal = WalletUtils.loadCredentials(pwd, file);
			} // Else
		}
		catch (IOException | CipherException e)
		{
			System.out.println(
					"ERROR Credential Creation failed. Right Password, walletfile or private key?  " + e.getMessage());
			this.guiController.Error("ERROR Credential Creation failed. Right Password, walletfile or private key?");
			return null;
		}
		return retVal;
	}

	@Override
	public int getSize() {
		return this.size();
	}

	@Override
	public Object getElementAt(int index) {
		return this.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selection = (String) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;

	}

	/**
	 * @param urlString
	 * @param privateKey
	 * @param contractAddress
	 * @return
	 */
	// private int addInfura(String urlString) {
	// try
	// {
	// BcProvider element = new BcProvider();
	// data.add(element);
	// element.isActive = false;
	// element.displayName = "Infura, " + urlString + ", ";
	// HttpService httpService = new InfuraHttpService(urlString);// defaults
	// // to
	// // http://localhost:8545/
	// // element.storageContractAddress = contractAddress;
	// // Set the credentials and start to init web3j
	// // element.credentials = Credentials.create (privateKey);
	//
	// element.web3j = Web3j.build(httpService); // Finally store the
	// // element data if all
	// // went well.
	// // Store the data and select the dataset:
	//
	// element.isActive = true; // Initialisation went well, set to
	// // isactive.
	// return data.indexOf(element);
	// }
	// catch (Exception e)
	// {
	// System.out.println("ERROR: Coiuld not construct Blockchain Service" +
	// e.getMessage());
	// return Integer.MAX_VALUE;
	// }
	//
	// }
	//
	// /**
	// * @param privateKey
	// * @param contractAddress
	// * @return
	// */
	//
	// private int addDefaultProvider() {
	// try
	// {
	// BcProvider element = new BcProvider();
	// element.displayName = "Default localhost, port 8545";
	// // element.storageContractAddress = contractAddress;
	// // Set the credentials and start to init web3j
	// // element.credentials = Credentials.create (privateKey);
	// element.web3j = Web3j.build(new HttpService()); // Finally store the
	// // element data if
	// // all went well.
	// System.out.println("setLocalhttp: " + this.toString());
	//
	// // Store the data and select the dataset:
	// data.add(element);
	// return data.indexOf(element);
	// }
	// catch (Exception e)
	// {
	// System.out.println("ERROR: Coiuld not construct Blockchain Service" +
	// e.getMessage());
	// return Integer.MAX_VALUE;
	// }
	//
	// }
	//
	// /**
	// * Consturct the Web3j Instance, either using default values or
	// Infura/normal http providers.
	// * @param url The Url where to connect to
	// * @return The Web3j Instance
	// */
	//

}
