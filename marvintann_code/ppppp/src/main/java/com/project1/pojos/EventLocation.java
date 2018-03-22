package com.project1.pojos;

public class EventLocation {
	private int locationId;
	private int eventLocationId;
	private String street;
	private String city;
	private String stateName;
	private int zip;
	
	public EventLocation() {
		
	}
	
	public EventLocation(int locationId, int eventLocationId, String street, String city, String stateName, int zip) {
		super();
		this.locationId = locationId;
		this.eventLocationId = eventLocationId;
		this.street = street;
		this.city = city;
		this.stateName = stateName;
		this.zip = zip;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getEventLocationId() {
		return eventLocationId;
	}
	
	public void setEventLocationId(int eventLocationId) {
		this.eventLocationId = eventLocationId;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	
	
}
