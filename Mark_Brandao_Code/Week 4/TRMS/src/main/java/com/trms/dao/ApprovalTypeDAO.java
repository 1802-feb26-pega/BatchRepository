package com.trms.dao;

import com.trms.pojos.Approval;
import com.trms.pojos.ApprovalType;

public interface ApprovalTypeDAO {
	public ApprovalType getApprovalTypeById(int approvalTypeId);
	public ApprovalType getApprovalTypeByApproval(Approval approval);
}
