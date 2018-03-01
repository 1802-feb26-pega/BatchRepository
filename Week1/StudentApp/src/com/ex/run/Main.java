package com.ex.run;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		Student a = new Student("Genesis", 55);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Beyonce", 34);
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);
	}

}