package com.revature.driver;

import java.util.Stack;

public class StackDemo {
	   public static void main(String args[]) {

	      Stack<String> st = new Stack<String>();

	      st.push("Alpha");
	      st.push("Beta");
	      st.push("Gamma");

	      System.out.println("Removed object is: "+st.pop());
	      System.out.println("Elements after remove: "+st);
	      
	      System.out.println("Element at index 1 is " +st.elementAt(1));
	      
	      st.setElementAt("Delta", 1);
	      System.out.println("Elements after setElement: "+st);
	   }    
	}
