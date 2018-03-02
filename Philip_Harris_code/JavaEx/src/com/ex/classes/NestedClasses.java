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
	//Main methos of nestedClasses class
	public static void main(String[] args) {
		
		int local = 0;
		class local{
			void  message() {
				System.out.println("in local nested class");
			}
		}
		
	Runnable anon = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Running in anonymout class");
		}
		
	};
		
	}
}
