package com.ex.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ex.pojos.Student;

public class StudentIO {
	
	final static String FILENAME = "src/data/students.txt";
	
	public void writeStudent(Student student) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) { 
			bw.write(student.toString());
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public List<Student> readStudents(){
		List<Student> students = new ArrayList<Student>();
		try(BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			String line = null;
			while ((line=br.readLine()) != null) {
				String[] fields = line.split(":");
				Student fileStudent = new Student(fields[0], Integer.parseInt(fields[1]));
				students.add(fileStudent);
			}
		}
		catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return students;
	}
	
}
