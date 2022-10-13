package com.employeeapi.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Level;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "15"; // Hard coded input to get details of one employee and update employee data
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = logger.getLogger("EmployeeRestAPI"); // logger added
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

}
