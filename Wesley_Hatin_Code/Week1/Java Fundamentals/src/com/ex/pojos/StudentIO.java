package com.ex.pojos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class StudentIO {
	
	static final String filename = "src/data/students.txt";
	
	public void writeStudent(Student student) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			bw.write(student.toString());
			bw.close();
		}catch(FileNotFoundException f) {
			f.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Student> readStudents(){
		List<Student> students = new ArrayList<Student>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = null;
			while((line=br.readLine()) != null) {
				String[] data = line.split(";");
				Student temp = new Student();
				temp.setName(data[0]);
				temp.setAge(Integer.parseInt(data[1]));
				students.add(temp);
			}
			br.close();
		}catch(FileNotFoundException f) {
			f.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}

}
