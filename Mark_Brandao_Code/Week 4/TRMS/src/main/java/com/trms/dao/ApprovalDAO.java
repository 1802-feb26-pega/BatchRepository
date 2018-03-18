package com.trms.dao;

import java.util.List;

import com.trms.pojos.Approval;

public interface ApprovalDAO {
	public Approval getApprovalById(int approvalId);
	public Approval addApproval(Approval newApproval);
	public int updateApproval(int approvalId, Approval updatedApproval);
	public int removeApproval(int approvalId);
	public List<Approval> getAllApprovalsByReimbursementId(int reimbursementId);
	public Approval getMostRecentApprovalByReimbursementId(int reimbursementId);
	public List<Approval> getAllApprovalsByApproverId(int employeeId);
}
