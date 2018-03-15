package com.tailock.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author taikwando
 *
 *
 *	this table will provide reimbursement information to the employee
 *		!!NOT ABOUT A SPECIFIC REIMBURSEMENT BUT ABOUT ABOUT ALL REIMBURSEMENTS!!
 *		CAN HAVE A MAX OF $1000 PER YEAR SO NEEDS TO BE UPDATED!!!!
 */

@Entity
@Table(name = "EMP_REIM_INFO")
public class EmployeeReimbursementInfo {
	
	@Id @GeneratedValue
	@Column(name = "ERI_id")
	private int ERIId;
	
	//how much does the employee have remaining?
	@Column(name = "remaining")
	private int remaining;
	
	/*
	 * 
	 * how much is currently waiting on approval?
	 * need to update this when it gets approved or denied.
	 * 
	 */
	@Column(name = "amount_pending")
	private int amountPending;
	
	//show how much has already been awarded
	@Column(name = "amount_awarded")
	private int amountAwarded;
}
