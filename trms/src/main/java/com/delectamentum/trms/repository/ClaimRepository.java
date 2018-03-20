package com.delectamentum.trms.repository;

import java.util.List;

import com.delectamentum.trms.document.Claim;
import com.delectamentum.trms.document.Employee;

public interface ClaimRepository extends CRUDRepository<Claim> {

	public List<Claim> getClaimsByEmployee(int employeeId);
	public List<Claim> getApprovableClaims(Employee e);
	
}
