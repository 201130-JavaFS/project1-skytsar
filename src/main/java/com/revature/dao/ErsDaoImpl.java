package com.revature.dao;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.*;
import com.sql.*;


public class ErsDaoImpl implements ErsDAO{
	@Override
	public User login(String username, String password) {
		Connection connection=null;
		User user=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		String sql;
		try {
			
			connection=PostgresSqlConnection.getConnection();
			
			sql="SELECT * from MoneyBack.user where username=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			//Step 4 - Execute Query
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),
						rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getInt("role_id"));
				//log.trace("User retreived");
			}
			else
			//log.trace("Invalid username or password");
			System.out.println("Invalid");
		} catch (ClassNotFoundException e) {
			//log.error("ClassNotFound Error");
			//log.trace(e);
		} catch (SQLException e) {
			//log.error("SQL Error");
			//log.trace(e);
		}
		finally {
			try {
			
			connection.close();
			} catch (SQLException e) {
				//log.error(e);
			}
		}
		return user;
	}
	
	List<Request> getRequests(){
		
	}

}
