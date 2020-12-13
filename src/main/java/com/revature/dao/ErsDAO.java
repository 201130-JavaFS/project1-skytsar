package com.revature.dao;

import com.revature.models.*;

public interface ErsDAO {
	public User login(String username, String password);
	public List<Request> getRequests();

}
