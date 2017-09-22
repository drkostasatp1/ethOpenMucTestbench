

package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"timestamp",
"flag",
"value"
})
public class Record {

@JsonProperty("timestamp")
protected Long timestamp;
@JsonProperty("flag")
protected String flag;
@JsonProperty("value")
protected String value;

/**
* No args constructor for use in serialization
* 
*/
public Record() {
	this.timestamp = System.currentTimeMillis();
	this.flag = "VALID";
	this.value="0";
}

/**
* 
* @param timestamp
* @param flag
* @param value
*/
public Record(Long timestamp, String flag, String value) {
super();
this.timestamp = timestamp;
this.flag = flag;
this.value = value;
}

@JsonProperty("timestamp")
public Long getTimestamp() {
return timestamp;
}

@JsonProperty("timestamp")
public void setTimestamp(Long timestamp) {
this.timestamp = timestamp;
}

@JsonProperty("flag")
public String getFlag() {
return flag;
}

@JsonProperty("flag")
public void setFlag(String flag) {
this.flag = flag;
}

@JsonProperty("value")
public String getValue() {
return value;
}

@JsonProperty("value")
public void setValue(String value) {
this.value = value;
}

@Override
public String toString() {
	return "Record [timestamp=" + timestamp + ", flag=" + flag + ", value=" + value + "]";
}


public String toJsonString()
{
	String result = "";
	ObjectMapper mapper = new ObjectMapper();
	StringWriter wr   = new StringWriter();
	try
	{
		mapper.writeValue(wr, this);
	}
	catch (IOException e)
	{
		System.out.println("Record.toJsonString()");
		System.out.println("Error creating Json String" + e.getMessage());
	} // where 'dst' can be File, OutputStream or Writer
	wr.flush();
	return wr.toString();
}

}