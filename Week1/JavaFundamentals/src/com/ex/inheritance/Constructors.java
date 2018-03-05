package com.ex.inheritance;

public class Constructors {

/* this class was made to show simple constructor inheritance, 
 * in addition to both static and dynamic binding
*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		A c = new C();
		c.getType();
		System.out.println(c.me);
		//c.getB();
		System.out.println(c.getClass());
	}
}

class A{
	public int me = 1; //
	public A(){
		System.out.println("A");
	}
	
	public void getType(){// 
		System.out.println("Hi I am A");
	}
	
	public void getA(){
		System.out.println("I can access getA");
	}
}

class B extends A{
	public int me = 2;
	
	public B(){
		System.out.println("B");
	}
	
	public void getType(){
		System.out.println("Hi I am B");
	}
	
	public void getB(){
		System.out.println("I can access getB");
	}
}

class C extends B{
	public int me = 3;
	public C(){
		System.out.println("C");
	}
	
	public void getType(){
		System.out.println("Hi I am C");
	}
	
	public void getC(){
		System.out.println("I can access getC");
	}
}