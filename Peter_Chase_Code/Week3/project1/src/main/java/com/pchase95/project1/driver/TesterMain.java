package com.pchase95.project1.driver;

import java.util.List;

import com.pchase95.project1.dao.EmployeeRBMTDao;
import com.pchase95.project1.pojos.Reimbursment;

public class TesterMain {
	public static void main(String[] args) {
		
		EmployeeRBMTDao dao = new EmployeeRBMTDao();
		
		List<Reimbursment> results = dao.getRBMTByEmployeeId(4);
		
		for (Reimbursment e : results) {
			System.out.println(e);
		}
	}
}
