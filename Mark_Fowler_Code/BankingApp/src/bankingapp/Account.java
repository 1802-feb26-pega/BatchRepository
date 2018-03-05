
package bankingapp;
import java.util.Scanner;

public class Account {
    
    Scanner sc = new Scanner(System.in);
    
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private double balance;
    
    public Account() {
        super();
    }
    
    public Account(String firstName, String lastName, String username,
            String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = 0.00;
    }
    
    // First name getters/setters
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    // Last name getters/setters
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + username + ", " +
                password + ", " + balance + "\n";
    }
    
}
