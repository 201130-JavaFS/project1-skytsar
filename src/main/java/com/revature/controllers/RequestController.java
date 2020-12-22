package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.Request;
import com.revature.models.RequestDTO;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.RequestService;

public class RequestController {
	private ObjectMapper om = new ObjectMapper();
	private RequestService rs = new RequestService();
	private static Logger log = LogManager.getRootLogger();

	public void addRequest(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			
			StringBuilder sb = new StringBuilder();
			
			String line = reader.readLine();
			
			while(line!=null) {
				sb.append(line);
				line=reader.readLine();
			}
			String body = new String(sb);
			RequestDTO ers= om.readValue(body, RequestDTO.class);
			
			
			try {
				HttpSession ses = req.getSession(false);
				if(ses!=null) {
					User user = (User) ses.getAttribute("user");
				rs.addRequest(ers.amount,ers.description,user.getId(),ers.type);
					res.setStatus(200);
					res.getWriter().print("Push successful");
				}
				else {
					res.setStatus(401);
					//e.printStackTrace();
					ses.invalidate();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				e.printStackTrace();
			}
			
		}
	}
	public void getRequests(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getMethod().equals("GET")) {
			try {
				HttpSession ses = req.getSession(false);
				if(ses!=null) {
					List<Request> list=null;
					User user = (User) ses.getAttribute("user");
					
					if(user.getRole()==1) {
						list=rs.getAll();
					}
					else
						list=rs.getEmployeeRequests(user.getId());
					String json = om.writeValueAsString(list);
					res.getWriter().print(json);
					res.setStatus(200);
					
				}
				else {
					res.setStatus(401);
					//e.printStackTrace();
					ses.invalidate();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				e.printStackTrace();
			}
			
		}
	}

}
