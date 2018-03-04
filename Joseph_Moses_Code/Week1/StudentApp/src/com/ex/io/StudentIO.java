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

	final static String filename = "src/data/students.txt";
	
	public void writeStudent(Student student) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			
			bw.write(student.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Student> readStudents(){
		
		List<Student> students = new ArrayList<Student>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			
			String line = null;
			while((line = br.readLine()) != null) {
				String[] data = line.split(":");
				Student temp = new Student(data[0],data[1],Integer.parseInt(data[2]));
				students.add(temp);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		return students;
		
	}
	
}