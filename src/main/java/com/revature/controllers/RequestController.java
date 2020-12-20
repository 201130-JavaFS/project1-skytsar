package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.models.Request;
import com.revature.services.LoginService;
import com.revature.services.RequestService;

public class RequestController {
	private ObjectMapper om = new ObjectMapper();
	private RequestService rs = new RequestService();

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
			Request ers= om.readValue(body, Request.class);
			
			
			try {
				HttpSession ses = req.getSession(false);
				if(ses!=null) {
				rs.addRequest(ers.getAmmount(),ers.getDescription(),ers.getAuthorID(),ers.getTypeID());
					res.setStatus(200);
					res.getWriter().print("Push successful");
				}
				else {
					res.setStatus(401);
					//e.printStackTrace();
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
