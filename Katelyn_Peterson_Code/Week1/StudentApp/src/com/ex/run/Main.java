package com.ex.run;

import java.util.List;
import java.util.Scanner;

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
		
		getNewStudent(io);
		
		List<Student> students = io.readStudents();
		
		
		for (Student s : students)
		{
			System.out.println(s.getName() + " is " + s.getAge() + " years old.");
		}
	}
	
	static void getNewStudent(StudentIO io)
	{
		// Read in from console
		Scanner input = new Scanner(System.in);
		String info;
		Student temp = new Student();
		
		System.out.println("What is your name: ");
		info = input.nextLine();
		
		temp.setName(info);
		
		System.out.println("What is your age: ");
		validateInput(input);
		info = input.nextLine();
		
		temp.setAge(Integer.parseInt(info));
		
		//students.add(temp);
		io.writeStudent(temp);
		
		//System.out.println(temp.getName() + " is " + temp.getAge() + " years old.");
		
		input.close();
	}
	
	static void validateInput(Scanner input)
	{
		System.out.println("Enter age: ");
		
		while (!input.hasNextInt())
		{
			System.out.println("Please enter an Integer.");
			System.out.println("Enter age: ");
			input.nextLine();
		}
	}

}
