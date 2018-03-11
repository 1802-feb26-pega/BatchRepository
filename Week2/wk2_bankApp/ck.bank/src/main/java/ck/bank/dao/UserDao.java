package ck.bank.dao;

import java.util.List;

import ck.bank.pojos.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getUserByUsername(String name);
	public int addUser(User newUser);
	//public int updateArtist(int id, Artist updatedArtist);
	public int removeUserByUsername(String name);
}
