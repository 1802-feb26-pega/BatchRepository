package com.ex.run;

//import java.util.List;
import java.util.Scanner;

import com.ex.io.StudentIO;
import com.ex.pojos.Student;

public class Main {
	public static void main(String[] args) {
//		Student a = new Student("Genesis", 55);
//		Student b = new Student("Rick James", 77);
//		Student c = new Student("Beyonce", 34);
//		
//		StudentIO io = new StudentIO();
//		
//		io.writeStudent(a);
//		io.writeStudent(b);
//		io.writeStudent(c);
		
		
		repeatGNS();
	}
	

	static void repeatGNS() {
		String condition = "y";
		while (condition.toLowerCase() == "y" || condition.toLowerCase() == "yes") {
			getNewStudent();
			Scanner scan = new Scanner(System.in);
			System.out.println("Do you want to record another new student? (y/n)");
			condition = scan.next();
			scan.close();
		}
	}
	
	static void getNewStudent() {
		StudentIO io = new StudentIO();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a student's name: ");
		String s = scan.next();
		System.out.println("Enter the student's age: ");
		String n = scan.next();
		Student nn = new Student(s, Integer.parseInt(n));
		scan.close();
		io.writeStudent(nn);
	}
}
