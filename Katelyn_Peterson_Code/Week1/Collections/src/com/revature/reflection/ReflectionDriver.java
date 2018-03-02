package com.revature.reflection;

import java.lang.reflect.Field;

public class ReflectionDriver
{
	public static void main(String[] args)
	{
		String s = "Strings are ALWAYS immutable, right?";
		
		Class<String> stringClass = String.class;
		
		System.out.println("Old value: " + s);
		
		try
		{
			// get access to the 'value' attribute of the String class
			Field value = stringClass.getDeclaredField("value");
			
			// change the accessibility of the 'value' attribute to true
			// BUT WAIT!  I thought Strings were immutable...
			// That is how powerful Reflection is!
			value.setAccessible(true);
			
			// Change 'value' to equal something else.
			value.set(s, "Bananana".toCharArray());
			System.out.println("New Value: " + s);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
