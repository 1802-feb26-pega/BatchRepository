package com.ex.pojos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentIO {
	String name = null;
	int age = 0;
	public void talker() {
		System.out.println("What's your name?");
		Scanner scan = new Scanner(System.in);
		name = scan.nextLine();
		System.out.println("What's your age?");
		age = scan.nextInt();
	}
		
	final static String filename = "src/data/students.txt";
	
	void writeStudent(Student student) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			bw.write(student.toString());
		}
		catch(IOException e) {
			e.printStackTrace();			
		}
	}
	
	
	public List<Student> readStudents(){
		
		List<Student> students = new ArrayList<Student>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while((line=br.readLine())!=null) {
				
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}
}
