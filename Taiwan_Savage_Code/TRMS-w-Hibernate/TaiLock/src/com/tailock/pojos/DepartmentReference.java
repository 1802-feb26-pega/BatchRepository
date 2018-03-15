package com.tailock.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT_REFERENCE")
public class DepartmentReference {

	@Id @GeneratedValue
	@Column(name = "department_id")
	private int departmentID;
	
	//We need to know who the head is because if this person is reached we don't need to go further
	@OneToOne
	@JoinColumn(name = "department_head")
	private int departmentHead;
	
	//the string name of the department
	@Column(name = "department_name")
	private String departmentName;
}
