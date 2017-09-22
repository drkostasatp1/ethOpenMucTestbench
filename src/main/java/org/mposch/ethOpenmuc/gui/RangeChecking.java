package org.mposch.ethOpenmuc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Uint;
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
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
@Component
public class RangeChecking {
@Autowired
private MainWindow mainWindow;
@Autowired
private ContractBean contractBean;
	private JFrame frmTransactionMonitor;
	private JTextField txtHighvalue;
	private JTextField txtLowvalue;
	private JButton btnQueryvalues;
	private JButton btnSetvalues;



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
		frmTransactionMonitor.setBounds(100, 100, 450, 300);
		frmTransactionMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmTransactionMonitor.getContentPane().setLayout(gridBagLayout);
		
		txtHighvalue = new JTextField();
		txtHighvalue.setText("highValue");
		GridBagConstraints gbc_txtHighvalue = new GridBagConstraints();
		gbc_txtHighvalue.anchor = GridBagConstraints.WEST;
		gbc_txtHighvalue.insets = new Insets(0, 0, 5, 0);
		gbc_txtHighvalue.gridx = 1;
		gbc_txtHighvalue.gridy = 1;
		frmTransactionMonitor.getContentPane().add(txtHighvalue, gbc_txtHighvalue);
		txtHighvalue.setColumns(10);
		
		txtLowvalue = new JTextField();
		txtLowvalue.setText("lowValue");
		GridBagConstraints gbc_txtLowvalue = new GridBagConstraints();
		gbc_txtLowvalue.anchor = GridBagConstraints.WEST;
		gbc_txtLowvalue.insets = new Insets(0, 0, 5, 0);
		gbc_txtLowvalue.gridx = 1;
		gbc_txtLowvalue.gridy = 2;
		frmTransactionMonitor.getContentPane().add(txtLowvalue, gbc_txtLowvalue);
		txtLowvalue.setColumns(10);
		
		btnQueryvalues = new JButton("queryValues");
		btnQueryvalues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Uint256 highval, lowval;
				try
				{
					highval = contractBean.getContract().getHighValue().get();
					lowval = contractBean.getContract().getLowValue().get();
					txtHighvalue.setText(highval.toString());
					txtLowvalue.setText(lowval.toString());
				}
				catch (InterruptedException | ExecutionException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		GridBagConstraints gbc_btnQueryvalues = new GridBagConstraints();
		gbc_btnQueryvalues.anchor = GridBagConstraints.WEST;
		gbc_btnQueryvalues.insets = new Insets(0, 0, 5, 0);
		gbc_btnQueryvalues.gridx = 1;
		gbc_btnQueryvalues.gridy = 3;
		frmTransactionMonitor.getContentPane().add(btnQueryvalues, gbc_btnQueryvalues);
		
		btnSetvalues = new JButton("setValues");
		btnSetvalues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String highval,lowval;
				Uint256 high, low;
				highval = txtHighvalue.getText();
				lowval = txtLowvalue.getText();
				high = new Uint256(new BigInteger(highval));
				low = new Uint256(new BigInteger(lowval));
				// Transact the high and low values onto blockchain
				contractBean.getContract().setRangeValues(high, low);
			}
		});
		GridBagConstraints gbc_btnSetvalues = new GridBagConstraints();
		gbc_btnSetvalues.anchor = GridBagConstraints.WEST;
		gbc_btnSetvalues.insets = new Insets(0, 0, 5, 0);
		gbc_btnSetvalues.gridx = 1;
		gbc_btnSetvalues.gridy = 4;
		frmTransactionMonitor.getContentPane().add(btnSetvalues, gbc_btnSetvalues);
	}

	
}
