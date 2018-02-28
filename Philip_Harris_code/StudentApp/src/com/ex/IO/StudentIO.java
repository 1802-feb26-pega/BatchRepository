package com.ex.IO;

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

	static final String fileName = "src/data/students.txt";
	
	public void writeStudent(Student student) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
			bw.write(student.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Student> readStrudent(){
		
		List<Student> students = new ArrayList<Student>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
				String line = null;
				while((line=br.readLine()) != null) {
					String[] data = line.split(":");
					Student temp = new Student();
					temp.setName(data[0]);
					temp.setAge(Integer.parseInt(data[1]));
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
	
	public List<Student> removeStudent(String name){
		
		List<Student> students = new ArrayList<Student>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
				String line = null;
				while((line=br.readLine()) != null) {
					String[] data = line.split(":");
					Student temp = new Student();
					//if(data[0])
					temp.setName(data[0]);
					temp.setAge(Integer.parseInt(data[1]));
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
