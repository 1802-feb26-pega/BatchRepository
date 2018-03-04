import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -465150837652436807L;
	
	private String name;
	private String email;
	private String password;
	private float balance;
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		
		balance = 0.0f;
	}
	
	public void deposit(float amount) {
		balance += amount;
	}
	
	public boolean withdraw(float amount) {
		if (balance < amount) {
			return false;
		}
		
		balance -= amount;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("name->%S email->%s balance->$%.2f", name, email, balance);
	}
	
	public String getName() { return name; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public float getBalance() { return balance; }
}
