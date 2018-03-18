package com.trms.pojos;

public class Coverage {
	private int eventCoverageId;
	private String eventType;
	private short coveragePercentage;
	
	public Coverage() { }
	
	public Coverage(int eventCoverageId, String eventType, short coveragePercentage) {
		super();
		this.eventCoverageId = eventCoverageId;
		this.eventType = eventType;
		this.coveragePercentage = coveragePercentage;
	}
	public int getEventCoverageId() {
		return eventCoverageId;
	}
	public void setEventCoverageId(int eventCoverageId) {
		this.eventCoverageId = eventCoverageId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public short getCoveragePercentage() {
		return coveragePercentage;
	}
	public void setCoveragePercentage(short coveragePercentage) {
		this.coveragePercentage = coveragePercentage;
	}
	@Override
	public String toString() {
		return "Coverage [eventCoverageId=" + eventCoverageId + ", eventType=" + eventType + ", coveragePercentage="
				+ coveragePercentage + "]";
	}
}
