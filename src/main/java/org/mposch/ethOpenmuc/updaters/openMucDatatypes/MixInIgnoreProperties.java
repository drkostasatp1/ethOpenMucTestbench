package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class MixInIgnoreProperties {

	@JsonIgnore
	@JsonProperty("record")
	protected Record record;
	@JsonIgnore abstract String	getType();
	@JsonIgnore abstract String	setType();
	
	@JsonIgnore abstract Record getRecord();
	@JsonIgnore abstract Record setRecord();
	
}
