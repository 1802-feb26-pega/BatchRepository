package com.ex.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.trms.dao.DAO;
import com.trms.dao.DAOImpl;
//import com.trms.pojos.Account;
import com.trms.pojos.User;

public class Service {
	static DAO dao = new DAOImpl();
	
	public User login(String username, String password)
	{
		String passHash = null;
		MessageDigest digest;
		try
		{//HASHING PASSWORD - SHA512 ************************************
			digest = MessageDigest.getInstance("SHA-512");
			byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			
			StringBuffer hexString = new StringBuffer();
		    for (int i = 0; i < encodedHash.length; i++)
		    {
		    	String hex = Integer.toHexString(0xff & encodedHash[i]);
		    	if(hex.length() == 1) hexString.append('0');
		        hexString.append(hex);
		    }
		    passHash = hexString.toString();
		    //System.out.println(passHash);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		
		User u = dao.getUserByUsername(username);
		if(u==null)
		{
			System.out.println("null in service");
			return null;
		}else if(u.getPassword().equals(passHash))
		{
			return u;
		}else
		{
			return null;
		}
		
	}//login
	
	public boolean usernameExists(String un)
	{
		User u = dao.getUserByUsername(un);
		
		if(u == null)
		{
			return false;	//return true if the user is not found --> email/username available
		}else
		{
			return true;
		}
	}//email exists
	
	public int addUser(User u)
	{
		return dao.addUser(u.getFirstname(),u.getLastname(),u.getUsername(),u.getPassword(),u.getAddress(),u.getZipcode(),u.getTitle(),u.getSupervisorId(),
				u.getDeptHeadId(),u.getBenCoId(),u.getApprovedFunds(),u.getPendingFunds());
	}//addUser

/*
	public ArrayList<Account> getAccounts(User u)
	{
		return dao.getAccountsByUser(u);
	}//getAccounts
*/
	
}