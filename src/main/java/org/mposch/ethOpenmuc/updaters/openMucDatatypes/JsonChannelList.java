package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"records"})
public class JsonChannelList {

@JsonProperty("records")
private List<Channel> records = null;

/**
* No args constructor for use in serialization
* 
*/
public JsonChannelList() {
}

/**
* 
* @param records
*/
public JsonChannelList(List<Channel> records) {
super();
this.records = records;
}

@JsonProperty("records")
public List<Channel> getChannels() {
return records;
}

@JsonProperty("records")
public void setRecords(List<Channel> records) {
this.records = records;
}

@Override
public String toString() {
	final int maxLen = 10;
	return "JsonChannelList [records=" + (records != null ? records.subList(0, Math.min(records.size(), maxLen)) : null)
			+ "]";
}



}