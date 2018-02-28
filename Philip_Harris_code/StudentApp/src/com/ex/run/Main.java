package com.ex.run;

import java.util.List;
import java.util.Scanner;

import com.ex.IO.StudentIO;
import com.ex.pojos.Student;

public class Main {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Student a = new Student("Gensis", 55);
		Student b = new Student("Rick James", 77);
		Student c = new Student("Queen B", 32);
		Student d = new Student("test", 100);
		
		StudentIO io = new StudentIO();
		
		
		/*
		 * io.writeStudent(a);
		 * io.writeStudent(b);
		 * io.writeStudent(c);
		 */
		Student e = new Student(getName(),getAge());
		//io.writeStudent(e);
		
		List <Student> students = io.readStrudent();
		int count = 0;
		for(Student s : students) {
			count++;
			System.out.println(count + ".   " +s.getName() + " is " + s.getAge() + " years old.");
		}
		
		
		
		
	}
	
	static String getName() {
		
		System.out.println("What is your name?");
		String name = scan.nextLine();		
		return name;		
	}
	static int getAge() {
		int age = 0;
		System.out.println("What is your Age?");		
		age = scan.nextInt();
		scan.close();

		return age;
		
	}

}
