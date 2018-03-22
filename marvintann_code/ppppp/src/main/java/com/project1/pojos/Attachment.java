package com.project1.pojos;

import java.sql.Blob;

public class Attachment {
	private int attachmentId;
	private int requestId;
	private Blob attachment;
	
	public Attachment() {
		
	}
	
	public Attachment(int attachmentId, int requestId, Blob attachment) {
		super();
		this.attachmentId = attachmentId;
		this.requestId = requestId;
		this.attachment = attachment;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public Blob getAttachment() {
		return attachment;
	}
	public void setAttachment(Blob blob) {
		this.attachment = blob;
	}
	
}
