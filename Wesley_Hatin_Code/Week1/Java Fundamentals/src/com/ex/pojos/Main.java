package com.ex.pojos;

public class Main {

	public static void main(String[] args) {
		Student a = new Student("\n Genesis", 55);
		Student b = new Student("\n Rick James", 22);
		Student c = new Student("\n Beyonce", 35);
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);

	}

}
