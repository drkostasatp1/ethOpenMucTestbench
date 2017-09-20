package org.mposch.ethOpenmuc.updaters.openMucDatatypes;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecordWrapper {

	public RecordWrapper() {
		// TODO Auto-generated constructor stub
	}
@JsonProperty("record")
public Record record;

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
		System.out.println("RecordWrapper.toJsonString()");
		System.out.println("Error creating Json String" + e.getMessage());
	} // where 'dst' can be File, OutputStream or Writer
	wr.flush();
	return wr.toString();
}
}
