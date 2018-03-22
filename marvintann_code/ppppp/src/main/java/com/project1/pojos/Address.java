package com.project1.pojos;

public class Address {
	private int addressId;
	private int employeeId;
	private String street;
	private String city;
	private String stateName;
	private int zip;
	
	public Address() {
		
	}
	
	public Address(int addressId, int employeeId, String street, String city, String stateName, int zip) {
		super();
		this.addressId = addressId;
		this.employeeId = employeeId;
		this.street = street;
		this.city = city;
		this.stateName = stateName;
		this.zip = zip;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int address_id) {
		this.addressId = address_id;
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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
}
