package com.delectamentum.trms.repository;

import java.util.List;

import com.delectamentum.trms.document.Attachment;

public interface AttachmentRepository extends CRUDRepository<Attachment>{
	public List<Attachment> getByClaimId(int claimId);
}
