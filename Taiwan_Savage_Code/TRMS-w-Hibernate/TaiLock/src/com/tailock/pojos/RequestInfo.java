package com.tailock.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_INFO")
public class RequestInfo {

	@Id @GeneratedValue
	@Column(name = "req_info_id")
	private int reqInfoId;
	
	@OneToOne
	@JoinColumn(name = "request_id")
	private int requestID;
	
	/*
	 * We need to get information about who sent the request and who it is going to
	 */
	@ManyToOne
	@JoinColumn(name = "emp_requestor")
	private Employee requestor_employee; //employee that made the request
	
	@ManyToOne
	@JoinColumn(name = "emp_request_to")
	private Employee requested_employee; //employee that the request is being sent to
	
	
	
}
