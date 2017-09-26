package org.mposch.ethOpenmuc.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.tableModels.CurrencyComboBoxModel;
import org.mposch.ethOpenmuc.updaters.RateUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.swing.JTextField;
import java.awt.Font;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
@Component
public class GasCounter  {

	private JFrame frmGasCounter;
	private JPanel contentPane;
	private JTextField txtGasSpent;
	private BigInteger gasSpent;
	private JScrollPane scrollPane;
	private JTextArea txtTransactionLog;
	private JTextField textFieldEther;
	private JTextField textFieldEuro;
	private JLabel lblGas;
	private JLabel lblEther;
	private JLabel lblEuro;
	
	private double gasPrice = 0;
	ArrayList<Double> gasExpenses;
	
	@Autowired private CurrencyComboBoxModel currencyComboBoxModel;
	private JLabel lblMedianGas;
	private JTextField txtMediangas;
	private JLabel lblGasPrice;
	private JTextField txtGasPrice;
	
	/**
	 * Create the frame.
	 */
	
	
	public GasCounter() {
	
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize()
	{
		gasExpenses = new ArrayList();
		gasSpent = BigInteger.ZERO;
		frmGasCounter = new JFrame();
		
		frmGasCounter.setTitle("GAS COUNTER");
		frmGasCounter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGasCounter.setBounds(100, 100, 1004, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmGasCounter.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{685, 117, 244, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 70, 101, 268, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		frmGasCounter.setVisible(true);
		
		lblGas = new JLabel("Totel gas usage");
		GridBagConstraints gbc_lblGas = new GridBagConstraints();
		gbc_lblGas.insets = new Insets(0, 0, 5, 5);
		gbc_lblGas.anchor = GridBagConstraints.EAST;
		gbc_lblGas.gridx = 1;
		gbc_lblGas.gridy = 0;
		contentPane.add(lblGas, gbc_lblGas);
		txtGasSpent = new JTextField();
		txtGasSpent.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		txtGasSpent.setEditable(false);
		txtGasSpent.setText("gas Spent");
		GridBagConstraints gbc_txtGasSpent = new GridBagConstraints();
		gbc_txtGasSpent.insets = new Insets(0, 0, 5, 0);
		gbc_txtGasSpent.anchor = GridBagConstraints.NORTH;
		gbc_txtGasSpent.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGasSpent.gridx = 2;
		gbc_txtGasSpent.gridy = 0;
		contentPane.add(txtGasSpent, gbc_txtGasSpent);
		txtGasSpent.setColumns(10);
		txtGasSpent.setText(gasSpent.toString());
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtTransactionLog = new JTextArea();
		txtTransactionLog.setText("Transaction Log");
		txtTransactionLog.setColumns(1);
		scrollPane.setViewportView(txtTransactionLog);
		
		lblGasPrice = new JLabel("Gas Price");
		GridBagConstraints gbc_lblGasPrice = new GridBagConstraints();
		gbc_lblGasPrice.anchor = GridBagConstraints.EAST;
		gbc_lblGasPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblGasPrice.gridx = 1;
		gbc_lblGasPrice.gridy = 1;
		contentPane.add(lblGasPrice, gbc_lblGasPrice);
		
		txtGasPrice = new JTextField();
		txtGasPrice.setEditable(false);
		txtGasPrice.setText("gas Price");
		GridBagConstraints gbc_txtGasPrice = new GridBagConstraints();
		gbc_txtGasPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtGasPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGasPrice.gridx = 2;
		gbc_txtGasPrice.gridy = 1;
		contentPane.add(txtGasPrice, gbc_txtGasPrice);
		txtGasPrice.setColumns(10);
		
		lblMedianGas = new JLabel("Median Gas");
		GridBagConstraints gbc_lblMedianGas = new GridBagConstraints();
		gbc_lblMedianGas.anchor = GridBagConstraints.EAST;
		gbc_lblMedianGas.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedianGas.gridx = 1;
		gbc_lblMedianGas.gridy = 2;
		contentPane.add(lblMedianGas, gbc_lblMedianGas);
		
		txtMediangas = new JTextField();
		txtMediangas.setEditable(false);
		txtMediangas.setText("medianGas");
		GridBagConstraints gbc_txtMediangas = new GridBagConstraints();
		gbc_txtMediangas.insets = new Insets(0, 0, 5, 0);
		gbc_txtMediangas.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMediangas.gridx = 2;
		gbc_txtMediangas.gridy = 2;
		contentPane.add(txtMediangas, gbc_txtMediangas);
		txtMediangas.setColumns(10);
		
		lblEther = new JLabel("Ether");
		GridBagConstraints gbc_lblEther = new GridBagConstraints();
		gbc_lblEther.insets = new Insets(0, 0, 5, 5);
		gbc_lblEther.anchor = GridBagConstraints.EAST;
		gbc_lblEther.gridx = 1;
		gbc_lblEther.gridy = 3;
		contentPane.add(lblEther, gbc_lblEther);
		
		textFieldEther = new JTextField();
		textFieldEther.setEditable(false);
		GridBagConstraints gbc_textFieldEther = new GridBagConstraints();
		gbc_textFieldEther.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEther.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEther.gridx = 2;
		gbc_textFieldEther.gridy = 3;
		contentPane.add(textFieldEther, gbc_textFieldEther);
		textFieldEther.setColumns(10);
		
		lblEuro = new JLabel("Euro");
		GridBagConstraints gbc_lblEuro = new GridBagConstraints();
		gbc_lblEuro.insets = new Insets(0, 0, 0, 5);
		gbc_lblEuro.anchor = GridBagConstraints.EAST;
		gbc_lblEuro.gridx = 1;
		gbc_lblEuro.gridy = 4;
		contentPane.add(lblEuro, gbc_lblEuro);
		
		textFieldEuro = new JTextField();
		textFieldEuro.setEditable(false);
		GridBagConstraints gbc_textFieldEuro = new GridBagConstraints();
		gbc_textFieldEuro.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEuro.gridx = 2;
		gbc_textFieldEuro.gridy = 4;
		contentPane.add(textFieldEuro, gbc_textFieldEuro);
		textFieldEuro.setColumns(10);
		
	}
	
	
	
	
	public void addGas(TransactionReceipt tr)
	{
		Date now = new Date();
		this.addGas(tr.getGasUsed());
		
		try
		{
			double euroRate = currencyComboBoxModel.getRate("EUR").doubleValue();
			// convert from wei to ether
			double gasPriceEther = this.gasPrice * Math.pow(10, -18);
			double etherSpent = this.gasSpent.longValue() * gasPriceEther;
			double euroSpent = etherSpent*euroRate;
			textFieldEther.setText(Double.toString(etherSpent));
			textFieldEuro.setText(Double.toString(euroSpent));
			
			this.gasExpenses.add(tr.getGasUsed().doubleValue());
			txtMediangas.setText( Double.toString(median()) );
			txtGasPrice.setText(Double.toString(this.gasPrice));
			
		}
		catch (Exception e)
		{
			
		}
		
		
		this.txtTransactionLog.append("\n"+now.toString()+", Gas Usage: "+ tr.getGasUsed().toString());
		
	}
	private void addGas(BigInteger gas)
	{
		this.gasSpent =  gasSpent.add(gas);
		this.txtGasSpent.setText(this.gasSpent.toString());
	}
	public double getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(double gasPrice) {
		this.gasPrice = gasPrice;
	}
	public double median() 
    {
       	gasExpenses.sort(null);
		       if (gasExpenses.size() % 2 == 0) 
       {
          return (( gasExpenses.get( (gasExpenses.size()/2)-1) + gasExpenses.get(gasExpenses.size()/2)  )/ 2.0);
       } 
       return gasExpenses.get(gasExpenses.size() / 2);
    }
}
