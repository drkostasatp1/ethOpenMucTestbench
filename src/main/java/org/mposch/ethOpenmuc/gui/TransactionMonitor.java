package org.mposch.ethOpenmuc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
@Component
public class TransactionMonitor {

	private JFrame frmTransactionMonitor;
	private JTable table;



	/**
	 * Create the application.
	 */
	public TransactionMonitor() {
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmTransactionMonitor = new JFrame();
		frmTransactionMonitor.setTitle("Transaction Monitor");
		frmTransactionMonitor.setVisible(true);
		frmTransactionMonitor.setBounds(100, 100, 450, 300);
		frmTransactionMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransactionMonitor.getContentPane().setLayout(new BoxLayout(frmTransactionMonitor.getContentPane(), BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		frmTransactionMonitor.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JTable getTransactionTable() {
		return table;
	}
}
