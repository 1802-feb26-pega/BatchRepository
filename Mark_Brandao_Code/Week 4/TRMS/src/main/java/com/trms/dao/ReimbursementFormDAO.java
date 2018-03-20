package com.trms.dao;

import java.util.List;

import com.trms.pojos.JoinedQuery;
import com.trms.pojos.ReimbursementForm;

public interface ReimbursementFormDAO {
	public List<ReimbursementForm> getReimsByEmployeeId(int employeeId);
	public List<ReimbursementForm> getReimsBySupervisees(int supervisorId);
	public List<ReimbursementForm> getReimsByDept(int deptHeadId);
	public List<ReimbursementForm> getReimsForBenCo(int bencoId);
	public ReimbursementForm addReim(ReimbursementForm rf);
	public ReimbursementForm updateReim(int rfid, ReimbursementForm rf);
	public List<JoinedQuery> getRequestsForApproval(int supervisorId);
}
