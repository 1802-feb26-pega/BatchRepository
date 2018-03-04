import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {
	private static final long serialVersionUID = 2742534148693075976L;
	
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JLabel lblSignUp;
	private JButton btnSubmit;
	private JLabel lblUsername;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblUsernameStatus;
	private JLabel lblEmailStatus;
	private JLabel lblPasswordStatus;
	private JLabel lblSuccess;
	private JTextField passwordField;

	public SignUp() {
		initialize();
	}
		
	private void btnSignInClick() {
		String username = nameTextField.getText();
		String email = emailTextField.getText();
		String password = passwordField.getText();
		boolean validUsername = Utils.sanitizeUsername(username);
		boolean validEmail = Utils.sanitizeEmail(email);
		boolean validPassword = Utils.sanitizePassword(password);
		
		if (!validUsername) {
			lblUsernameStatus.setText("Can only contain letters and numbers");
		} else {
			lblUsernameStatus.setText("");
		}
		
		if (!validEmail) {
			lblEmailStatus.setText("Invalid email format");
		} else {
			lblEmailStatus.setText("");
		}
		
		if (!validPassword) {
			lblPasswordStatus.setText("Must contain letters and numbers");
		} else {
			lblPasswordStatus.setText("");
		}
				
		
		if (validUsername && validEmail && validPassword) {
			Application.addUser(new User(
				username, email, password));
			
			btnSubmit.setEnabled(false);
			nameTextField.setEnabled(false);
			emailTextField.setEnabled(false);
			passwordField.setEnabled(false);
			lblSuccess.setText("Account created successfully");
		}
	}
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblEmail.setBounds(61, 164, 55, 21);
		contentPane.add(lblEmail);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUsername.setBounds(61, 108, 78, 21);
		contentPane.add(lblUsername);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		nameTextField.setBounds(151, 107, 174, 22);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		emailTextField.setColumns(10);
		emailTextField.setBounds(151, 163, 174, 22);
		contentPane.add(emailTextField);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblSignUp.setBounds(166, 27, 102, 32);
		contentPane.add(lblSignUp);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignInClick();
			}
		});
		btnSubmit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSubmit.setBounds(153, 269, 97, 25);
		contentPane.add(btnSubmit);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPassword.setBounds(61, 224, 69, 21);
		contentPane.add(lblPassword);
		
		lblUsernameStatus = new JLabel("");
		lblUsernameStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblUsernameStatus.setBounds(61, 85, 264, 21);
		contentPane.add(lblUsernameStatus);
		
		lblEmailStatus = new JLabel("");
		lblEmailStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblEmailStatus.setBounds(61, 142, 264, 21);
		contentPane.add(lblEmailStatus);
		
		lblPasswordStatus = new JLabel("");
		lblPasswordStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPasswordStatus.setBounds(61, 198, 264, 21);
		contentPane.add(lblPasswordStatus);
		
		lblSuccess = new JLabel("");
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSuccess.setBounds(77, 301, 264, 21);
		contentPane.add(lblSuccess);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		passwordField.setColumns(10);
		passwordField.setBounds(151, 223, 174, 22);
		contentPane.add(passwordField);
	}
}
