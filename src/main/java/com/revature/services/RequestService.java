package com.revature.services;

import java.util.List;

import com.revature.dao.ErsDAO;
import com.revature.dao.ErsDaoImpl;
import com.revature.models.Request;

public class RequestService {
	ErsDAO eDao=new ErsDaoImpl();
	
	public List<Request> getAll(){
		return eDao.getRequests();
	}
	
	public void addRequest(double ammount, String description,int authorID, int typeID) {
		eDao.addRequest(ammount, description, authorID, typeID);
	}
	public List<Request> getEmployeeRequests(int userID){
		return eDao.getUsersRequests(userID);
	}
	public void updateRequest(int reqID,int newStat, int resolverID ) {
		
		eDao.updateRequest(reqID, newStat, resolverID);
		
	}
	
}
