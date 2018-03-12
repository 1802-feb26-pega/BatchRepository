package BankInside;

import java.util.HashMap;

public class user {
	private String username;
	private String email;
	private String password;
	private double account = 0.0d;
	private HashMap<String, String> usrPwd;
	private HashMap<String, String> usrEmail;
	private HashMap<String, Double> usrAcct;
	
	public void logoutRemoval() {
		this.username = "";
		this.email = "";
		this.password = "";
		this.account = 0.0d;
	}
	
	//Username Stuff

	public String getUsername() {
		return username;
	}
	public void setUserPass(String usr, String pwd) {
		this.username = usr;
		this.password = pwd;
		this.usrPwd.put(usr,pwd);
	}
	public boolean boolUsername(String usr) {
		if (true == usrPwd.containsKey(usr)) {
			return true;
		}
		return false;
	}
	
	//Email Stuff
	
	public String getEmail() {
		return email;
	}
	public void setUserEmail(String usr, String email) {
		this.username = usr;
		this.email = email;
		this.usrEmail.put(usr, email);
	}
	
	//Password Stuff
	
	public boolean boolPassword(String pwd) {
		this.password = usrPwd.get(username);
		if (true == usrPwd.containsKey(pwd)) {
			return true;
		}
		return false;
	}
	
	//Account Stuff
	
	public double getAccount() {
		account = usrAcct.get(username);
		return account;
	}
	public void setUserAcct(String usr) {
		this.username = usr;
		this.account = 0.0d;
		this.usrAcct.put(usr, account);
	}
	public void increaseAccount(double amount) {
		this.account += amount;
		usrAcct.replace(username, account);
		account = usrAcct.get(username);
	}
	public void decreaseAccount(double amount) {
		this.account -= amount;
		usrAcct.replace(username, account);
		account = usrAcct.get(username);
	}	
}
