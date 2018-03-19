package com.trms.pojos;

public class Reimbursement {
	private int reId;
	private int formId;
	private int employeeId;
	private double ammount_paid;
	private String status;
	private String requestDate;
	
	public Reimbursement(int reId, int formId, int employeeId, 
			double ammount_paid, String status, String requestDate) {
		this.reId = reId;
		this.formId = formId;
		this.employeeId = employeeId;
		this.ammount_paid = ammount_paid;
		this.status = status;
		this.requestDate = requestDate;
	}

	public Reimbursement() {
		super();
		this.reId = -1;
		this.formId = -1;
		this.employeeId =-1;
		this.ammount_paid = -1;
		this.status = "";
		this.requestDate = "";
	}

	public int getReId() {
		return reId;
	}

	public void setReId(int reId) {
		this.reId = reId;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAmmount_paid() {
		return ammount_paid;
	}

	public void setAmmount_paid(double ammount_paid) {
		this.ammount_paid = ammount_paid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		return "Reimbursement [reId=" + reId + ", formId=" + formId + ", employeeId=" + employeeId + ", ammount_paid="
				+ ammount_paid + ", status=" + status + ", requestDate=" + requestDate + "]";
	}

	

}
