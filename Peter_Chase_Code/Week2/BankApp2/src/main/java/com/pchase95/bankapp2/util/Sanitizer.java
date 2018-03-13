package com.pchase95.bankapp2.util;

import java.util.regex.Pattern;

public class Sanitizer {
	private Sanitizer() { }
	
	private static final Pattern usernamePattern = Pattern.compile(
		"([A-Za-z0-9\\-\\_]+)");
	public static boolean sanitizeUsername(String username) {
		return usernamePattern.matcher(username).matches();
	}

	private static final Pattern emailPattern = Pattern.compile(
		   "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"
	 	 + "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
		 + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
		 + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
		 + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\"
		 + "x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	public static boolean sanitizeEmail(String email) {
		return emailPattern.matcher(email).matches();
	}
	
	
	private static final Pattern passwordPattern = Pattern.compile(
		"^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$");
	public static boolean sanitizePassword(String password) {
		return passwordPattern.matcher(password).matches();
	}
	
	public static boolean sanitizePin(String pin) {
		try {
			Integer.parseInt(pin);
			return pin.length() == 4;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
