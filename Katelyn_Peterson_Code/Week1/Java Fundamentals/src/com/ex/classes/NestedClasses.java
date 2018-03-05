package com.ex.classes;

public class NestedClasses
{
	// Instance/Object Scope
	int x;
	class Instance
	{
		void message()
		{
			System.out.println("in Instance Nested Class");
		}
	}
	
	// static
	static class StaticClass
	{
		void message()
		{
			System.out.println("in Static Nested Class");
		}
	}
	
	// Main Function of NestedClasses class
	public static void main(String[] args)
	{
		int local = 0;
		
		class Local
		{
			void message()
			{
				System.out.println("in Local Nested Class");
			}
		}
		
		Runnable anonymous = new Runnable()
		{

			@Override
			public void run()
			{
				System.out.println("Running in Anonymous Class");
				
			}
		};
	}
}
