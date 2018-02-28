package com.ex.run;

import com.ex.io.StudentIO;
import com.ex.pogo.Student;

public class Main {
	
	public static void main(String[] args) {
		Student a = new Student("Thomas Hemming", 23);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Person Guy", 28);
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);
		
		
		
	}

}
