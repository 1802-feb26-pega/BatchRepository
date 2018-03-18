package com.revature.middle;

import javax.naming.AuthenticationException;

import com.revature.backend.User;
import com.revature.data.Data;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
private static UserService INSTANCE = null;
	
	UserService(){}
	
	synchronized public static UserService getInstance(){
		if(INSTANCE == null)
			INSTANCE = new UserService();
		return INSTANCE;
	}
	
	User authenticate(String username, String password) throws AuthenticationException{
		
		Data data = new Data();
		User user = null;
		String hash = data.getHash(username);
		if(hash == null || !BCrypt.checkpw(password, hash)){ 
			System.out.println("User login failed");
			throw new AuthenticationException();
		}else{
			user = data.getUser(username);
			System.out.println("User login successful");
		}
		return user;
	}

}

