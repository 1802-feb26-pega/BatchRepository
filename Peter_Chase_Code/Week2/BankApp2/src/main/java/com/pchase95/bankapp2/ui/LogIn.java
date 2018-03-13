package com.pchase95.bankapp2.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pchase95.bankapp2.driver.Application;

public class LogIn extends JFrame {
	private static final long serialVersionUID = -1289644530511476544L;
	
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblPetesBank;
	private JLabel lblLogin;
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblStatus;
	private JLabel lblCurrrentMoney;
	private JLabel lblCurrentMoney;

	public LogIn() {
		initialize();
		lblCurrentMoney.setText(
				String.format("$%.2f", Application.getTotalMoney()));
	}
	
	private void btnSignUpClick() {
		WindowController.launchSignUp();
	}
	
	private void btnSignInClick() {
		String nameOrEmail = textFieldUsername.getText();
		String password = textFieldPassword.getText();
		
		if (Application.attemptSignIn(nameOrEmail, password)) {
			WindowController.launchHome();
			dispose();
		} else {
			lblStatus.setText("Wrong username/email or password");
		}
		
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setBounds(100, 100, 539, 502);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblPetesBank = new JLabel("Pete's Bank");
		lblPetesBank.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblPetesBank.setBounds(190, 11, 141, 51);
		getContentPane().add(lblPetesBank);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLogin.setBounds(190, 129, 71, 51);
		getContentPane().add(lblLogin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textFieldUsername.setBounds(190, 186, 192, 34);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textFieldPassword.setBounds(190, 233, 192, 34);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		btnSignIn = new JButton("Submit");
		btnSignIn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSignIn.setBounds(190, 280, 97, 25);
		getContentPane().add(btnSignIn);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignUpClick();
			}
		});
		btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSignUp.setBounds(191, 383, 109, 34);
		getContentPane().add(btnSignUp);
		
		lblUsername = new JLabel("Username / Email");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUsername.setBounds(71, 187, 107, 34);
		getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPassword.setBounds(71, 234, 59, 34);
		getContentPane().add(lblPassword);
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblStatus.setBounds(107, 318, 297, 34);
		getContentPane().add(lblStatus);
		
		lblCurrrentMoney = new JLabel("Currently holding");
		lblCurrrentMoney.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCurrrentMoney.setBounds(211, 50, 107, 34);
		getContentPane().add(lblCurrrentMoney);
		
		lblCurrentMoney = new JLabel("$0.00");
		lblCurrentMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMoney.setFont(new Font("SansSerif", Font.PLAIN, 36));
		lblCurrentMoney.setBounds(22, 75, 476, 41);
		getContentPane().add(lblCurrentMoney);
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSignInClick();
			}
		});
	}
}
