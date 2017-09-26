package org.mposch.ethOpenmuc.gui.tableModels;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

import org.mposch.ethOpenmuc.EthereumConversionRate.EthereumConversionRate;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author mposch
 *
 */
@Component
public class CurrencyComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {
	private HashMap<String, Double>	currencies;
	private ArrayList<String>		indexes;
	private int						selection;
@Autowired
private GuiController guiController;

	public CurrencyComboBoxModel() {
		this.currencies = new HashMap();
		this.indexes = new ArrayList();
		addCurrency("Wei", new Double(1));
		addCurrency("Ether", new Double(Math.pow(10, -18)));
		
}
	public void updateRatesBlocking() {
		try
		{
			RestTemplate r = new RestTemplate();
			EthereumConversionRate er = r.getForObject("https://api.kraken.com/0/public/Ticker?pair=ETHEUR,ETHUSD",
					EthereumConversionRate.class);
		
			Double eurEthRate = new Double (er.result.xETHZEUR.a.get(0));
			double weiRate = eurEthRate.doubleValue() * Math.pow(10, -18);
			addCurrency("EUR", new Double(weiRate));
			
			Double usdEthRate = new Double (er.result.xETHZUSD.a.get(0));
			weiRate = usdEthRate.doubleValue() * Math.pow(10, -18);
			addCurrency("USD", new Double(weiRate));
		}
		catch (Exception e)
		{
			guiController.Error("Could not fetch conversion Rates" + e.getMessage());
		}
	}

	public Double getRate (String key)
	{
		return this.currencies.get(key);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return currencies.size();
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selection = indexes.indexOf(anItem);
	}

	@Override
	public Object getSelectedItem() {
		return this.indexes.get(selection);
	}

	@Override
	public String getElementAt(int index) {
		return this.indexes.get(index);
	}

	public double getCurrentRate() {
		double retVal;
		retVal = currencies.get(indexes.get(selection)).doubleValue();
		return retVal;
	}

	public void addCurrency(String key, Double value) {
		this.currencies.put(key, value);
		if (!indexes.contains(key)) this.indexes.add(key);
	}
}
