package com.ex.run;

import com.ex.IO.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student a = new Student("Gensis", 55);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Queen B", 32);
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);
		
	}

}
