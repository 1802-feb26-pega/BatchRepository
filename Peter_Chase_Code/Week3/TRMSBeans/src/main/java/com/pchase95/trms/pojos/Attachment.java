package com.pchase95.trms.pojos;

import java.sql.Blob;

public class Attachment implements TrmsObject {
	private long id;
	private Reimbursment reimbursment;
	private Blob data;
	private String fileName;
	
	public Attachment() {
		
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", reimbursment=" + reimbursment + ", data=" + data + ", fileName=" + fileName
				+ "]";
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Reimbursment getReimbursment() {
		return reimbursment;
	}

	public void setReimbursment(Reimbursment reimbursment) {
		this.reimbursment = reimbursment;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
