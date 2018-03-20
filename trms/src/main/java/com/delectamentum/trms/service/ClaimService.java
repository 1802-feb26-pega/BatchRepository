package com.delectamentum.trms.service;

import java.util.List;

import com.delectamentum.trms.document.Claim;
import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.repository.ClaimRepository;
import com.delectamentum.trms.repository.ClaimRepositoryImplSQL;

public class ClaimService {
	
	private static ClaimRepository claimRepo = new ClaimRepositoryImplSQL();
	
	public Claim denyRequest(int claimId, Employee reviewer) {
		
		Claim target = getClaimById(claimId);
		if(target == null)
			return null;
		else {
			target.setStatus(0);
			return claimRepo.update(target);
		}
	}
	
	public List<Claim> getApprovableRequests(Employee reviewer) {
		if(reviewer.getType() > 1)
			return claimRepo.getApprovableClaims(reviewer);
		else
			return null;
	}
	
	public Claim approveRequest(int claimId, Employee reviewer) {
		
		Claim target = getClaimById(claimId);
		if(target == null)
			return null;
		else {
			target.setStatus(reviewer.getType());
			return claimRepo.update(target);
		}
		
	}
	
	public Claim submitNewRequest(Claim c) {
		if(claimRepo.getById(c.getId()) == null)
			return claimRepo.save(c);
		else
			return null;
	}
	
	public List<Claim> getAllOwnClaims(Employee e) {
		return claimRepo.getClaimsByEmployee(e.getId());
	}
	
	public Claim getClaimById(int id) {
		return claimRepo.getById(id);
	}
	
}
