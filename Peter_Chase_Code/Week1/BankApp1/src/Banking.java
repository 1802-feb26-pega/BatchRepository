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

public class Banking extends JFrame {
	private static final long serialVersionUID = -6882150152101214273L;
	
	private JPanel contentPane;
	private JLabel lblBalance;
	private JLabel lblWithdraw;
	private JLabel lblDeposit;
	private JTextField withdrawField;
	private JTextField depositField;
	private JButton btnWidthdraw;
	private JButton btnDeposit;
	private JLabel lblSuccess;
	private JButton btnSignOut;
	
	public Banking() {
		initialize();
	}
	
	private void formatMoney(JTextField tf, KeyEvent e) {
		char c = e.getKeyChar();
		String text = tf.getText();
		
		if (c >= '0' && c <= '9'
		|| e.getKeyCode() == KeyEvent.VK_PERIOD && !text.contains(".")) {
			tf.setText(tf.getText() + c);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !text.equals("")) {
			tf.setText(text.substring(0, text.length() - 1));
		}
	}
	
	private void updateBalance() {
		lblBalance.setText(String.format("$%.2f", Application.getUser().getBalance()));
	}
	
	private void btnWithdrawClick() {
		double amount = parseField(withdrawField);
		boolean success = Application.getUser().withdraw(amount);
		
		if (success) {
			lblSuccess.setText("");
		} else {
			lblSuccess.setText("Insufficient Funds");
		}
		
		updateBalance();
		if (amount != 0.0f) {
			Application.writeUsers();
		}
	}
	
	private void btnDepositClick() {
		double amount = parseField(depositField);
		Application.getUser().deposit(amount);
		updateBalance();
		if (amount != 0.0f) {
			Application.writeUsers();
		}
	}
	
	private double parseField(JTextField tf) {
		lblSuccess.setText("");
		String text = tf.getText();
		double amount = 0.0;
		if (!text.equals("")) {
			if (text.contains(".")) {
				amount = Double.parseDouble(text);
			} else {
				amount = (double)Integer.parseInt(text);
			}
		}
		tf.setText("");
		return amount;
	}
	
	private void btnSignOutClick() {
		Application.logOut();
		dispose();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		lblWithdraw = new JLabel("Withdraw");
		lblWithdraw.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithdraw.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblWithdraw.setBounds(61, 156, 121, 39);
		contentPane.add(lblWithdraw);
		
		lblDeposit = new JLabel("Deposit");
		lblDeposit.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeposit.setFont(new Font("SansSerif", Font.PLAIN, 26));
		lblDeposit.setBounds(331, 156, 121, 39);
		contentPane.add(lblDeposit);
		
		withdrawField = new JTextField();
		withdrawField.setEditable(false);
		withdrawField.setBackground(Color.WHITE);
		withdrawField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				formatMoney(withdrawField, arg0);
			}
		});
		withdrawField.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		withdrawField.setBounds(40, 208, 181, 22);
		
		contentPane.add(withdrawField);
		
		depositField = new JTextField();
		depositField.setEditable(false);
		depositField.setBackground(Color.WHITE);
		depositField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				formatMoney(depositField, arg0);
			}
		});
		depositField.setHorizontalAlignment(SwingConstants.CENTER);
		depositField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		depositField.setBounds(305, 208, 181, 22);
		contentPane.add(depositField);
		
		btnWidthdraw = new JButton("Submit");
		btnWidthdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnWithdrawClick();
			}
		});
		btnWidthdraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnWidthdraw.setBounds(73, 243, 97, 25);
		contentPane.add(btnWidthdraw);
		
		btnDeposit = new JButton("Submit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDepositClick();
			}
		});
		btnDeposit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnDeposit.setBounds(341, 243, 97, 25);
		contentPane.add(btnDeposit);
		
		lblSuccess = new JLabel("");
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSuccess.setBounds(50, 281, 147, 25);
		contentPane.add(lblSuccess);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignOutClick();
			}
		});
		btnSignOut.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSignOut.setBounds(432, 13, 97, 25);
		contentPane.add(btnSignOut);
		updateBalance();
	}
}
