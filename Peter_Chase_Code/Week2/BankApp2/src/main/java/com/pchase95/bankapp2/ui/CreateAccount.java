package com.pchase95.bankapp2.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.pchase95.bankapp2.driver.Application;
import com.pchase95.bankapp2.pojos.Account;
import com.pchase95.bankapp2.util.Sanitizer;

public class CreateAccount extends JFrame {
	private static final long serialVersionUID = -7970455665301399394L;
	
	private JPanel contentPane;
	private JTextField pinField;
	private JTextField nameField;
	private JLabel lblStatus;
	private JButton btnSubmit;
	private JLabel lblName;
	private JLabel lblPin;
	private JLabel lblHead;

	private boolean enabled;
	private Home home;

	public CreateAccount(Home home) {
		this.home = home;
		enabled = true;
		initialize();
	}
	
	private void formatPin(JTextField tf, KeyEvent e) {
		if (enabled) {
			char ch = e.getKeyChar();
			int code = e.getKeyCode();
			String text = tf.getText();
			
			if (ch >= '0' && ch <= '9' && text.length() < 4) {
				tf.setText(tf.getText() + ch);
			}
			
			if (code == KeyEvent.VK_BACK_SPACE && !text.equals("")) {
				tf.setText(text.substring(0, text.length() - 1));
			}
		}
	}
	
	private void btnSubmitClick() {
		String name = nameField.getText();
		String pin = pinField.getText();
		
		boolean validName = Sanitizer.sanitizeUsername(name);
		boolean validPin = Sanitizer.sanitizePin(pin);
				
		if (validName && validPin) {
			Account newAccount = new Account(Application.getUser().getId(), name, pin);
			if (Application.addAccount(newAccount)) {
				home.update();
				lblStatus.setText("Account created");
				btnSubmit.setEnabled(false);
				nameField.setEnabled(false);
				enabled = false;
			}
		} else {
			lblStatus.setText("Invalid name or pin");
		}
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 475, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHead = new JLabel("Create Account");
		lblHead.setHorizontalAlignment(SwingConstants.LEFT);
		lblHead.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblHead.setBounds(140, 40, 165, 51);
		contentPane.add(lblHead);
		
		pinField = new JTextField();
		pinField.setHorizontalAlignment(SwingConstants.CENTER);
		pinField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		pinField.setEditable(false);
		pinField.setBackground(Color.WHITE);
		pinField.setBounds(153, 224, 93, 22);
		pinField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				formatPin(pinField, arg0);
			}
		});
		contentPane.add(pinField);
		
		nameField = new JTextField();
		nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		nameField.setColumns(10);
		nameField.setBounds(153, 164, 217, 34);
		contentPane.add(nameField);
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblStatus.setBounds(50, 117, 358, 34);
		contentPane.add(lblStatus);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSubmitClick();
			}
		});
		btnSubmit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSubmit.setBounds(153, 275, 97, 25);
		contentPane.add(btnSubmit);
		
		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblName.setBounds(101, 165, 40, 34);
		contentPane.add(lblName);
		
		lblPin = new JLabel("4 digit pin");
		lblPin.setHorizontalAlignment(SwingConstants.LEFT);
		lblPin.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPin.setBounds(80, 212, 61, 34);
		contentPane.add(lblPin);
	}
}
