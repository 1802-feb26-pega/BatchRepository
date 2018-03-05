package com.ex.classes;

public class NestedClasses {

	//instance/object scope
	int x;
	class Instance {
		void message() {
			System.out.println("in instance class");
		}
	}
	
	
	//static
	static class StaticClass {
		void message() {
			System.out.println("in static method");
		}
	}
	
	
	//main method of NestedClasses class
	public static void main(String[] args) {
		
		int local = 0;
		class Local {
			void message() {
				System.out.println("in local nested class");
			}
		}
		
		Runnable anonymous = new Runnable() {
			public void run() {
				System.out.println("running in anonymous class");
			}
		};
		
	}
	
}
