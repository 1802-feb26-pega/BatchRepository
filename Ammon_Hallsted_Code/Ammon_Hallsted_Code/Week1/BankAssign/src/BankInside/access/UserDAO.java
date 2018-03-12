package BankInside.access;

import java.util.List;

import BankInside.pojos.User;

public interface UserDAO {
	public void updateUser(User u);
	public void saveUser(User u);
	public User getUserbyEmail(String email);
	public User getUserbyId (int id);
	public List<User> getAllUsers();
}
