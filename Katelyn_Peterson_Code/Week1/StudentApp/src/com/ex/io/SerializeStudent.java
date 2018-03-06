package com.ex.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ex.pojo.SecureStudent;

public class SerializeStudent
{
	static String file = "src/data/serialized.txt";
	
	public static void main(String[] args)
	{
		SecureStudent s = new SecureStudent();
		
		s.setName("Genesis Bonds");
		s.setPassword("password");
		s.setUsername("gb");
		s.setFl(135.41f);
		s.setTest(true);
		s.setLetter('g');
		
		serialize(s);
		
		System.out.println(s.toString());
		
		s = deserialize();
		System.out.println(s.toString());
	}
	
	static void serialize(SecureStudent s)
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(file)))
		{
			oos.writeObject(s);
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static SecureStudent deserialize()
	{
		SecureStudent s = new SecureStudent();
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
		{
			s = (SecureStudent) ois.readObject();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
}