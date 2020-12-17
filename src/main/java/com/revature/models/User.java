package com.revature.models;
import com.revature.models.Request;
public class User {
	public int id;
	public String username;
	private String password;
	public String firstName;
	public String lastName;
	public String email;
	public int role;
	
	/*Request newRequest(double ammount, String description,int typeID) {
		return Request(null,ammount,);
	}*/
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public User(int id, String uname, String pword, String lname,String fname,String email,int roleID) {
		super();
		this.id=id;
		this.username=uname;
		this.password=pword;
		this.firstName=fname;
		this.lastName=lname;
		this.email=email;
		this.role=roleID;
		
		
	}
	
	public String encrypt(String password) {
		String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer result= new StringBuffer();
		char[] passwordChars=password.toCharArray();
		for(char letter: passwordChars) {
			char uppered=Character.toUpperCase(letter);
			int location=letters.indexOf(uppered);
			location=location+5;
			char newletter=letters.charAt(location);
			if(Character.isLowerCase(letter)) {
				Character.toLowerCase(newletter);
			}
			result.append(newletter);
		}
		
		return result.toString();
		
	}
	public String decrypt(String rawPassword) {
		String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer result= new StringBuffer();
		char[] passwordChars=password.toCharArray();
		for(char letter: passwordChars) {
			char lowered=Character.toUpperCase(letter);
			int location=letters.lastIndexOf(lowered);
			location=location-5;
			char newletter=letters.charAt(location);
			if(Character.isLowerCase(letter)) {
				Character.toLowerCase(newletter);
			}
			result.append(newletter);
		}
		return result.toString();
	}

}
