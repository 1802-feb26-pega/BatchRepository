package com.ex.classes;

public class ConstructorDemo {
	int a=0;
	int b=0;
	int x, y, z;
	
	public ConstructorDemo() {
		System.out.println("in no args constructor");
	}
	
	public ConstructorDemo(int random) {
		this();
		this.a = random;
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
	
	@Override
	public String toString() {
		return  String.valueOf(this.a) + String.valueOf(this.b);
	}
	
	public static void main(String[] args) {
		ConstructorDemo test = new ConstructorDemo();
		ConstructorDemo test2 = new ConstructorDemo(1);
		
		test.b = test2.a+1;
		System.out.println(test);
	}

}
