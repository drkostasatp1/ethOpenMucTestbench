package org.mposch.ethOpenmuc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChannelDialog extends JDialog {

private final JPanel contentPanel = new JPanel();
private JTextField txtChannelName;
private JTextField txtValue;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try
//		{
//			ChannelDialog dialog = new ChannelDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ChannelDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{159, 0, 130, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{26, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			txtChannelName = new JTextField();
			txtChannelName.setText("Channel Name");
			txtChannelName.setColumns(10);
		}
		GridBagConstraints gbc_txtChannelName = new GridBagConstraints();
		gbc_txtChannelName.insets = new Insets(0, 0, 5, 5);
		gbc_txtChannelName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtChannelName.gridwidth = 3;
		gbc_txtChannelName.anchor = GridBagConstraints.NORTH;
		gbc_txtChannelName.gridx = 0;
		gbc_txtChannelName.gridy = 1;
		contentPanel.add(txtChannelName, gbc_txtChannelName);
		{
			txtValue = new JTextField();
			txtValue.setText("Value");
			GridBagConstraints gbc_txtValue = new GridBagConstraints();
			gbc_txtValue.insets = new Insets(0, 0, 0, 5);
			gbc_txtValue.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtValue.gridx = 0;
			gbc_txtValue.gridy = 3;
			contentPanel.add(txtValue, gbc_txtValue);
			txtValue.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
