package com.trms.pojos;

import java.sql.Blob;

public class Attachment {
	private int attachmentId;
	private int reimbursementId;
	private String attachmentDescription;
	private Blob file;
	private String fileType;
	private String filename;
	
	public Attachment() { }
	
	public Attachment(int attachmentId, int reimbursementId, String attachmentDescription, Blob file, String fileType,
			String filename) {
		super();
		this.attachmentId = attachmentId;
		this.reimbursementId = reimbursementId;
		this.attachmentDescription = attachmentDescription;
		this.file = file;
		this.fileType = fileType;
		this.filename = filename;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "Attachment [attachmentId=" + attachmentId + ", reimbursementId=" + reimbursementId
				+ ", attachmentDescription=" + attachmentDescription + ", file=" + file + ", fileType=" + fileType
				+ ", filename=" + filename + "]";
	}
	
	
}
