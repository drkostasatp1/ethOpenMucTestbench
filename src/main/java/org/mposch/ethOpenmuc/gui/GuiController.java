package org.mposch.ethOpenmuc.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.prefs.Preferences;

import javax.annotation.PostConstruct;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

import org.mposch.ethOpenmuc.contracts.BcProvider;
import org.mposch.ethOpenmuc.contracts.BlockChainProviderList;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.tableModels.ContractTableModelRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;


/***
 * This Class will control the gui. The Gui objects itself will be created by the Spring framework as a component and can therefore be autowired here.
 * 
 * @author mposch
 *
 */
@Controller
public class GuiController implements PropertyChangeListener{
@Autowired
private MainWindow mainWindow;
@Autowired
private BlockChainProviderList bcProviders;
@Autowired
private ContractTableModelRaw tableModelRaw;
@Autowired
private ContractBean contractBean;
@Autowired
private RangeChecking transactionMonitor;
/**
 * 
 */
public GuiController(){
		// TODO Auto-generated constructor stub
	
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	
	public void updateGui ()
	{
		System.out.println("UPDATE GUI..");
		this.tableModelRaw.fireTableDataChanged();
		
	}
	
	public void initGui()
	{
		System.out.println("INIT GUI...");
		mainWindow.initialize();
		transactionMonitor.initialize();
		// Read the stored properties
//		txtStorageLocation
//		txtPrivateKey
//		pwdWalletPassword
//		textFieldWalletFile
//		comboBoxBcProvider (selecet)
		Preferences p = Preferences.userNodeForPackage(MainWindow.class);
		
		mainWindow.getTxtStorageLocation().setText(p.get("txtStorageLocation", "0x333aa92cd8ded6b27bafe6e2f1b9ab272a74b614"));
		mainWindow.getTxtPrivateKey().setText(p.get("txtPrivateKey", "8ec4182356fcdc854d65ee86421190310102dc4219addf3de1229c4c2cb873b3"));
		mainWindow.getPwdWalletPassword().setText(p.get("pwdWalletPassword", "qaywsxedc"));
	
		mainWindow.getTextFieldWalletFile().setText(p.get("textFieldWalletFile", "/Volumes/UserData/mposch/Library/Ethereum/testnet/keystore/testnet"));
		
		mainWindow.getComboBoxBcProvider().setSelectedIndex(p.getInt("comboBoxBcProvider", 0));
		mainWindow.getLblAccountPrivateKey().setSelected(p.getBoolean("lblAccountPrivateKey", false));
	}
	
	
	public boolean queryIsOwner()
	{
		Address adr = new Address (mainWindow.getTxtEthereumAccount().getText());
		Address owner;
		try
		{
			owner = contractBean.getContract().owner().get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			Error("Could not query Owner ");
			return false;
		}
		return adr.equals(owner);
	}
	
	
	public void queryPermissions() {
		
		Bool b;
		try
		{
			Address adr = new Address(mainWindow.getTxtPermissionsForUser().getText());
			b = contractBean.getContract().getPermissionRead(adr).get();
			mainWindow.getCheckBoxReadPermissions().setSelected(b.getValue());
			System.out.println("Read Permission: " + b.toString());
			b = contractBean.getContract().getPermissionWrite(adr).get();
			mainWindow.getCheckBoxWritePermissions().setSelected(b.getValue());
			System.out.println("Write Permissions: " + b.toString());
			
		}
		catch (Exception e)
		{
			Error("Error: Query Permissions: " + e.getMessage());
		}

	}

	/**
	 * 
	 */
	
	public void updatePermissions() {
		Address adr = new Address(mainWindow.getTxtPermissionsForUser().getText());
		 CompletableFuture.supplyAsync(() -> {
		try
		{
			contractBean.getContract().setPermissionRead(adr, new Bool(mainWindow.getCheckBoxReadPermissions().isSelected())).get();
			contractBean.getContract().setPermissionWrite(adr, new Bool(mainWindow.getCheckBoxWritePermissions().isSelected())).get();
		}
		catch (Exception e)
		{
			this.Error("Error: MainWindow.updatePermissions()" + e.getMessage());
		}
		return 0;
		 });
		 
	}
	

	public int getBcProviderSelection() {
		return mainWindow.getComboBoxBcProvider().getSelectedIndex();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		// Update the gui if anything happens
	
		//this.updateGui();
	}
	/**
	 * Sets the textbox to red and Displays a Message on the Bottom of the Screen
	 * @param m
	 */
	public void Error(String m)
	{
		Date date = new Date(System.currentTimeMillis());
		
		mainWindow.getLblMessages().setText(date.toString() + ": "+ m);
		mainWindow.getLblMessages().setBackground(Color.RED);
	}
	/**
	 * Sets the textbox to green and Displays a Message on the Bottim og the Screen
	 * @param m
	 */
	public void Success(String m)
	{
		Date date = new Date(System.currentTimeMillis());
		mainWindow.getLblMessages().setText(date.toString() + ": "+ m);
		mainWindow.getLblMessages().setBackground(Color.GREEN);
	}
	
	
}
