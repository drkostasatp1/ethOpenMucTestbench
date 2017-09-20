package org.mposch.ethOpenmuc.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URI;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.mposch.ethOpenmuc.ChannelState;
import org.mposch.ethOpenmuc.contracts.BlockChainProviderList;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.tableModels.ContractTableModelRaw;
import org.mposch.ethOpenmuc.gui.tableModels.TableModelChannelState;
import org.mposch.ethOpenmuc.gui.tableModels.CurrencyComboBoxModel;
import org.mposch.ethOpenmuc.test.ContractTests;
import org.mposch.ethOpenmuc.updaters.BalanceUpdater;
import org.mposch.ethOpenmuc.updaters.BlockChainFetcher;
import org.mposch.ethOpenmuc.updaters.OpenMucFetcher;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Channel;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;
import java.awt.Toolkit;

@Component
@AutoConfigureAfter
public class MainWindow {
	@Autowired
	private ContractTableModelRaw	tableModelRaw;
	@Autowired
	private ContractBean			contractBean;
	@Autowired
	private ContractTests			tests;

	@Autowired
	private OpenMucFetcher			openMucFetcher;
	@Autowired
	BlockChainFetcher				blockChainFetcher;
	@Autowired
	BlockChainProviderList			blockChainProviderList;

	@Autowired
	private TableModelChannelState	tableModelChannelState;

	@Autowired
	private ChannelState			channelState;
	@Autowired
	private GuiController			guiController;
	@Autowired
	private CurrencyComboBoxModel	currencyComboBoxModel;
	@Autowired
	private BalanceUpdater			balanceUpdater;

	String[]						columnNames	= { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian" };
	Object[][]						data		= {
			{ "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
			{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
			{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
			{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
			{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
	/**
	 * A Frame that holds all the gui elements for this window
	 */
	public JFrame					frame;
	private JTextField				txtStorageLocation;
	private JComboBox<String>		comboBoxBcProvider;
	private JButton					btnCreateNewContract;
	private JTextField				txtContractValidity;
	private JTextField				txtPrivateKey;
	private JTextField				textFieldWalletFile;
	private JPasswordField			pwdWalletPassword;
	private JCheckBox				lblAccountPrivateKey;
	private JTextField				TxtEthereumAccount;
	private JTable					tableBlockChainRaw;
	private FTable					channelStateTable;
	private JCheckBox				chckbxRunBlockchainupdater;
	private JButton					buttonClearState;
	private JButton					buttonQueryPermissions;
	private JCheckBox				checkBoxReadPermissions;
	private JCheckBox				checkBoxWritePermissions;
	private JButton					btnUpdatepermissions;
	private JLabel					lblAlterPermissionsFor;
	private JTextField				txtPermissionsForUser;
	private JTextField				txtVersionInfo;
	private JButton					buttonRefreshContractRaw;
	private JTextField				lblMessages;
	private JButton					button;
	private JButton					buttonChooseFile;
	private JTextField				txtBalance;
	private JButton					buttonDeleteAll;
	private JComboBox<String>		comboBoxCurrencySelector;

	/**
	 * Create the application.
	 */
	public MainWindow() {

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */

	public void initialize() {
		Preferences p = Preferences.userNodeForPackage(MainWindow.class);
		frame = new JFrame();
		    
        
		frame.setResizable(false);
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblEthereumContractInterface = new JLabel("Ethereum Account");
		lblEthereumContractInterface.setBounds(15, 50, 117, 27);
		frame.getContentPane().add(lblEthereumContractInterface);
		comboBoxBcProvider = new JComboBox<String>();
		comboBoxBcProvider.setBounds(197, 10, 766, 27);
		comboBoxBcProvider.setModel(this.blockChainProviderList);
		frame.getContentPane().add(comboBoxBcProvider);
		comboBoxBcProvider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.putInt("comboBoxBcProvider", comboBoxBcProvider.getSelectedIndex());
			}
		});

		JLabel lblBlockchainProvider = new JLabel("Blockchain Provider (Web3j Instance)");
		lblBlockchainProvider.setBounds(-69, -67, 230, 16);
		frame.getContentPane().add(lblBlockchainProvider);

		JLabel lblStorageContractAddress = new JLabel("Storage Contract Address");
		lblStorageContractAddress.setBounds(15, 89, 160, 16);
		frame.getContentPane().add(lblStorageContractAddress);

		txtStorageLocation = new JTextField();
		txtStorageLocation.setBounds(197, 84, 662, 26);
		txtStorageLocation.setText("0x43...");
		frame.getContentPane().add(txtStorageLocation);
		txtStorageLocation.setColumns(10);
		txtStorageLocation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.put("txtStorageLocation", txtStorageLocation.getText());
			}
		});

		btnCreateNewContract = new JButton("Create new Contract");
		btnCreateNewContract.setForeground(Color.RED);
		btnCreateNewContract.setBounds(1015, 84, 175, 29);
		btnCreateNewContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contractBean.deployNewContract();

			}
		});
		frame.getContentPane().add(btnCreateNewContract);

