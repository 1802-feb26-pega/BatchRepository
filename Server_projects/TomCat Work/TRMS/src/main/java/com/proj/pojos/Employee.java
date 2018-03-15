package com.proj.pojos;

import java.util.ArrayList;
import java.util.List;

public class Employee {
		
		private int key;
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		
		
		//Getters and Setters
		
		
		
		
		/**
		 * @param name
		 * @param password
		 */
		public Employee(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		public Employee() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Employee [key=" + key + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
					+ username + ", password=" + password + "]";
		}
		//-------------------------------------------------------------------------------------
		//TESTING AREA	
		
		private ArrayList<String> skills;
		
		public static Employee toJSON() {
			// TODO Auto-generated constructor stub
			
			Employee emp = new Employee();
			emp.setFirstname("Philip");
			emp.setLastname("Harris");
			

	        List<String> skills = new ArrayList<>();
	        skills.add("java");
	        skills.add("python");
	        skills.add("shell");
			
			return emp;
		}
		
		
}
