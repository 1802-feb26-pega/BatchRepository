package com.ex.run;

import java.util.List;
import java.util.Scanner;

import com.ex.io.StudentIO;
import com.ex.pogo.Student;

public class Main {
	
	public static void main(String[] args) {
		/*Student a = new Student("Thomas Hemming", 23);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Person Guy", 28);
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);*/
		
		/*StudentIO io = new StudentIO();
		
		List<Student> students = io.readStudents();
		
		for(Student s : students) {
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}*/
		
		getNewStudent();
		
	}
	
	static void getNewStudent() {
		Scanner scan = new Scanner(System.in);
		  
		System.out.println("Enter your name:");		  
		String name = scan.nextLine();
		
		System.out.println("Enter your age:");
		int age = Integer.parseInt(scan.nextLine());
		
		StudentIO io = new StudentIO();
		
		io.writeStudent(new Student(name, age));
		
		System.out.println("\nAdded student, " + name + ", to file.");
	}

}
