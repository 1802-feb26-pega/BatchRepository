package com.tailock.pojos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/**
 * 
 * @author taikwando
 *
 *	The Reimbursement Request table will house various information surrounded the request
 *
 *	Look up tables are crucial for this project so many of the more complex fields are
 *	pulled from them (grade format, event type, priority, and current status)
 *
 *	There should be a many to one relationship between RR and the Employee table as each
 *	employee can have multiple requests.
 *
 *	The date and location of the request will be stored
 *		I need to make sure that the date object in java will convert over to sql nicely
 *		via hibernate
 *
 *	
 *
 */
@Entity
@Table(name="REIMBURSEMENT_REQUEST")
public class ReimbursementRequest {
	
	@Id @GeneratedValue
	@Column(name="request_id")
	private int requestId;
	
	//many requests per employee
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	/*
	 * because a request can either be urgent or not urgent, we need to track the start date of
	 * the course
	 */
	@Column(name = "course_start_date")
	private Date startDate;
	
	//this uses type date, will test later
	@Column(name = "date_of_request")
	@Type(type="date")
	private Date dateOfRequest;
	
	@Column(name = "location_of_request")
	private String locationOfRequest;
	
	//employees must add a description their request
	@Column(name = "request_desc")
	private String requestDescription;
	
	//employee must specify the cost
	@Column(name = "cost")
	private int cost;
	
	//this describes the total number of days missed
	@Column(name = "time_missed")
	private int daysMissed;
	
	//if request is denied, denier must provide a note on why they denied it
	//also used if BenCo provides more funds than available for an employee
	@Column(name = "added_note")
	private String addedNote = "n/a";
	
	/*
	 * 
	 * there is a formula for calculating the projected reimbursement that depends on 
	 * the course type.
	 * 
	 * The amount that will actually be awarded can also be affected by the amount available
	 * 
	 */
	@Column(name = "reim_amount")
	private int reimAmount;
	
	
	/**
	 * there needs to be a few reference tables for some of the columns needed in this table
	 * 
	 *  ADD LATER
	 */
	
}
