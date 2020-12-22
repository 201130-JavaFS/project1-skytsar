package com.revature.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.*;
import com.revature.sql.*;


public class ErsDaoImpl implements ErsDAO{
	
	private static Logger log = LogManager.getRootLogger();
	
	public User login(String username, String password) {
		
		//Connection connection=null;
		User user=new User();
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		String sql;
		try (Connection connection=PostgresSqlConnection.getConnection()){
			
			
			
			sql="SELECT * from moneyback.user where username=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			password=user.encrypt(password);
			preparedStatement.setString(2, password);
			//Step 4 - Execute Query
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("email"),
						rs.getInt("role_id"));
				//log.trace("User retrieved");
				user.setPassword(user.decrypt(user.getPassword()));
			}
			else {
			//log.trace("Invalid user name or password");
			user=null;
			System.out.println("Invalid came back empty");
			}
		} catch (ClassNotFoundException e) {
			//log.error("ClassNotFound Error");
			//log.trace(e);
			System.out.println(e);
		} catch (SQLException e) {
			//log.error("SQL Error");
			//log.trace(e);
			System.out.println(e);
			return null;
		}
				return user;
	}
	
	public void addRequest(Request req) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String sql ="INSERT into MoneyBack.ers_requests (reimb_id, reimb_ammount, submitted, resolved, description, author, resolver, status_id, type_id )"
				+ "  VALUES (default, ?, current_timestamp, null, ?, ?, null, ?, ?)";
		
		try {
			connection=PostgresSqlConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, req.getAmmount());
			//preparedStatement.setTime(2, current_timestamp);
			preparedStatement.setString(2, req.getDescription());
			preparedStatement.setInt(3, req.getAuthorID());
			preparedStatement.setInt(4,1);
			preparedStatement.setInt(5, req.getTypeID());
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			//log.error("ClassNotFound Error");
			//log.trace(e);
		}
		finally {
			try {
			
			connection.close();
			} catch (SQLException e) {
				//log.error(e);
			}
		}
	}
	public void addRequest(double ammount, String description,int authorID, int typeID) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		if(ammount<0) {
			log.error("Negative cash ammount, will not push");
		}
		else {
			ammount=this.validMoney(ammount);
		String sql ="INSERT into MoneyBack.ers_requests (reimb_id, reimb_ammount, submitted, resolved, description, author, resolver, status_id, type_id )"
				+ "  VALUES (default, ?, current_timestamp, null, ?, ?, null, ?, ?)";
		
		try {
			connection=PostgresSqlConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, ammount);
			//preparedStatement.setTime(2, current_timestamp);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, authorID);
			preparedStatement.setInt(4,1);
			preparedStatement.setInt(5, typeID);
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			//log.error("ClassNotFound Error");
			//log.trace(e);
		}
		finally {
			try {
			
			connection.close();
			} catch (SQLException e) {
				//log.error(e);
			}
		}
		}
	}
	public void updateRequest(Request req) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String sql = "UPDATE MoneyBack.ers_requests SET resolved=current_timestamp, resolver=?,status_id=? WHERE reimb_id=?";
		
		try {
			connection=PostgresSqlConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setTime(1, req.resolveTime);
			preparedStatement.setInt(1, req.getResolverID());
			preparedStatement.setInt(2, req.getStatusID());
			preparedStatement.setInt(3, req.getId());
			preparedStatement.execute();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void updateRequest(int requestID, int statusID,int resolverID) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String sql = "UPDATE MoneyBack.ers_requests SET resolved=current_timestamp, resolver=?,status_id=? WHERE reimb_id=?";
		
		try {
			connection=PostgresSqlConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setTime(1, req.resolveTime);
			preparedStatement.setInt(1, resolverID);
			preparedStatement.setInt(2, statusID);
			preparedStatement.setInt(3, requestID);
			preparedStatement.execute();
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public List<Request> getRequests(){
		String sql="SELECT * from MoneyBack.ers_requests";
		
		Request request=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		List<Request> output= new ArrayList();
		try(Connection connection=PostgresSqlConnection.getConnection();) {
			
			preparedStatement = connection.prepareStatement(sql);
			//Step 4 - Execute Query
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				request = new Request(rs.getInt("reimb_id"),
						rs.getDouble("reimb_ammount"),
						rs.getTime("submitted"),
						rs.getTime("resolved"),
						rs.getString("description"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getInt("status_id"),
						rs.getInt("type_id"));
				//log.trace("User retrieved");
				output.add(request);
			}
			connection.close();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		return output;
	}
	
	
	public List<Request> getUsersRequests(int userid){
		String sql="SELECT * from MoneyBack.ers_requests WHERE author="+userid;
		Connection connection=null;
		Request request=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		List<Request> output= new ArrayList();
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			//Step 4 - Execute Query
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				request = new Request(rs.getInt("reimb_id"),
						rs.getDouble("reimb_ammount"),
						rs.getTime("submitted"),
						rs.getTime("resolved"),
						rs.getString("description"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getInt("status_id"),
						rs.getInt("type_id"));
				//log.trace("User retrieved");
				output.add(request);
			}
			
		}
		catch(SQLException e){
			System.out.println(e);
		}
		finally {
			try {
			
			connection.close();
			} catch (SQLException e) {
				//log.error(e);
			}
		}
		
		
		return output;
	}
	public List<Request> getOneRequests(int id){
		String sql="SELECT * from MoneyBack.ers_requests WHERE reimb_id="+id;
		//Connection connection=null;
		Request request=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		List<Request> output= new ArrayList();
		try (Connection connection=PostgresSqlConnection.getConnection();){
			
			preparedStatement = connection.prepareStatement(sql);
			
			//Step 4 - Execute Query
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				request = new Request(rs.getInt("reimb_id"),
						rs.getDouble("reimb_ammount"),
						rs.getTime("submitted"),
						rs.getTime("resolved"),
						rs.getString("description"),
						rs.getInt("author"),
						rs.getInt("resolver"),
						rs.getInt("status_id"),
						rs.getInt("type_id"));
				//log.trace("User retrieved");
				output.add(request);
			}
			try {
			
			connection.close();
			} catch (SQLException e) {
				//log.error(e);
				System.out.println(e);
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	public double validMoney(double input) {
		int temp = (int)(input*100.0);
	    double value = ((double)temp)/100.0;
	    return value;
	}

}
