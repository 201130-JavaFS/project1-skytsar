package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.revature.controllers.AvengersController;
import com.revature.controllers.LoginController;
import com.revature.controllers.RequestController;


public class MasterServlet extends HttpServlet {

	//private AvengersController ac = new AvengersController();
	private LoginController lc = new LoginController();
	private RequestController rc = new RequestController();
	private static Logger log = LogManager.getRootLogger();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json");
		// By default tomcat will send back a successful status code if it finds a
		// servlet method.
		// Because all requests will hit this method, we are defaulting to not found and
		// will override for success requests.
		res.setStatus(404);

		
		final String URI = req.getRequestURI().replace("/project-1/", "");

		switch (URI) {
		
		case "login":
			log.info("logging in");
			lc.login(req, res);
			break;
		case "addRequest":
			rc.addRequest(req, res);
			break;
		case "changeRequest":
			break;
		case "getRequests":
			break;
		}
		
			
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
