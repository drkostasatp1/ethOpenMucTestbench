
package org.mposch.ethOpenmuc.gui.tableModels;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import javax.swing.table.AbstractTableModel;

import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Channel;
import org.mposch.solidityContractWrapper.RecordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class contractTableModel extends AbstractTableModel {

	private String[]		colNames	= { "Channel ID", "Value"};
	@Autowired
	private ContractBean	contractBean;

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		try
		{
			Uint256 retval = contractBean.getContract().getCount().get();
			return retval.getValue().intValue();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		// If something goes wrong, return 1
		return 1;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String json = "";
		// Get the appropriate data entry by evaluating the stored JSON
		// information within the contract
		if (contractBean.isValid() == false) return null;
		
		try
		{
			if (contractBean.isValid() == false) throw new Exception("Contract not Valid!");
			Address addr = contractBean.getContract().getStringAtIndex(new Uint256(columnIndex)).get();
			
			json = contractBean.getContract().getString(addr).get().toString();
			Channel listEntry;
			ObjectMapper mapper = new ObjectMapper();

			listEntry = mapper.readValue(json, Channel.class);

			switch (columnIndex) {
				case 0:
					return listEntry.getId();
				case 1:
					return listEntry.getRecord().getValue();
			}

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.println("ERROR UPDATING TABLE:  "+ e.getMessage());
		}
		return "Exception Raised, Probably Contract not present?";
	}
	public Class getColumnClass(int c) {
        return String.class;
    }

}
