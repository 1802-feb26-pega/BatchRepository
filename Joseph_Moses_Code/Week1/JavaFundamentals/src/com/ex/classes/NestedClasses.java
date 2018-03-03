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
	
	//main method of NestedClasses class
	public static void main(String[] args) {
		/*int local = 0;
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
			
		};*/
		
		System.out.println(validateCurrencyInput("100.00"));
	}
	
static boolean validateCurrencyInput(String input) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		if(input.matches("[-+]?\\d*\\.?\\d{2}")) {
			return true;
		}
		else return false;
	}
}
