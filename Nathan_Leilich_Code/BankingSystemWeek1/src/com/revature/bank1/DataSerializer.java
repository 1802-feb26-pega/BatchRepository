package com.revature.bank1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class DataSerializer {

	private static final DataSerializer INSTANCE = new DataSerializer();
	private static final String FILE_PATH = "src/Data/UserData.txt";
	
	public static DataSerializer getInstance(){
		
		return INSTANCE;
	}//constructor
	
	public void serialize(HashMap<String, User> data) {
		
		try {
			
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			System.out.println("user data saved");
			
			oos.close();
			fos.close();
		}//try
		catch (IOException e) {
			
			System.out.println("exception thrown on serialize()");
		}//catch
	}//serialize()
	
	
	public HashMap<String, User> deSerialize() {
		
		HashMap<String, User> users = null;
		try {
			
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ObjectInputStream ois = new ObjectInputStream(fis);
			users = (HashMap<String, User>) ois.readObject();
			ois.close();
			fis.close();
		}//try
		catch (FileNotFoundException fnfe) { 	//If file doesn't exist, there is no data
											 	//return an empty HashMap instead
			return new HashMap<String, User>();
		}
		catch (IOException | ClassNotFoundException e) {
			
			System.out.println("exception thrown on deSerialize()");
		}//catch
		return users;
	}//deSerialize()
}//DataSerializer
