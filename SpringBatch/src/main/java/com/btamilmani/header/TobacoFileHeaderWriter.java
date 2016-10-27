package com.btamilmani.header;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

public class TobacoFileHeaderWriter implements FlatFileHeaderCallback{

	private String header;
	
	
	@Override
	public void writeHeader(Writer writer) throws IOException {
		writer.write(header);
		
	}


	public void setHeader(String header) {
		this.header = header;
	}

}
