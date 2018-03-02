package com.ex.pojos;

import java.io.Serializable;

public class Student implements Serializable {

	private String name;
	private int age;
	
	public Student() {/* empty */}
	
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
		return name + ":" + age + "\n";
	}
}