package com.revature.trms.dao;

import java.util.List;

import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;

public interface ReimbursementRequestDAO {

	public ReimbursementRequest addReimbursementRequest(ReimbursementRequest request);
	public List<ReimbursementRequest> getAllPendingRequests(Employee emp);
	public List<ReimbursementRequest> getAllRequestsForReviewBySuper(Employee emp);
	public ReimbursementRequest getReimbursementRequestById(int id);
	public ReimbursementRequest updateReimbursementRequest(ReimbursementRequest updatedRequest);
}
