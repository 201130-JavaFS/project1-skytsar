package com.revature.models;

public class RequestDTO {
	public double amount;
	public String description;
	public int type;
	public RequestDTO(double amount, String description, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
	}
	public RequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
