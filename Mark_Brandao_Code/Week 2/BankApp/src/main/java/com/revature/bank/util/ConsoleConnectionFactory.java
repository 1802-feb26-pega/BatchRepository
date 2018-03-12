package com.revature.bank.util;

import java.util.Scanner;

public class ConsoleConnectionFactory {
		private static ConsoleConnectionFactory ccf = null;
		private static Boolean build = true;
		private static Scanner scan = new Scanner(System.in);
		
		private ConsoleConnectionFactory() {
			build = false;
		}
		
		public static synchronized ConsoleConnectionFactory getInstance() {
			if(build == true) {
				ccf = new ConsoleConnectionFactory();
			}
			
			return ccf;
		}
		
		
		public Scanner getConnection() {
			return scan;
		}
		
		
}
