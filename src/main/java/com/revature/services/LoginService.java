package com.revature.services;

import com.revature.dao.*;
import com.revature.models.User;

public class LoginService {
	ErsDAO eDao=new ErsDaoImpl();
	User user=null;
	/*
	 * This is a placeholder file created simple to maintain the Maven file structure on GitHub. 
	 * You may immediately delete this file from your project, you will not require it. 
	 */
	public boolean login(String username, String password) {
		System.out.println("loginservice reached");
		//password=user.encrypt(password);
		user= eDao.login(username, password);
		if(user!=null) {
			return true;
		}
		return false;
	}
	
	

}
