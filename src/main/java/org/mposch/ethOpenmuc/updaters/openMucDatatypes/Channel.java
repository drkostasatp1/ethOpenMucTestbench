package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mposch
 *  This classrepresents a Channel within OpenMuc. Actual values are containes within the Record property
 * 
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "type", "record" })


public class Channel {

	@JsonProperty("id")
	protected String	id;
	@JsonProperty("type")
	private String	type;
	@JsonProperty("record")
	protected Record record;
	@JsonIgnore
	protected boolean syncToBlockchain;
	@JsonIgnore
	protected boolean syncToOpenMuc;
	// Probably use this field if conflicting channel ids are present..
	@JsonIgnore
	protected int priority;
	@JsonIgnore
	protected String source;
	@JsonIgnore
	protected String blockchainStatus = "";
	@JsonIgnore
	protected int failedTransacitons = 0;
	@JsonIgnore
	protected BigInteger gasSpent;
	/**
	 * No args constructor for use in serialization
	 * 
	 */
public Channel()
{
	super();
	
}
	/**
	 * 
	 * @param id
	 * @param record
	 * @param type
	 */
	public Channel(String id, String type, Record record) {
		super();
		this.id = id;
		this.type = type;
		this.record = record;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("record")
	public Record getRecord() {
		return record;
	}
	@JsonProperty("record")
	public void setRecord(Record record) {
		this.record = record;
	}

	public String toJsonString() throws JsonGenerationException, JsonMappingException, IOException
	{
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		StringWriter wr   = new StringWriter();
		mapper.writeValue(wr, this); // where 'dst' can be File, OutputStream or Writer
		wr.flush();
		return wr.toString();
	}

	public boolean isSyncToBlockchain() {
		return syncToBlockchain;
	}
	public boolean isSyncToOpenMuc() {
		return syncToOpenMuc;
	}

public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
@Override
public String toString() {
	return "Channel [id=" + id + ", type=" + type + ", record=" + record + ", syncToBlockchain=" + syncToBlockchain
			+ ", syncToOpenMuc=" + syncToOpenMuc + ", priority=" + priority + ", source=" + source + "]";
}
public void setSyncToBlockchain(boolean syncToBlockchain) {
	this.syncToBlockchain = syncToBlockchain;
}
public void setSyncToOpenMuc(boolean syncToOpenMuc) {
	this.syncToOpenMuc = syncToOpenMuc;
}
public String getBlockchainStatus() {
	return blockchainStatus;
}
public void setBlockchainStatus(String blockchainStatus) {
	this.blockchainStatus = blockchainStatus;
}
public int getFailedTransacitons() {
	return failedTransacitons;
}
public void setFailedTransacitons(int failedTransacitons) {
	this.failedTransacitons = failedTransacitons;
}
public BigInteger getGasSpent() {
	if (this.gasSpent != null) return gasSpent;
	else
		return BigInteger.ZERO;
}
public void setGasSpent(BigInteger gasSpent) {
	this.gasSpent = gasSpent;
}
public void accumulateGasSpent(BigInteger gasSpent)
{
	if (this.gasSpent == null) this.gasSpent = BigInteger.ZERO;
	this.gasSpent = this.gasSpent.add(gasSpent);
}
}