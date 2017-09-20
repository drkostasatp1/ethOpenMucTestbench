package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a helper CLass that will return the stored value as double. Necessary for writing channels to openMuc
 * 
 * @author mposch
 *
 */
public class RecordDouble extends Record {

	public RecordDouble() {
		// TODO Auto-generated constructor stub
	}
	public RecordDouble(Record re)
	{
		this.value = re.getValue();
		this.timestamp = re.getTimestamp();
		this.flag = re.getFlag();
	}
	
	public RecordDouble(Long timestamp, String flag, String value) {
		super(timestamp, flag, value);
		// TODO Auto-generated constructor stub
	}
	
	@JsonProperty("value")	
	public double getValueDouble()
	{
		double retVal = new Double(this.value).doubleValue();
		return retVal;
	}
	public void setValue (double val)
	{
		this.value = Double.toString(val);
	}
}
