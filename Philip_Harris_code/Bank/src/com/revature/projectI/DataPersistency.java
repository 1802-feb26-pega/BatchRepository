package com.revature.projectI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPersistency {
	static final String file = "src/data/students.txt";
	static DataPersistency data = new DataPersistency();
	

	public void writeCustomer(String accountInfo) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
			bw.write(accountInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public String readCustomer(String accountInfo){
		
		List<String> students = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
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
}
