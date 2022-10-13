package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		
		logger.info("*********TC001_Get_All_Employees*********");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		
		Thread.sleep(3);
		}
	@Test
	void checkResponseBody() {
		logger.info("******Response Body******");
		String responseBody = response.getBody().asString();
		logger.info(" Response Body : " + responseBody);
		Assert.assertTrue(responseBody!=null);
	} 
	
	@Test
	void checkStatusCode() {
		logger.info("******Status Code******");
		int statusCode = response.getStatusCode();
		logger.info("Status code is :" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("******Check Response Time******");
		long responseTime = response.getTime();
		logger.info("Response Time : " + responseTime);
		
		if (responseTime>2000)
		
			logger.warn("Response time is greater than 5000");
			Assert.assertTrue(responseTime<5000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("******Check Status Line******");
		String statusLine = response.getStatusLine();
		logger.info("Status line : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("******Checking Content Type******");
		String contentType = response.header("Content-Type");
		logger.info("Content Type : " + contentType);
		Assert.assertEquals(contentType, "application/json");
	}
	
	@Test
	void checkServertype() {
		logger.info("******Checking Server Type******");
		String serverType = response.header("Server");
		logger.info("Server Type : " + serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");
	}
	
	//@Test
	void checkContentLength() {
		logger.info("******Check Content Length******");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length : " + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength)>50);
	}
	
	@Test
	void checkCookies() {
		logger.info("******Check Cookies******");
		
		String cookie = response.getCookie("PHPSESSID");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("******Finished TC001_Get_All_Employees******");
	}
	
}
