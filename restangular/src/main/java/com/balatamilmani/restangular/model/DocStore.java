package com.balatamilmani.restangular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity

public class DocStore {

	@Id
	@SequenceGenerator(sequenceName="DOC_STORE_SEQ", name = "DOC_STORE_ID_GENERATOR")
	@GeneratedValue(generator="DOC_STORE_ID_GENERATOR", strategy=GenerationType.SEQUENCE)
	@Column(name="DOC_STORE_ID")
	private Long docStoreId;
	
	

	public byte[] getDocByte() {
		return docByte;
	}

	public Long getDocStoreId() {
		return docStoreId;
	}

	public void setDocStoreId(Long docStoreId) {
		this.docStoreId = docStoreId;
	}
	
	public void setDocByte(byte[] docByte) {
		this.docByte = docByte;
	}

	@Lob
	@Column(name="DOCUMENT")
	private byte[] docByte;
}
