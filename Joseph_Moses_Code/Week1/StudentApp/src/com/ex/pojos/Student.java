package com.ex.pojos;

import java.io.Serializable;

//POJO = Plain old java object -- used to represent our day to day entities
public class Student implements Serializable{

	private String firstName;
	private String lastName;
	private int age;
	
	public Student() {}
	
	public Student(String fistName, String lastName, int age) {
		super();
		this.firstName = fistName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return firstName + ":" + lastName + ":" + age +"\n";
	}
	
	
}
