package com.trms.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.trms.pojos.Request;
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
	public int addUser(String fn,String ln,String un,String pass,String addr,String zip,String title,int dept,double app,double pen);
	public int addRequest(String eType,Date sDate,Date eDate,String loc,String desc,
			double cost,int gStyleId,int grade,Date rDate,Timestamp t,int flaggedId,
			int appId,int uId);
	public List<Request> getRequestsByUser(User u);
}
