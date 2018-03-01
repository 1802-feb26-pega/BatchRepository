package com.ex.io;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ex.pojos.SecureStudent;


public class SerializeStudent {
	static final String filename = "src/data/serialized.txt";
	
	public static void main(String[] args) {
		SecureStudent s = new SecureStudent("William", "wille179", "Apackalot");
		serialize(s);
		SecureStudent p = deserialize();
		System.out.println(p.toString());
		System.out.println(SecureStudent.getCounter());
	}
	
	static void serialize(SecureStudent s) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static SecureStudent deserialize() {
		SecureStudent s = new SecureStudent();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
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
