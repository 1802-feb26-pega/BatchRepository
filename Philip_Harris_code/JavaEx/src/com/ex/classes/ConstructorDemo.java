package com.ex.classes;

public class ConstructorDemo {
	int a;
	int b;
	int x,y,z;
	
	
	public ConstructorDemo() {
		System.out.println("in no args constructor");
	}
	//ConstructorDemo cd = new ConstructorDemo() is incorrect because a constructor was created and default constructor was deleted.
	
	
	public ConstructorDemo(int random) {
		this();
		this.a = random;
	}
	
	public ConstructorDemo( String num) {
		this();
		this.a = Integer.parseInt(num);
		System.out.println("in random constructor");
	}
	
	public ConstructorDemo(int a, int b, int x, int y, int z) {
		super();
		this.a = a;
		this.b = b;
		this.x = x;
		this.y = y;
		this.z = z;
		System.out.println("in all var constructor");
	}
	
	public static void main(String[] args) {
		
		ConstructorDemo test = new ConstructorDemo(5);
	}
	
/* main ctrl space key strokes	
	public static void main(String[] args) {
		
	}
	
*/	
class Test{
	
}
	
	
}
