package BankInside.pojos;

public class User {
	private String firstName;
	private String lastName;
	private String password;
	private String username;
	private int user_id;
	
	


	public User() {
		
	}
	
	public User(String firstName, String lastName, String password, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
	}
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
