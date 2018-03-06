package com.ex.run;

import java.util.List;
import java.util.Scanner;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {

	private static StudentIO io;
	public static void main(String[] args) {
//		Student a = new Student("William Rosser", 21);
//		Student b = new Student("Nick Hale", 22);
//		Student c = new Student("Damien", 666);
//		
		io = new StudentIO();
//		
//		io.writeStudent(a);
//		io.writeStudent(b);
//		io.writeStudent(c);
		List<Student> students = io.readStudents();
		for(Student s : students) {
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}
		getNewStudent();
	}
	
	static void getNewStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a new Student's name:");
		String name = sc.nextLine();
		Student s = new Student();
		s.setName(name);
		int age = -1;
		while (age < 0) {
			try {
				System.out.println("Enter the new student's age:");
				age = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Enter a real age.");
			}
		}
		s.setAge(age);
		System.out.println("Added: " + s.toString());
		io.writeStudent(s);
	}

}