		JButton btnLoadContract = new JButton("Load Contract");
		btnLoadContract.setBounds(871, 84, 132, 29);
		btnLoadContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contractBean.loadContract(txtStorageLocation.getText());
				// Update Version TExt Field, if the contract is okay

			}
		});
		frame.getContentPane().add(btnLoadContract);

		txtContractValidity = new JTextField();
		txtContractValidity.setBounds(836, 212, 354, 26);
		txtContractValidity.setText("Version");
		frame.getContentPane().add(txtContractValidity);
		txtContractValidity.setColumns(10);

		lblAccountPrivateKey = new JCheckBox("Private key - TestRpc");
		lblAccountPrivateKey.setBounds(0, 181, 175, 23);
		lblAccountPrivateKey.setToolTipText("Enter a private Key here, like one of testRpc output");
		frame.getContentPane().add(lblAccountPrivateKey);
		lblAccountPrivateKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.putBoolean("lblAccountPrivateKey", lblAccountPrivateKey.isSelected());
			}
		});

		txtPrivateKey = new JTextField();
		txtPrivateKey.setBounds(197, 180, 993, 26);
		txtPrivateKey.setText("0x54...");
		txtPrivateKey.setColumns(10);
		frame.getContentPane().add(txtPrivateKey);

		txtPrivateKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.put("txtPrivateKey", txtPrivateKey.getText());
			}
		});

		JButton btnTestContract = new JButton("Test Contract");
		btnTestContract.setBounds(1058, 289, 129, 29);
		btnTestContract.setBackground(Color.RED);
		btnTestContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Start the tests!
				tests.runAllTests();

			}
		});
		frame.getContentPane().add(btnTestContract);

		JCheckBox chckbxRunOpenmucUpdater = new JCheckBox("Run OpenMuc Sync Task");
		chckbxRunOpenmucUpdater.setBounds(0, 213, 186, 23);
		chckbxRunOpenmucUpdater.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				switch (e.getStateChange()) {
					case 1:
						openMucFetcher.sheduleUpdater();
						break;
					case 2:
						openMucFetcher.stopUpdater();
						break;
				}

			}
		});
		frame.getContentPane().add(chckbxRunOpenmucUpdater);

		textFieldWalletFile = new JTextField();
		textFieldWalletFile.setBounds(350, 116, 840, 26);
		textFieldWalletFile.setText("Location of Wallet File (created by Ethereum Wallet)");
		textFieldWalletFile.setColumns(10);
		frame.getContentPane().add(textFieldWalletFile);
		textFieldWalletFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MainWindow.initialize().new ActionListener() WalletFIle.actionPerformed()");
				p.put("textFieldWalletFile", textFieldWalletFile.getText());
			}
		});

		pwdWalletPassword = new JPasswordField();
		pwdWalletPassword.setBounds(197, 148, 993, 26);
		pwdWalletPassword.setToolTipText("Enter the password of the Wallet");
		pwdWalletPassword.setText("Wallet password");
		frame.getContentPane().add(pwdWalletPassword);
		pwdWalletPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(pwdWalletPassword.getPassword());
				p.put("pwdWalletPassword", pwd);
				pwd = "";
			}
		});

		JLabel lblWalletFile = new JLabel("Wallet File");
		lblWalletFile.setBounds(15, 121, 64, 16);
		frame.getContentPane().add(lblWalletFile);

		JLabel lblWalletPassword = new JLabel("Wallet Password");
		lblWalletPassword.setBounds(15, 153, 101, 16);
		frame.getContentPane().add(lblWalletPassword);

		TxtEthereumAccount = new JTextField();
		TxtEthereumAccount.setBounds(197, 50, 662, 26);
		TxtEthereumAccount.setEditable(false);
		TxtEthereumAccount.setColumns(10);
		frame.getContentPane().add(TxtEthereumAccount);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 324, 1180, 112);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);

		tableBlockChainRaw = new JTable();
		tableBlockChainRaw.setModel(tableModelRaw);
		scrollPane.setViewportView(tableBlockChainRaw);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 461, 1180, 236);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane_1);

		channelStateTable = new FTable(tableModelChannelState);
		channelStateTable.setModel(tableModelChannelState);
		channelStateTable.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(channelStateTable);

		chckbxRunBlockchainupdater = new JCheckBox("Run BlockChain Sync Task");
		chckbxRunBlockchainupdater.setBounds(187, 213, 199, 23);
		frame.getContentPane().add(chckbxRunBlockchainupdater);

		buttonClearState = new JButton("Clear Local Cache");
		buttonClearState.setBounds(278, 703, 162, 29);
		buttonClearState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				channelState.clear();
				channelStateTable.repaint();
			}
		});
		frame.getContentPane().add(buttonClearState);

		buttonQueryPermissions = new JButton("QueryPermissions");
		buttonQueryPermissions.setBounds(197, 289, 157, 29);
		buttonQueryPermissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Directly query the permissions from the contract
				guiController.queryPermissions();
			}
		});

		frame.getContentPane().add(buttonQueryPermissions);
		checkBoxReadPermissions = new JCheckBox("Read");
		checkBoxReadPermissions.setBounds(17, 290, 62, 23);
		checkBoxReadPermissions.setToolTipText("Enter a private Key here, like one of testRpc output");
		frame.getContentPane().add(checkBoxReadPermissions);

		checkBoxWritePermissions = new JCheckBox("Write");
		checkBoxWritePermissions.setBounds(77, 290, 64, 23);
		checkBoxWritePermissions.setToolTipText("Enter a private Key here, like one of testRpc output");
		frame.getContentPane().add(checkBoxWritePermissions);

		JButton btnUpdateTable = new JButton("Update Table");
		btnUpdateTable.setBounds(140, 703, 126, 29);
		btnUpdateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (channelState.getSize() > 0)
				{
					tableModelChannelState.fireTableDataChanged();
				}
			}
		});
		frame.getContentPane().add(btnUpdateTable);

		JSeparator separator = new JSeparator();
		separator.setBounds(-10, 244, 1200, 12);
		frame.getContentPane().add(separator);

		btnUpdatepermissions = new JButton("UpdatePermissions");
		btnUpdatepermissions.setBounds(353, 289, 164, 29);
		btnUpdatepermissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiController.updatePermissions();
			}
		});
		frame.getContentPane().add(btnUpdatepermissions);

		lblAlterPermissionsFor = new JLabel("Alter Permissions for User");
		lblAlterPermissionsFor.setBounds(11, 261, 164, 16);
		frame.getContentPane().add(lblAlterPermissionsFor);

		txtPermissionsForUser = new JTextField();
		txtPermissionsForUser.setBounds(197, 262, 993, 26);
		txtPermissionsForUser.setText("0x43...");
		txtPermissionsForUser.setColumns(10);
		frame.getContentPane().add(txtPermissionsForUser);

		txtVersionInfo = new JTextField();
		txtVersionInfo.setBounds(1058, 9, 132, 26);
		txtVersionInfo.setText("Version Info");
		frame.getContentPane().add(txtVersionInfo);
		txtVersionInfo.setColumns(10);

		buttonRefreshContractRaw = new JButton("Refresh Data");
		buttonRefreshContractRaw.setBounds(931, 289, 124, 29);
		buttonRefreshContractRaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModelRaw.fireTableDataChanged();
			}
		});
		frame.getContentPane().add(buttonRefreshContractRaw);

		lblMessages = new JTextField("Messages");
		lblMessages.setBounds(10, 738, 1180, 30);
		lblMessages.setBackground(Color.RED);
		frame.getContentPane().add(lblMessages);

		button = new JButton("ADD CHANNEL");
		button.setBounds(0, 703, 139, 29);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] list = { "nothing" };
				list = channelState.getAllId().toArray(list);
				JComboBox<String> jcb = new JComboBox(list);
				jcb.setEditable(true);
				AutoCompleteDecorator.decorate(jcb);
				jcb.transferFocus();
				JOptionPane.showMessageDialog(frame, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
				String s = (String) jcb.getSelectedItem();
				Channel ch = new Channel();
				ch.setId(s);
				ch.setSource("EDITOR");
				ch.setType("DOUBLE");
				Record re = new Record();
				re.setValue("0.0");
				re.setFlag("VALID");
				re.setTimestamp(System.currentTimeMillis());
				ch.setRecord(re);
				channelState.put(ch);
				tableModelChannelState.fireTableDataChanged();
			}
		});
		frame.getContentPane().add(button);

		buttonChooseFile = new JButton("Choose File");
		buttonChooseFile.setBounds(197, 116, 117, 29);
		buttonChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Choose a wallet file", FileDialog.LOAD);
				fd.setDirectory("~/Library/Ethereum");
				fd.setVisible(true);

				String filename = fd.getDirectory() + fd.getFile();

				textFieldWalletFile.setText(filename);
			}
		});
		frame.getContentPane().add(buttonChooseFile);
		txtBalance = new JTextField();
		txtBalance.setBounds(871, 50, 243, 26);
		txtBalance.setEditable(false);
		txtBalance.setText("Balance");
		frame.getContentPane().add(txtBalance);

		txtBalance.setColumns(10);

		buttonDeleteAll = new JButton("DELETE ALL FROM BC");
		buttonDeleteAll.setBounds(1013, 709, 177, 29);
		buttonDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contractBean.removeAll();
			}
		});
		buttonDeleteAll.setForeground(Color.RED);
		frame.getContentPane().add(buttonDeleteAll);

		JLabel lblEthereumNode = new JLabel("Ethereum Node");
		lblEthereumNode.setBounds(15, 9, 117, 27);
		frame.getContentPane().add(lblEthereumNode);

		JButton buttonDisplayEtherScan = new JButton("EtherScan");
		buttonDisplayEtherScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try
				{
					URI uri = new URI("https://rinkeby.etherscan.io/address/" + txtStorageLocation.getText());

					Desktop.getDesktop().browse(uri);
				}
				catch (Exception ex)
				{
					guiController.Error("Error opening Browser: " + ex.getMessage());
				}
			}
		});
		buttonDisplayEtherScan.setBounds(766, 289, 164, 29);
		frame.getContentPane().add(buttonDisplayEtherScan);

		JButton buttonClearError = new JButton("Clear Errors");
		buttonClearError.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiController.Success("Errors Cleared");
			}
		});
		buttonClearError.setBounds(452, 703, 162, 29);
		frame.getContentPane().add(buttonClearError);

		comboBoxCurrencySelector = new JComboBox();
		comboBoxCurrencySelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balanceUpdater.updateTextField();
			}
		});
		comboBoxCurrencySelector.setToolTipText("Select a currency");
		comboBoxCurrencySelector.setBounds(1113, 51, 77, 27);
		comboBoxCurrencySelector.setModel(currencyComboBoxModel);
		frame.getContentPane().add(comboBoxCurrencySelector);

		chckbxRunBlockchainupdater.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				switch (e.getStateChange()) {
					case 1:
						blockChainFetcher.sheduleUpdater();
						break;
					case 2:
						blockChainFetcher.stopUpdater();
						break;
				}
			}
		});
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	public JComboBox<String> getComboBoxBcProvider() {
		return comboBoxBcProvider;
	}

	public JTextField getTxtStorageLocation() {
		return txtStorageLocation;
	}

	public JTable getTableBlockChainRaw() {
		return tableBlockChainRaw;
	}

	public JButton getBtnCreateNewContract() {
		return btnCreateNewContract;
	}

	public JTextField getTxtContractValidity() {
		return txtContractValidity;
	}

	public JTextField getTextFieldWalletFile() {
		return textFieldWalletFile;
	}

	public JPasswordField getPwdWalletPassword() {
		return pwdWalletPassword;
	}

	public JTextField getTxtPrivateKey() {
		return txtPrivateKey;
	}

	public JCheckBox getLblAccountPrivateKey() {
		return lblAccountPrivateKey;
	}

	public JTextField getTxtEthereumAccount() {
		return TxtEthereumAccount;
	}

	public JTable getChannelStateTable() {
		return this.channelStateTable;
	}

	public JTextField getTxtVersionInfo() {
		return txtVersionInfo;
	}

	public JTextField getLblMessages() {
		return lblMessages;
	}

	/**
	 * @return
	 */
	public JTextField getTxtBalance() {
		return txtBalance;
	}

	public JTextField getTxtPermissionsForUser() {
		return txtPermissionsForUser;
	}

	public JCheckBox getCheckBoxReadPermissions() {
		return checkBoxReadPermissions;
	}

	public JCheckBox getCheckBoxWritePermissions() {
		return checkBoxWritePermissions;
	}

	public JComboBox getComboBoxCurrencySelector() {
		return comboBoxCurrencySelector;
	}
}
