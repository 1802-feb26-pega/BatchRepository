package com.ex.run;

import java.util.List;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		/*Student a = new Student("Genesis", 55);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Beyonce", 34);*/
		
		StudentIO io = new StudentIO();
		
		/*io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);*/
		
		List<Student> students = io.readStudents();
		
		for (Student s : students)
		{
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}
	}
	
	static void getNewStudent()
	{
		// Read in from console
		
	}

}
