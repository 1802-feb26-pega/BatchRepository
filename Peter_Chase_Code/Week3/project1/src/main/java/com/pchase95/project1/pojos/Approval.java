package com.pchase95.project1.pojos;

public class Approval implements TrmsObject {
	private long id;
	private Reimbursment reimbursment;
	private Employee approver;
	private ApprovalType type;
	private Employee recipient;
	private String reason;
	
	public Approval() {
		
	}
	
	
	
	@Override
	public String toString() {
		return "Approval [id=" + id + ", reimbursment=" + reimbursment + ", approver=" + approver + ", type=" + type
				+ ", recipient=" + recipient + ", reason=" + reason + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((approver == null) ? 0 : approver.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result + ((reimbursment == null) ? 0 : reimbursment.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Approval other = (Approval) obj;
		if (approver == null) {
			if (other.approver != null)
				return false;
		} else if (!approver.equals(other.approver))
			return false;
		if (id != other.id)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;
		if (reimbursment == null) {
			if (other.reimbursment != null)
				return false;
		} else if (!reimbursment.equals(other.reimbursment))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Reimbursment getReimbursment() {
		return reimbursment;
	}

	public void setReimbursment(Reimbursment reimbursment) {
		this.reimbursment = reimbursment;
	}

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public ApprovalType getType() {
		return type;
	}

	public void setType(ApprovalType type) {
		this.type = type;
	}

	public Employee getRecipient() {
		return recipient;
	}

	public void setRecipient(Employee recipient) {
		this.recipient = recipient;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
