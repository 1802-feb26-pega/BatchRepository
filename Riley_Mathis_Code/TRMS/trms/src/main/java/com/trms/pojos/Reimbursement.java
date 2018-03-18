package com.trms.pojos;

public class Reimbursement {
	private int reId;
	private int employeeId;
	private int eventId;
	private String justification;
	private int superApp;
	private int depHeadApp;
	private int benCoApp;
	private int requestedAmount;
	private int alternateAmount;
	private String reStatus;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int employeeId, int eventId, String justification, int superApp, int depHeadApp, int benCoApp,
			int requestedAmount) {
		super();
		this.employeeId = employeeId;
		this.eventId = eventId;
		this.justification = justification;
		this.superApp = superApp;
		this.depHeadApp = depHeadApp;
		this.benCoApp = benCoApp;
		this.requestedAmount = requestedAmount;
	}
	public Reimbursement(int employeeId, int eventId, String justification, int requestedAmount) {
		super();
		this.employeeId = employeeId;
		this.eventId = eventId;
		this.justification = justification;
		this.requestedAmount = requestedAmount;
	}
	public int getReId() {
		return reId;
	}
	public void setReId(int reId) {
		this.reId = reId;
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
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public int getSuperApp() {
		return superApp;
	}
	public void setSuperApp(int superApp) {
		this.superApp = superApp;
	}
	public int getDepHeadApp() {
		return depHeadApp;
	}
	public void setDepHeadApp(int depHeadApp) {
		this.depHeadApp = depHeadApp;
	}
	public int getBenCoApp() {
		return benCoApp;
	}
	public void setBenCoApp(int benCoApp) {
		this.benCoApp = benCoApp;
	}
	public int getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(int requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public int getAlternateAmount() {
		return alternateAmount;
	}
	public void setAlternateAmount(int alternateAmount) {
		this.alternateAmount = alternateAmount;
	}
	public String getReStatus() {
		return reStatus;
	}
	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alternateAmount;
		result = prime * result + benCoApp;
		result = prime * result + depHeadApp;
		result = prime * result + employeeId;
		result = prime * result + eventId;
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + reId;
		result = prime * result + ((reStatus == null) ? 0 : reStatus.hashCode());
		result = prime * result + requestedAmount;
		result = prime * result + superApp;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (alternateAmount != other.alternateAmount)
			return false;
		if (benCoApp != other.benCoApp)
			return false;
		if (depHeadApp != other.depHeadApp)
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (eventId != other.eventId)
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (reId != other.reId)
			return false;
		if (reStatus == null) {
			if (other.reStatus != null)
				return false;
		} else if (!reStatus.equals(other.reStatus))
			return false;
		if (requestedAmount != other.requestedAmount)
			return false;
		if (superApp != other.superApp)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reId=" + reId + ", employeeId=" + employeeId + ", eventId=" + eventId
				+ ", justification=" + justification + ", superApp=" + superApp + ", depHeadApp=" + depHeadApp
				+ ", benCoApp=" + benCoApp + ", requestedAmount=" + requestedAmount + ", alternateAmount="
				+ alternateAmount + ", reStatus=" + reStatus + "]";
	}
	
	
}
