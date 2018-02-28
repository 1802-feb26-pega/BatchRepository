package com.ex.run;

import java.util.List;
import java.util.Scanner;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	public static void main(String[] args) {
		/*
		Student a = new Student("Genesis", 55);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Beyonce", 34);
		
		
		
		io.writeStudent(a);
		io.writeStudent(b);
		io.writeStudent(c);*/
		
		StudentIO io = new StudentIO();
		
		List<Student> students = io.readStudents();
		for(Student s : students) {
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}
	
	}
	
	static void getNewStudent() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Name: ");
		String name = scan.nextLine();
		System.out.println("Age: ");
		int age = scan.nextInt();
		
		//instantiate student with scanned values and return it
		Student student = new Student(name, age);
		//student.
	}
	
}
