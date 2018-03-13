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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.pchase95.bankapp2.driver.Application;
import com.pchase95.bankapp2.pojos.Account;

public class Bank extends JFrame {
	private static final long serialVersionUID = -6882150152101214273L;
	
	private JPanel contentPane;
	private JLabel lblBalance;
	private JTextField moneyField;
	private JButton btnWidthdraw;
	private JButton btnDeposit;
	private JLabel lblSuccess;
	private JLabel lblValidValue;
	private JButton btnDeleteAccount;
	private JButton btnTransfer;
	
	private Account account;
	private Home home;
	
	public Bank(long accountId, Home home) {
		this.account = Application.getAccountFromDB(accountId);
		this.home = home;
		initialize();
	}
	
	private void formatMoney(JTextField tf, KeyEvent e) {
		char ch = e.getKeyChar();
		int code = e.getKeyCode();
		String text = tf.getText();
		
		if (ch >= '0' && ch <= '9'
		|| code == KeyEvent.VK_PERIOD && !text.contains(".")) {
			tf.setText(tf.getText() + ch);
		}
		
		if (code == KeyEvent.VK_BACK_SPACE && !text.equals("")) {
			tf.setText(text.substring(0, text.length() - 1));
		}
	}
	
	private void updateBalance() {
		lblBalance.setText(String.format("$%.2f", account.getBalance()));
	}
	
	private void btnTransferClick() {
		double amount = parseField();
		if (amount != 0.0) {
			try {
				long recipientAccountId = Long.parseLong(
						JOptionPane.showInputDialog(null, "Enter recipient accound id"));
				Application.transfer(account, amount, recipientAccountId);
				lblSuccess.setText("Transfer Success");
			} catch (NumberFormatException e) {
				lblSuccess.setText("Invalid id");
			}
		}
	}

	private void btnDeleteAccountClick() {
		int reply = JOptionPane.showConfirmDialog(
			null,
			"Are you sure you want to delete your account?",
			"Delete Account",
			JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION) {
			Application.deleteAccount(account);
			home.update();
			dispose();
		}
	}
	
	private void btnWithdrawClick() {
		double amount = parseField();
		if (amount != 0.0) {
			boolean success = Application.withdraw(account, amount);
			if (success) {
				lblSuccess.setText("");
				updateBalance();
			} else {
				lblSuccess.setText("Insufficient Funds");
			}			
		}
	}
	
	private void btnDepositClick() {
		double amount = parseField();
		if (amount != 0.0) {
			Application.deposit(account, amount);
			updateBalance();			
		}
	}
	
	private double parseField() {
		lblSuccess.setText("");
		lblValidValue.setText("");
		String text = moneyField.getText();
		double amount = 0.0;
		try {
			if (!text.equals("")) {
				if (text.contains(".")) {
					amount = Double.parseDouble(text);
				} else {
					amount = (double)Integer.parseInt(text);
				}
			}
		} catch (NumberFormatException e) {
			lblValidValue.setText("Amount too large");
		}
		moneyField.setText("");
		return amount;
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBalance = new JLabel("Balance");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setFont(new Font("SansSerif", Font.PLAIN, 36));
		lblBalance.setBounds(40, 55, 476, 54);
		contentPane.add(lblBalance);
		
		moneyField = new JTextField();
		moneyField.setEditable(false);
		moneyField.setBackground(Color.WHITE);
		moneyField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				formatMoney(moneyField, arg0);
			}
		});
		moneyField.setHorizontalAlignment(SwingConstants.CENTER);
		moneyField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		moneyField.setBounds(142, 186, 235, 22);
		contentPane.add(moneyField);
		
		btnWidthdraw = new JButton("Withdraw");
		btnWidthdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnWithdrawClick();
			}
		});
		btnWidthdraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnWidthdraw.setBounds(40, 243, 124, 25);
		contentPane.add(btnWidthdraw);
		
		btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDepositClick();
			}
		});
		btnDeposit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnDeposit.setBounds(195, 243, 132, 25);
		contentPane.add(btnDeposit);
		
		lblSuccess = new JLabel("");
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSuccess.setBounds(88, 148, 359, 25);
		contentPane.add(lblSuccess);
		
		lblValidValue = new JLabel("");
		lblValidValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidValue.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblValidValue.setBounds(152, 122, 225, 25);
		contentPane.add(lblValidValue);
		
		btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTransferClick();
			}
		});
		btnTransfer.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnTransfer.setBounds(356, 243, 132, 25);
		contentPane.add(btnTransfer);
		
		btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDeleteAccountClick();
			}
		});
		btnDeleteAccount.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnDeleteAccount.setBounds(12, 15, 171, 25);
		contentPane.add(btnDeleteAccount);
		updateBalance();
	}
}
