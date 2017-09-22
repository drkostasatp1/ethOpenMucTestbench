package org.mposch.ethOpenmuc.gui.tableModels;

import java.util.Date;
import java.util.Observable;

import javax.swing.table.AbstractTableModel;

import org.mposch.ethOpenmuc.ChannelState;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Channel;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class controls the raw data Table. This class shoudl be sble to react to
 * events emitted by the smart contract, Reducing the need to update all cells,
 * which requires a lot of time and makes the user interface unresponsive.
 * 
 * @author mposch
 *
 */
@Component
public class TableModelChannelState extends AbstractTableModel implements java.util.Observer {

	String					colNames[]	= { "Index", "Source", "Id", "Timestamp", "Value", "Sync to Blockchain",
			"Sync to OpenMuc", "Status", "Age[s]", "gas spent" };
	@Autowired
	private ContractBean	cb;
	@Autowired
	ChannelState			channelState;
	@Autowired
	private MainWindow		mainWindow;

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return channelState.getSize();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}

	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		// only rows that are created by the user (Source: Editor) can be edited

		if ((col == 5) || (col == 6)) return true;
		if (col == 4)
		{
			Channel c;
			c = channelState.getChannelAtIndex(row);
			if (c.getSource().equals("EDITOR") && (!c.getBlockchainStatus().equals("PENDING"))) return true;
		}
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Channel channel = channelState.getChannelAtIndex(rowIndex);
		if (channel == null) return null;
		Record record = channel.getRecord();
		if (record == null) return null;
		
		
		switch (columnIndex) {
			case 0:
				return rowIndex;
			case 1:
				return channel.getSource();
			case 2:
				return channel.getId();
			case 3:
				return new Date(record.getTimestamp()).toString();
			case 4:
				return record.getValue();
			case 5:
				return channel.isSyncToBlockchain();
			case 6:
				return channel.isSyncToOpenMuc();
			case 7:
				return channel.getBlockchainStatus();
			case 8: return (System.currentTimeMillis() - record.getTimestamp() ) / 1000;
			case 9: return channel.getGasSpent().toString();
			default:
				return null;
		}// end switch
	}

	/**
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 * int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 4:
				Channel ch = channelState.getChannelAtIndex(rowIndex);
				if (ch == null) return;
				Record record = ch.getRecord();
				if (record == null) return;
				record.setValue(aValue.toString());
				record.setTimestamp(System.currentTimeMillis());
				// Set the channel to be ready for broadcast
				ch.setBlockchainStatus("READY");
				break;
			case 5:
				channelState.getChannelAtIndex(rowIndex).setSyncToBlockchain((boolean) aValue);
				break;
			case 6:
				channelState.getChannelAtIndex(rowIndex).setSyncToOpenMuc((boolean) aValue);
				break;
			// case 7:
			// channelState.getChannelAtIndex(rowIndex).setBlockchainStatus(
			// (String) aValue);
		}// end switch
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colNames[column];
	}

	@Override
	public void update(Observable o, Object arg) {
		this.fireTableDataChanged();
	}

	@Override
	public void fireTableDataChanged() {
		// TODO Auto-generated method stub
		if (mainWindow.getChannelStateTable().isEditing() == false) super.fireTableDataChanged();
	}

}
