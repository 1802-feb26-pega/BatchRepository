package com.ex.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ex.pojos.Student;

public class StudentIO {

	static final String fileName = "src/data/students.txt";
	
	public void writeStudent(Student student) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
			bw.write(student.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	List<Student> readStrudent(){
		
		List<Student> students = new ArrayList<Student>();
		return students;
		
	}
}
