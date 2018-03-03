import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Application {

	private JFrame frame;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblPetesBank;
	private JLabel lblLogin;
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}
	
	private void btnSignUpClick() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp signUp = new SignUp();
					signUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	private void btnSignInClick() {
		String username = textFieldUsername.getText();
		String password = textFieldPassword.getText();
		textFieldUsername.setText("");
		textFieldPassword.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblPetesBank = new JLabel("Pete's Bank");
		lblPetesBank.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblPetesBank.setBounds(191, 50, 141, 51);
		frame.getContentPane().add(lblPetesBank);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblLogin.setBounds(190, 108, 71, 51);
		frame.getContentPane().add(lblLogin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textFieldUsername.setBounds(190, 165, 192, 34);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textFieldPassword.setBounds(190, 212, 192, 34);
		frame.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		btnSignIn = new JButton("Submit");
		btnSignIn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSignIn.setBounds(190, 259, 97, 25);
		frame.getContentPane().add(btnSignIn);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignUpClick();
			}
		});
		btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnSignUp.setBounds(191, 362, 109, 34);
		frame.getContentPane().add(btnSignUp);
		
		lblUsername = new JLabel("Username / Email");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUsername.setBounds(71, 166, 107, 34);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPassword.setBounds(71, 213, 59, 34);
		frame.getContentPane().add(lblPassword);
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblStatus.setBounds(107, 297, 297, 34);
		frame.getContentPane().add(lblStatus);
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSignInClick();
			}
		});
	}
}
