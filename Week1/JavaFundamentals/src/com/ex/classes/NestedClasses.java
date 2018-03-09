package com.ex.classes;

public class NestedClasses {
	
	//instance/object scope
	int x;
	class Instance{
		void message() {
			System.out.println("in instance nested class");
		}
	}
	
	//static
	static class StaticClass{
		void message() {
			System.out.println("in static nested class");
		}
	}
	
	//main method of NestedClasses class
	public static void main(String[] args) {
		
		int local = 0;
		class Local{
			void message() {
				System.out.println("in local nested class");
			}
		}
		
		Runnable anonymous = new Runnable() {
			@Override
			public void run() {
				System.out.println("running in anonymous class");
			}
		};
		
	}

}
