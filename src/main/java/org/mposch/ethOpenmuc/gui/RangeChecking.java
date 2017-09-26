package org.mposch.ethOpenmuc.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
@Component
public class RangeChecking {
@Autowired
private MainWindow mainWindow;
@Autowired
private ContractBean contractBean;
@Autowired GasCounter gasCounter;

	private JFrame frmTransactionMonitor;
	private JTextField txtHighvalue;
	private JTextField txtLowvalue;
	private JButton btnQueryvalues;
	private JButton btnSetvalues;
	private JLabel lblHighValue;
	private JLabel lblLowValue;
	private JTextField textFieldStatus;
	/**
	 * Create the application.
	 */
	public RangeChecking() {
	}
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frmTransactionMonitor = new JFrame();
		frmTransactionMonitor.setTitle("Range Checking Controller");
		frmTransactionMonitor.setVisible(true);
		frmTransactionMonitor.setBounds(100, 100, 339, 246);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 91, 146, 0};
		gridBagLayout.rowHeights = new int[]{0, 71, 0, 41, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmTransactionMonitor.getContentPane().setLayout(gridBagLayout);
		
		lblLowValue = new JLabel("Low Value");
		GridBagConstraints gbc_lblLowValue = new GridBagConstraints();
		gbc_lblLowValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblLowValue.gridx = 2;
		gbc_lblLowValue.gridy = 3;
		frmTransactionMonitor.getContentPane().add(lblLowValue, gbc_lblLowValue);
		
		txtLowvalue = new JTextField();
		txtLowvalue.setText("lowValue");
		GridBagConstraints gbc_txtLowvalue = new GridBagConstraints();
		gbc_txtLowvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLowvalue.insets = new Insets(0, 0, 5, 0);
		gbc_txtLowvalue.gridx = 3;
		gbc_txtLowvalue.gridy = 3;
		frmTransactionMonitor.getContentPane().add(txtLowvalue, gbc_txtLowvalue);
		txtLowvalue.setColumns(10);
		
		lblHighValue = new JLabel("High Value");
		GridBagConstraints gbc_lblHighValue = new GridBagConstraints();
		gbc_lblHighValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblHighValue.gridx = 2;
		gbc_lblHighValue.gridy = 4;
		frmTransactionMonitor.getContentPane().add(lblHighValue, gbc_lblHighValue);
		
		txtHighvalue = new JTextField();
		txtHighvalue.setText("highValue");
		GridBagConstraints gbc_txtHighvalue = new GridBagConstraints();
		gbc_txtHighvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHighvalue.ipadx = 10;
		gbc_txtHighvalue.insets = new Insets(0, 0, 5, 0);
		gbc_txtHighvalue.gridx = 3;
		gbc_txtHighvalue.gridy = 4;
		frmTransactionMonitor.getContentPane().add(txtHighvalue, gbc_txtHighvalue);
		txtHighvalue.setColumns(10);
		
		btnQueryvalues = new JButton("queryValues");
		btnQueryvalues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Int256 highval, lowval;
				try
				{
					textFieldStatus.setText("Querying High and Low Values");
					highval = contractBean.getContract().getHighValue().get();
					lowval = contractBean.getContract().getLowValue().get();
					
					txtHighvalue.setText(highval.getValue().toString());
					txtLowvalue.setText(lowval.getValue().toString());
					
					textFieldStatus.setText("Query complete");
					textFieldStatus.setBackground(Color.GREEN);
					
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					textFieldStatus.setText("Error: " + e1.getMessage());
					textFieldStatus.setBackground(Color.RED);
				}
				
				
			}
		});
		GridBagConstraints gbc_btnQueryvalues = new GridBagConstraints();
		gbc_btnQueryvalues.anchor = GridBagConstraints.WEST;
		gbc_btnQueryvalues.insets = new Insets(0, 0, 5, 0);
		gbc_btnQueryvalues.gridx = 3;
		gbc_btnQueryvalues.gridy = 5;
		frmTransactionMonitor.getContentPane().add(btnQueryvalues, gbc_btnQueryvalues);
		
		btnSetvalues = new JButton("setValues");
		btnSetvalues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String highval,lowval;
				Int256 high, low;
				highval = txtHighvalue.getText();
				lowval = txtLowvalue.getText();
				high = new Int256(new BigInteger(highval));
				low = new Int256(new BigInteger(lowval));
				// Transact the high and low values onto blockchain

				try
				{
					textFieldStatus.setText("Updating high and low values");
					gasCounter.addGas( contractBean.getContract().setRangeValues(high, low).get());
					textFieldStatus.setText("Transaction complete");
					textFieldStatus.setBackground(Color.GREEN);
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					textFieldStatus.setText("Error: " + e1.getMessage());
					textFieldStatus.setBackground(Color.RED);

				}
				
			}
		});
		GridBagConstraints gbc_btnSetvalues = new GridBagConstraints();
		gbc_btnSetvalues.anchor = GridBagConstraints.WEST;
		gbc_btnSetvalues.insets = new Insets(0, 0, 5, 0);
		gbc_btnSetvalues.gridx = 3;
		gbc_btnSetvalues.gridy = 6;
		frmTransactionMonitor.getContentPane().add(btnSetvalues, gbc_btnSetvalues);
		
		textFieldStatus = new JTextField();
		GridBagConstraints gbc_textFieldStatus = new GridBagConstraints();
		gbc_textFieldStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStatus.gridwidth = 4;
		gbc_textFieldStatus.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStatus.gridx = 0;
		gbc_textFieldStatus.gridy = 7;
		frmTransactionMonitor.getContentPane().add(textFieldStatus, gbc_textFieldStatus);
		textFieldStatus.setColumns(10);
	}

	
}
