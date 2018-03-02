package com.project.Data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.project.Logic.AppMemory;

public class Serializer {
	static final String filename = "src/data/serializedAppData.txt";
	
	public static void serialize(AppMemory app) {
//		File f = new File(filename);
//		if (!f.exists())
//			try {
//				f.createNewFile();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(app);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static AppMemory deserialize() {
		AppMemory am = null;
		File f = new File(filename);
		if (!f.exists()) {
			return null;
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			am = (AppMemory) ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found.");
		} catch (IOException e) {
			System.err.println("Trouble reading serialization file.");
		} catch (ClassNotFoundException e) {
			System.err.println("Can't find corresponding class.");
		}
		return am;
	}

}
