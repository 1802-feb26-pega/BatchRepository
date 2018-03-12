package com.ex.pojos;

import java.io.Serializable;

//pojo = plain-old java object - used to represent our day to day entities
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -812029385295181278L;

	private String name;
	private int age;
	
	public Student() {}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return name + ";" + age + "\n";
	}
	
	
	
}