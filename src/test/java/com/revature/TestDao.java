package com.revature;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.revature.dao.ErsDaoImpl;

import static org.junit.Assert.assertEquals;

public class TestDao {
	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(ErsDaoImpl.class);
	      
	      
	      
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
}
