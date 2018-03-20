package com.trms.dao;

import com.trms.pojos.Reimbursement;

public interface ReimbursementDAO {
	
	public Reimbursement addReimbursement(int employeeId, int eventId, String justification, 
			int superApp, int depheadApp, int bencoApp, int requestedAmount);
	
}
