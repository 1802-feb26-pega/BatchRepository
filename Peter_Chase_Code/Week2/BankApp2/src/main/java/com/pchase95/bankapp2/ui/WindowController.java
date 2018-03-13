package com.pchase95.bankapp2.ui;

import java.awt.EventQueue;

public class WindowController {
	private WindowController() { }
	
	public static void launchLogIn() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn login = new LogIn();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void launchSignUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp signup = new SignUp();
				
					signup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	public static void launchBank(final long accountId, final Home home) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank bank = new Bank(accountId, home);
					bank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void launchHome() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void launchCreateAccount(final Home home) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount(home);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
