package com.ex.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ex.pojos.SecureStudent;

public class SerializeStudent {

	static final String fileName = "src/data/serialed.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		SecureStudent s = new SecureStudent();
		//s.setName("Philip Harris");
		//s.setPassword("12345");
		//s.setUsername("Vesruk");
		
		//serialize(s);
		s = deserilize();
		System.out.println(s.toString());
	}

	
	static void serialize(SecureStudent s) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
			
			oos.writeObject(s);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static SecureStudent deserilize() {
		
		SecureStudent s = new SecureStudent();
		try(ObjectInputStream ois = new ObjectInputStream( new FileInputStream(fileName))){
			s = (SecureStudent) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
}
