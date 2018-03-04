package com.ex.classes;

public class NestedClasses {
	
	//instance/object scope
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
	
	public static void main(String[] args) {
		int local = 0;
		class local{
			void message() {
				System.out.println("in local nested class");
			}
		}
		
		Runnable anonymous = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("running in anonymous class");
			}
		};
	}
}
