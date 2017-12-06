package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "value" })
@JsonIgnoreProperties({"record", "type"})
//@JsonAutoDetect(
//	    fieldVisibility = Visibility.NONE,
//	    setterVisibility = Visibility.NONE,
//	    getterVisibility = Visibility.NONE,
//	    isGetterVisibility = Visibility.NONE,
//	    creatorVisibility = Visibility.NONE
//	)
/**
 * This class stores Channel Values in a simplified manner, to save transaction Costs on the Blockchain
 * 
 * @author mposch
 *
 */
public class simpleChannel extends Channel {

	public simpleChannel() {
		this.record = new Record();
		
		}
	public simpleChannel (Channel c)
	{
		this.setRecord(c.getRecord());
		this.setId(c.getId());
		this.setBlockchainStatus(c.getBlockchainStatus());
		this.setPriority(c.getPriority());
		this.setSource(c.getSource());
	}
	public simpleChannel(String id, String type, Record record) {
		super(id, type, record);
		// TODO Auto-generated constructor stub
	}
	/**
	 * These Methods work differently now, because only values are stored. 
	 */
	@JsonProperty("value")
	public double getValue() {
		return Double.valueOf(this.record.getValue()).doubleValue();
	}
	@JsonProperty("value")
	public void setValue(double value) {
		record.setValue(String.valueOf(value));
	}
	/**
	 * Returns a JsonString by using the fasterxml object mapper
	 */
	@Override
	public String toJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		{
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(this);
			return json;
			
		}
	}
	
}
