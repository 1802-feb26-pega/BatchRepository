package com.revature.store;

public class Dessert
{
	static int staticCounter = 0;
	public int instanceCounter = 0;
	static String hello = "Hello";
	
	public static void function()
	{
		System.out.println(hello);
		
		Dessert d = new Dessert();
		Dessert e = new Dessert();
		
		d.staticCounter++;
		e.staticCounter++;
		staticCounter++;
		
		// Static/Class Scope: exists across all instances, does not need instatiation
		System.out.println(staticCounter++);
		
		d.staticCounter++;		
		
		// Instance/Object Scope: exists local to the instance and nowhere else
		System.out.println(++d.instanceCounter);
		System.out.println(e.instanceCounter);
		
		// Function Scope
		int functionCounter = 0;
		
		{
			// Block/Loop Scope
			int blockCounter = 0;
		}
	}
	
	public static void main(String[] args)
	{
		IceCreamSandwich ics = new IceCreamSandwich();
		ics.getFlavor();
		
		
		//function();
		//System.out.println(functionCounter);
		
		// Control Statements
		// If-Else, Loops, Switch-Case
		
		if (true)
		{
			// do Something
			
		}
		else
		{
			// do Something Else
		}
		
		if (1 > 2)
		{
			// Do Something
		}
		else if (1 < 2)
		{
			// Do another thing
		}
		else if (1 == 2)
		{
			// Do third thing
		}
		else
		{
			// Do anything
		}
		
		int count = 0;
		while (count < 0)
		{
			count++;
		}
		
		do
		{
			count++;
		}while(count < 1);
		
		switch(count)
		{
		case 1: count++;
		break;
		case 2: count++;
		break;
		default: break;
		}
	}

}