package com.ex.pojos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SecureStudentIO {
	static final String file = "src/data/serialized.txt";
	
	public static void main(String[] args) {
		SecureStudent s = new SecureStudent();
		
		s.setName("idk");
		s.setPassword("something");
		s.setUsername("somethingelse");
		serialize(s);
		
		System.out.println(s);
		s = deserialize();
		System.out.println(s);
		
	}

	static void serialize(SecureStudent s) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file));
			oos.writeObject(s);
			oos.close();
			
		}catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static SecureStudent deserialize() {
		SecureStudent s = new SecureStudent();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			s = (SecureStudent) ois.readObject();
			ois.close();
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return s;
	}
}
