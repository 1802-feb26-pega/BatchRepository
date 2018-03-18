package com.trms.dao;

import java.util.List;

import com.trms.pojos.Attachment;

public interface AttachmentDAO {
	public Attachment getAttachmentById(int attachmentId);
	public List<Attachment> getAllAttachmentsByReimbursementId(int reimbursementId);
	public int addAttachment(Attachment newAttachment);
	public int updateAttachment(int attachmentId, Attachment updatedAttachment);
}
