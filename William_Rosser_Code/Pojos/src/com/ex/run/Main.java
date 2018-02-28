package com.ex.run;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		Student a = new Student("William Rosser", 21);
		Student b = new Student("Nick Hale", 22);
		Student c = new Student("Damien", 666);
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);

	}

}
