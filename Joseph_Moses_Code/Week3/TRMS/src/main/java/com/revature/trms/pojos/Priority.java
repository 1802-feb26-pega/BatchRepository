package com.revature.trms.pojos;

public class Priority {

	private int priorityId;
	private String priority;
	
	public Priority(){}

	public Priority(int priorityId, String priority) {
		super();
		this.priorityId = priorityId;
		this.priority = priority;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Priority [priorityId=" + priorityId + ", priority=" + priority + "]";
	}
	
	
}
