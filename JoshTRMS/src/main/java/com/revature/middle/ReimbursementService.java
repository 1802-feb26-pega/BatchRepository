package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import com.revature.backend.ReimbStatus;
import com.revature.backend.ReimbType;
import com.revature.backend.Reimbursement;
import com.revature.backend.User;
import com.revature.data.Data;

public class ReimbursementService {
	public List<ReimbType> getTypes() throws SQLException {
		return new Data().getTypes();
	}
	
	public List<ReimbStatus> getStatus() throws SQLException {
		return new Data().getStatus();
	}

	public void updateStatus (Reimbursement reimb, User user, ReimbStatus status) throws Exception {
		new Data().updateStatus(reimb, user, status);
	}
	
	public Reimbursement insertReimb(User author, double amount, 
			ReimbType type,ReimbStatus status, String description) throws SQLException{
		return new Data().insertReimb(author, amount, type, status, description);
	}	

	public List<Reimbursement> getReimbs(User user) throws Exception {
		if(user.getRole_id().getUser_role().equals("Direct Supervisor"))
			return getAllReimbs();
		return getAuthorReimbs(user.getUser_id());
	}
		
	private List<Reimbursement> getAllReimbs() throws Exception{
		return  Data.getAllReimbs();
	}

	private List<Reimbursement> getAuthorReimbs(int author_id) throws Exception{
		return Data.getReimbByAuthor(author_id);
	}

	Reimbursement getReimbById(int reimb_id) throws SQLException {
		return Data.getReimbById(reimb_id);
	}

}

