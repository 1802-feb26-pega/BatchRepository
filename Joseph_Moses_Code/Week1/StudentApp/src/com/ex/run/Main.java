package com.ex.run;

import java.util.List;
import java.util.Scanner;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		
		
		getNewStudent();
		
		StudentIO io = new StudentIO();
		
		List<Student> students = io.readStudents();
		for(Student s : students) {
			System.out.println(s.getFirstName() + " " + s.getLastName() + " is " + s.getAge() + " years old.");
		}

	}

	static void getNewStudent() {
		Scanner scan = new Scanner(System.in);
		StudentIO io = new StudentIO();
		String firstName;
		String lastName;
		int age;
		
		System.out.println("Hello. Please enter the first name of the student:");
		
		firstName = scan.nextLine();
		
		System.out.println("Hello. Please enter the last name of the student:");
		
		lastName = scan.nextLine();
		
		System.out.println("What is " + firstName + "'s age?:");
		
		age = Integer.parseInt(scan.next());
		
		io.writeStudent(new Student(firstName, lastName, age));	
	}
	
}
