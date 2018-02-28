package com.ex.pojos;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
		StudentIO io = new StudentIO();
		Scanner scan = new Scanner(System.in);
		Student newStudent = new Student();
		
		System.out.println("Hello, new student! Welcome to Revature High!");
		System.out.println("Before we can get started, I need to know your name.");
		System.out.println("Please enter your name here: ");
		newStudent.setName(scan.nextLine());
		
		String[] name = newStudent.getName().split(" ");
		System.out.println("Alright! So, " + name[0] + ", how old are you?");
		newStudent.setAge(Integer.parseInt(scan.nextLine()));
		io.writeStudent(newStudent);
		
		List<Student> students = io.readStudents();
		for(Student s : students) {
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}
		scan.close();

	}

}
