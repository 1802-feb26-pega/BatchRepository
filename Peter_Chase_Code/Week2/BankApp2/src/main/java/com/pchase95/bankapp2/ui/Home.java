package com.pchase95.bankapp2.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.pchase95.bankapp2.driver.Application;
import com.pchase95.bankapp2.pojos.Account;
import javax.swing.SwingConstants;

public class Home extends JFrame {
	private static final long serialVersionUID = 1049905457465948171L;
	
	private JPanel contentPane;
	private JLabel lblHead;
	private JButton btnCreateAccount;
	private JButton btnSignOut;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JList list;
	private JButton button;
	
	private DefaultListModel<IDString> model;
	private JLabel lblStatus;
	
	public Home() {
		initialize();
		update();
	}
	
	@SuppressWarnings("unchecked")
	public void update() {
		List<Account> accounts = Application.fetchUserAccounts();

		model = new DefaultListModel<>();
		for (final Account account : accounts) {
			IDString title = new IDString(
					account.getId(), String.format("Account #%d - %s", account.getId(), account.getName()));
			model.addElement(title);
		}
		list = new JList(model);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void submitBtnClick() {
		long id = model.get(list.getSelectedIndex()).getId();
		String pin = JOptionPane.showInputDialog(null, "Enter 4 digit pin");
		Account account = Application.getAccountById(id);
		if (pin.equals(account.getPin())) {
			WindowController.launchBank(id, this);
			lblStatus.setText("");
		} else {
			lblStatus.setText("Wrong Pin");
		}
	}
	
	private void btnCreateAccountClick() {
		WindowController.launchCreateAccount(this);		
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHead = new JLabel("Choose an Account");
		lblHead.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblHead.setBounds(174, 13, 220, 51);
		contentPane.add(lblHead);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCreateAccountClick();
			}

		});
		btnCreateAccount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnCreateAccount.setBounds(189, 457, 182, 34);
		contentPane.add(btnCreateAccount);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Application.logOut();
				dispose();
			}
		});
		btnSignOut.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSignOut.setBounds(12, 13, 97, 25);
		contentPane.add(btnSignOut);
		
		panel = new JPanel();
		panel.setBounds(134, 117, 303, 284);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitBtnClick();
			}
		});
		button.setFont(new Font("SansSerif", Font.PLAIN, 16));
		button.setBounds(236, 419, 97, 25);
		contentPane.add(button);
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblStatus.setBounds(92, 70, 358, 34);
		contentPane.add(lblStatus);
	}
}
