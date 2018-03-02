package com.project.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.project.Logic.AppMemory;
import com.project.Logic.User;

public class Serializer {
	static final String filename = "bin/data/serializedAppData.txt";
	
	public static void serialize(AppMemory app) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(app);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static AppMemory deserialize() {
		AppMemory am = new AppMemory();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			am = (AppMemory) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return am;
	}

}
