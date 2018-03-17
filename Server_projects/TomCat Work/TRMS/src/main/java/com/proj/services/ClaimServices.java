package com.proj.services;

import com.proj.pojos.Claim;
import com.proj.pojos.Employee;
import com.proj.pojos.Note;

public class ClaimServices {

	ClaimDao claim_dao = new ClaimDao();
	Employee employee = new Employee();
	Note note = new Note();

	public boolean addClaim(Claim claim) {
			System.out.println("INSIDE SERVICES METHOD");
			return claim_dao.addClaim(claim);
	}

}
