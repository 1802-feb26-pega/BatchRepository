package com.ex.pojos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class StudentIO {
	
	static final String filename = "src/data/students.txt";
	
	public void writeStudent(Student student) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			bw.write(student.toString());
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Student> readStudents(){
		List<Student> students = new ArrayList<Student>();
		return students;
	}

}
