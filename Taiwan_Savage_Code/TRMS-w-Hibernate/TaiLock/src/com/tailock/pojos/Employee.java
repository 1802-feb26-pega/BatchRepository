package com.tailock.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	@Id @GeneratedValue
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="username")
	private String empUsername;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="street_name")
	private String street;
	
	@Column(name="email")
	private String empEmail;
	
	/**
	 * 
	 * Establish an EMPLOYEETITLE lookup so that we can see the hierarchy of approvals
	 * 
	 */
	
}
