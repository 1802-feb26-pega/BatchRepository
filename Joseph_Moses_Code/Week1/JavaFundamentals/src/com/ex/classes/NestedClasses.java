package com.ex.classes;

import java.text.NumberFormat;
import java.util.Locale;

public class NestedClasses {

	//instance/object scope
	int x;
	class Instance{
		void message() {
			System.out.println("instance neseted class");
		}
	}
	
	static class StaticClass{
		void message() {
			System.out.println("in static nested class");
		}
	}
	
	interface MethodPassTest{
		void abstractMethod();
		
		default Object callAbleMethod() {
			System.out.println("callAbleMethod is called.");
			Object object = new Object();
			return object ;
		}
	}
	
	public static void takingMethod1(Object test) {
		System.out.println("in takingMethod");
	}
	
	//main method of NestedClasses class
	public static void main(String[] args) {
		
		MethodPassTest obj = (MethodPassTest) new Object();
		
		takingMethod1(obj.callAbleMethod());
		
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

	private static void takingMethod(Object callAbleMethod) {
		// TODO Auto-generated method stub
		
	}
	
}
