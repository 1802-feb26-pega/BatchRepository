package com.proj.pojos;

import java.util.ArrayList;
import java.util.List;

public class Employee {
		
		private int key;
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		private String Sup;
		private double total;
		private double awarded;
		private String dept;
		
		
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
		
		public String getSup() {
			return Sup;
		}
		public void setSup(String sup) {
			Sup = sup;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		public double getAwarded() {
			return awarded;
		}
		public void setAwarded(double awarded) {
			this.awarded = awarded;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		
		@Override
		public String toString() {
			return "Employee [key=" + key + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
					+ username + ", password=" + password + ", reportsto=" + Sup + ", total=" + total
					+ ", awarded=" + awarded + ", dept=" + dept + "]";
		}
}