package com.revature.trms.pojos;

public class TypeOfEvent {

	private int typeOfEventId;
	private String typeOfEvent;
	private int coverage;
	
	public TypeOfEvent(){}

	public TypeOfEvent(int typeOfEventId, String typeOfEvent, int coverage) {
		super();
		this.typeOfEventId = typeOfEventId;
		this.typeOfEvent = typeOfEvent;
		this.coverage = coverage;
	}

	public int getTypeOfEventId() {
		return typeOfEventId;
	}

	public void setTypeOfEventId(int typeOfEventId) {
		this.typeOfEventId = typeOfEventId;
	}

	public String getTypeOfEvent() {
		return typeOfEvent;
	}

	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

	public int getCoverage() {
		return coverage;
	}

	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}

	@Override
	public String toString() {
		return "TypeOfEvent [typeOfEventId=" + typeOfEventId + ", typeOfEvent=" + typeOfEvent + ", coverage=" + coverage
				+ "]";
	}

	
	
}
