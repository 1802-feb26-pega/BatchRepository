package com.trms.dao;

import java.util.ArrayList;
import java.util.HashMap;

//import com.bank.pojos.Account;
import com.trms.pojos.User;

public interface DAO {
//	public HashMap<Integer, String> getEmails();
//	public User getUserByUsernameAndPass(String uname, String pwd);
//	public List<User> getAllUsers();
//	public User getUserById(int id);
//	public Account createAccount(User u, double initialBal);
//	public ArrayList<Account> getAccountsByUser(User u);
//	public double getBalance(int id);
//	public void updateBalance(int id, double amt);
	public User getUserByUsername(String username);
	public int addUser(String fn,String ln,String un,String pass,String bday,String addr,String zip,String title,int sup,int head,int ben,double app,double pen);
}
