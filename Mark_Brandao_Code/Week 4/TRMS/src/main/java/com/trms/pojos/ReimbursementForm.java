package com.trms.pojos;

import java.sql.Date;
import java.sql.Timestamp;

public class ReimbursementForm {
	private int reimbursementId;
	private int employeeId;
	private int eventId;
	private Date dateSubmitted;
	private Timestamp timeSubmitted;
	private String justification;
	private int gradeFormatId;
	private int statusId;
	private int approvalid;
	private int timeMissed;
	private double totalCost;
	private double projectedReimbursement;
	private boolean urgent;
	private boolean exceedsValue;
	
	public ReimbursementForm() { }
	
	public ReimbursementForm(int reimbursementId, int employeeId, int eventId, Date dateSubmitted,
			Timestamp timeSubmitted, String justification, int gradeFormatId, int statusId, int approvalid,
			int timeMissed, double totalCost, double projectedReimbursement, boolean urgent, boolean exceedsValue) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.eventId = eventId;
		this.dateSubmitted = dateSubmitted;
		this.timeSubmitted = timeSubmitted;
		this.justification = justification;
		this.gradeFormatId = gradeFormatId;
		this.statusId = statusId;
		this.approvalid = approvalid;
		this.timeMissed = timeMissed;
		this.totalCost = totalCost;
		this.projectedReimbursement = projectedReimbursement;
		this.urgent = urgent;
		this.exceedsValue = exceedsValue;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}
	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public int getGradeFormatId() {
		return gradeFormatId;
	}
	public void setGradeFormatId(int gradeFormatId) {
		this.gradeFormatId = gradeFormatId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getApprovalid() {
		return approvalid;
	}
	public void setApprovalid(int approvalid) {
		this.approvalid = approvalid;
	}
	public int getTimeMissed() {
		return timeMissed;
	}
	public void setTimeMissed(int timeMissed) {
		this.timeMissed = timeMissed;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getProjectedReimbursement() {
		return projectedReimbursement;
	}
	public void setProjectedReimbursement(double projectedReimbursement) {
		this.projectedReimbursement = projectedReimbursement;
	}
	public boolean isUrgent() {
		return urgent;
	}
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
	public boolean isExceedsValue() {
		return exceedsValue;
	}
	public void setExceedsValue(boolean exceedsValue) {
		this.exceedsValue = exceedsValue;
	}
	@Override
	public String toString() {
		return "ReimbursementForm [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", eventId="
				+ eventId + ", justification=" + justification + ", gradeFormatId=" + gradeFormatId + ", statusId="
				+ statusId + ", approvalid=" + approvalid + ", timeMissed=" + timeMissed + ", totalCost=" + totalCost
				+ ", projectedReimbursement=" + projectedReimbursement + ", urgent=" + urgent + ", exceedsValue="
				+ exceedsValue + "]";
	}
}
