package com.project.UI;

public class ApplicationUI {
	
	
	/**
	 * Prompts the user to register for an account. Returns a String array with the following format:
	 *     [0] = User's First name. (I.E. "Joe")
	 *     [1] = User's middle initial (I.E. "J")
	 *     [2] = User's last name ("Smith")
	 *     [3] = User's email address.
	 *     [4] = User's chosen username.
	 *     [5] = User's password.
	 *     
	 * If any input is invalid, or the user chooses to exit, this returns null instead.
	 * @return String array of user input (length 5), or null.
	 */
	public String[] register() {
		return null;
	}

	/**
	 * Prompts the user to log in via the UI, asking for username & password.
	 * @return A String[] of length 2 containing the username and password, respectively.
	 */
	public String[] userLogIn() {
		return null;
	}
	
	/**
	 * Prompts a returning user from a previous session to log in.
	 * Returns none if the user does not choose to log into the previous account.
	 * 
	 * @param user The birth name of the user.
	 * @param username The account username of the user.
	 * @return the password of the user, or none if they choose to log into 
	 * a different account.
	 */
	public String returningUserLogIn(String user, String username) {
		return null;
	}
	
	/**
	 * Prompts the user via the UI if they want to log out.
	 * @param user The username of the current user.
	 * @return True if the user wants to log out, or false if they want to stay logged in.
	 */
	public boolean userLogOut(String user) {
		return false;
	}
	
	/**
	 * Prompts the user to deposit money into their account.
	 * @param user The username of the current user.
	 * @param currentBalance The current money balance in their account.
	 * @return The amount the user deposits, or -1 if the input is invalid.
	 */
	public float depositMoney(String user, float currentBalance) {
		return -1f;
	}

	/**
	 * Prompts the user to withdraw money from their account.
	 * @param user The username of the current user.
	 * @param currentBalance The current money balance in their account.
	 * @return The amount they wish to withdraw, regardless of their actual balance. Returns -1 if the input is invalid.
	 */
	public float withdrawMoney(String user, float currentBalance) {
		return -1f;
	}
	
	/**
	 * Prints out a formatted string of the user's name and their current balance.
	 * @param user
	 * @param currentBalance
	 */
	public void viewBalance(String user, float currentBalance) {
		String out = String.format("%1$s,\nYour current balance is: $%2$.2f.", user, currentBalance);
		System.out.println(out);
	}
}
