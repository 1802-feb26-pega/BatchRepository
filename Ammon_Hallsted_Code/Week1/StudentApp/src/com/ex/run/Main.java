package com.ex.run;

import java.util.List;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		
		StudentIO io = new StudentIO();
	/*	Student a = new Student("test", 289);
		io.writeStudent(a);*/
		
		List<Student> students = io.readStudents();
		for(Student s : students) {
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}
		
		
	}
	
	static void getNewStudent() {
		//read in from console
	}

}
