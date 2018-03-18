package com.trms.util;

public class UserInputValidation {
	
	public boolean isValidInput(String input, String type) {
		switch(type) {
		case("singleNum"): return validateNumberOnlyInput(input);
		case("currency"): return validateCurrencyInput(input);
		case("email"): return validateEmailInput(input);
		case("name"): return validateNameInput(input);
		case("num"): return validateLongNumberInput(input);
		}
		return false;
	}
	
	private boolean validateNumberOnlyInput(String input) {
		if(input.matches("[0-9]{1}")) {
			return true;
		}
		return false;
	}	
	private boolean validateCurrencyInput(String input) {
		if(input.matches("[+]?\\d*\\.?\\d{2}") && Double.parseDouble(input) < Double.MAX_VALUE) {
			return true;
		}
		else return false;
	}
	private boolean validateEmailInput(String input) {
		if(input.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
		}
		return false;
	}
	private boolean validateNameInput(String input) {
		if(input.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}
	private boolean validateLongNumberInput(String input) {
		if(input.matches("[0-9]+")) {
			return true;
		}
		return false;
	}
}