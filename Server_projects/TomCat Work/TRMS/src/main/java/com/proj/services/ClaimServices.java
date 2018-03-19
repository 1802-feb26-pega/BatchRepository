package com.proj.services;

import java.util.ArrayList;

import com.proj.pojos.Claim;
import com.proj.pojos.Employee;
import com.proj.pojos.Note;

public class ClaimServices {

	ClaimDao claim_dao = new ClaimDao();
	Employee employee = new Employee();
	Note note = new Note();

	public boolean addClaim(Claim claim,Employee employee) {
			return claim_dao.addClaim(claim,employee);
	}

	public ArrayList<Claim> getClaims(Employee emp) {		
		// TODO Auto-generated method stub
		return claim_dao.getClaims(emp);
	}

}
