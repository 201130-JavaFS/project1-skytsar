package com.revature.dao;

import java.util.List;

import com.revature.models.*;


public interface ErsDAO {
	public User login(String username, String password);
	public List<Request> getRequests();
	public void addRequest(Request req);
	public void addRequest(double ammount, String description,int authorID, int typeID);

}
