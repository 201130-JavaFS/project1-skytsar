package com.revature.controllers;



import java.io.BufferedReader;
import java.io.IOException;

import javax.management.relation.RelationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.*;


public class LoginController {
	
	private ObjectMapper om = new ObjectMapper();
	private LoginService ls = new LoginService();
	private static Logger log = LogManager.getRootLogger();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			
			StringBuilder sb = new StringBuilder();
			
			String line = reader.readLine();
			
			while(line!=null) {
				sb.append(line);
				line=reader.readLine();
			}
			
			String body = new String(sb);
			
			LoginDTO lDTO = om.readValue(body, LoginDTO.class);
			
			if(ls.login(lDTO.username, lDTO.password)) {
				HttpSession ses = req.getSession();
				
				
				ses.setAttribute("user",ls.getUser(lDTO.username, lDTO.password));
				
				ses.setAttribute("loggedin", true);
				String json = om.writeValueAsString(ses.getAttribute("user"));
				res.getWriter().print(json);
				
				res.setStatus(200);
				res.getWriter().print("Login Successful");
			}else {
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().print("Login failed");
			}
		}else if(req.getMethod().equals("GET")) {
			//This shows logging in with Query Params solely for the
			//example of using said parameters. Do not do this!
			
			if(req.getParameterMap().containsKey("username") &&
					req.getParameterMap().containsKey("password")) {
				
				if(ls.login(req.getParameter("username"), req.getParameter("password"))) {
					HttpSession ses = req.getSession(true);
					
					ses.setAttribute("loggedin", true);
					
					res.setStatus(200);
					res.getWriter().print("Login Successful");
				}else {
					HttpSession ses = req.getSession(false);
					if (ses != null) {
						ses.invalidate();
					}
					res.setStatus(401);
					res.getWriter().print("Login failed");
				}
			}
		}
	}

	
}
