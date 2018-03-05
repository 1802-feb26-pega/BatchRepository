package com.ex.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ex.pojos.SecureStudent;

public class SerializeStudent {
	
	static final String FILENAME = "src/data/serialized.txt";
	
	
	public static void main(String[] args) {
		SecureStudent s = new SecureStudent();
		s.setName("Mark Brandao");
		s.setUsername("mbrandao");
		s.setPassword("password");
		s.setFl(135.41f);
		s.setTest(true);
		s.setLetter('g');
		serialize(s);
		System.out.println(s.toString());
		SecureStudent n = new SecureStudent();
		n = deserialize();
		System.out.println(n.toString());
	}
	
	static void serialize(SecureStudent s) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))){
			oos.writeObject(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static SecureStudent deserialize() {
		SecureStudent s = new SecureStudent();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))){
			s = (SecureStudent) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
}
