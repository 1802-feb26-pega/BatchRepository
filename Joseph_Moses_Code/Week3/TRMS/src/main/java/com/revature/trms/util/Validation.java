package com.revature.trms.util;

public class Validation {

	public boolean validPhoneInput(String input) {
		
		if(input.matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")) {
			return true;
		}
		
		return false;
	}
	
	public boolean validPasswordInput(String input) {
		
		if(input.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
			return true;
		}
		else return false;
	}
	
	public boolean validEmailInput(String input) {
		
		if(input.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
		}
		
		return false;
	}
	
	public boolean validNameInput(String input) {
		if(input.matches("[a-zA-Z]+")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean validateAccountIdInput(String input) {
		
		if(input.matches("[0-9]+")) {
			return true;
		}
		
		return false;
	}
}
