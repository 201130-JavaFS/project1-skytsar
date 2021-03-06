package com.revature.models;
import java.sql.Time;
import java.sql.Timestamp;

public class Request {
	private int id;
	private double ammount;
	private Time submitTime;
	private Time resolveTime;
	private String description;
	private int authorID;
	private int resolverID;
	private int statusID;
	private int typeID;
	
	
	public Request(int id, double ammount, Time submitTime, Time resolveTime, String description,
			int authorID, int resolverID, int statusID, int typeID) {
		super();
		this.id = id;
		this.ammount = ammount;
		this.submitTime = submitTime;
		this.resolveTime = resolveTime;
		this.description = description;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.statusID = statusID;
		this.typeID = typeID;
	}
	public Request(double ammount,  String description,
			int authorID, int typeID) {
		super();
		this.id = 0;
		this.ammount = ammount;
		this.submitTime = null;
		this.resolveTime = null;
		this.description = description;
		this.authorID = authorID;
		this.resolverID = 0;
		this.statusID = 0;
		this.typeID = typeID;
	}
	

	




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public Time getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Time submitTime) {
		this.submitTime = submitTime;
	}

	public Time getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime(Time resolveTime) {
		this.resolveTime = resolveTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getResolverID() {
		return resolverID;
	}

	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	

}
