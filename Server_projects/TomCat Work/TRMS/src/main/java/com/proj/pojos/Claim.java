package com.proj.pojos;

import java.sql.Blob;

public class Claim {
	private int claim_id;
	private String status;
	private String created;
	private String eventStartdate;//date
	private double amount_given;
	private int emp_id;
	private String loc;
	private String event_type;
	private double cost;
	private String reason;
	private Blob attachment;
	private String eventStarttime;//time
	private int note_id;
	private int passing;
	private int gradingFormat;
	private int daysmissed;
	private String description;
	private String comments;


	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getDaysmissed() {
		return daysmissed;
	}
	public void setDaysmissed(int daysmissed) {
		this.daysmissed = daysmissed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getGradingFormat() {
		return gradingFormat;
	}
	public void setGradingFormat(int gradingFormat) {
		this.gradingFormat = gradingFormat;
	}
	public int getPassing() {
		return passing;
	}
	public void setPassing(int passing) {
		this.passing = passing;
	}
	public int getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

	public String getEventStartdate() {
		return eventStartdate;
	}
	public void setEventStartdate(String eventStartdate) {
		this.eventStartdate = eventStartdate;
	}
	public String getEventStarttime() {
		return eventStarttime;
	}
	public void setEventStarttime(String eventstarttime) {
		this.eventStarttime = eventstarttime;
	}
	public double getAmount_given() {
		return amount_given;
	}
	public void setAmount_given(double amount_given) {
		this.amount_given = amount_given;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}



	public Blob getAttachment() {
		return attachment;
	}



	public void setAttachment(Blob blob) {
		this.attachment = blob;
	}






}
