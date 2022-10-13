package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase{
		
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empAge = RestUtils.empAge();
	String empSalary = RestUtils.empSal();
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		
		logger.info("*********TC004_Put_Employee_Record*********");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("Name", empName);
		requestParams.put("Age", empAge);
		requestParams.put("Salary", empSalary);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT, "/update/" + empID);
		
		Thread.sleep(5000);
		
	}
		
		@Test
		void checkResponseBody() {
			logger.info("******Response Body******");
			String responseBody = response.getBody().asString();
			 
			Assert.assertEquals(responseBody.contains(empName), true);
			Assert.assertEquals(responseBody.contains(empAge), true);
			Assert.assertEquals(responseBody.contains(empSalary), true);
		} 
			
		@Test
		void checkStatusCode() {
			logger.info("******Status Code******");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
		}
		
		@Test
		void checkStatusLine() {
			logger.info("******Check Status Line******");
			String statusLine = response.getStatusLine();
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		}
		
		@Test
		void checkServertype() {
			logger.info("******Checking Server Type******");
			String serverType = response.header("Server");
			logger.info("Server Type : " + serverType);
			Assert.assertEquals(serverType, "Apache");
		}
		
		
		@AfterClass
		void tearDown() {
			logger.info("******Finished TC004_Put_Employee_Record******");
		}


	

}
