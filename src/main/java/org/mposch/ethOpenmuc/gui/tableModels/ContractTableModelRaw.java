package org.mposch.ethOpenmuc.gui.tableModels;

import java.util.concurrent.ExecutionException;

import javax.swing.table.AbstractTableModel;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.generated.Uint256;

import rx.Observer;


/**
 * This class controls the raw data Table. 
 * 
 * @author mposch
 *
 */
@Component
public class ContractTableModelRaw extends AbstractTableModel implements Observer {
@Autowired
private ContractBean cb;
private String[] colNames ={"Index", "Raw Contract Data", "Storage Address"};
	@Override
	public int getRowCount() {
			return cb.getCacheSize();
	
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
		
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
			case 0: return  (rowIndex);
			case 1: return cb.getStringAtIndexCached(rowIndex); 
			case 2 : return cb.getAddressfromIndex(rowIndex);
			default: return null;
		}
	}
	@Override
	public void onCompleted() {
		// TODO Auto-generated method stub
		System.out.println("EVENT OBSERVED: onCompleted");
	}
	@Override
	public void onError(Throwable e) {
		// TODO Auto-generated method stub
		System.out.println("EVENT OBSERVED: onError");
	}

	@Override
	public void onNext(Object t) {
		// TODO Auto-generated method stub
		System.out.println("EVENT OBSERVED: onNext: "+t.toString());
	}


	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colNames[column];
	}
	
}