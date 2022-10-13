package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
		@BeforeClass
		void getEmployeeData() throws InterruptedException {
			
			logger.info("*********TC001_Get_Single_Employee*********");
			RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
			httpRequest = RestAssured.given();
			response = httpRequest.request(Method.GET, "/employee/" + empID);
			
			Thread.sleep(3);
			}
		
		@Test
		void checkResponseBody() {
			logger.info("******Response Body******");
			String responseBody = response.getBody().asString();
			Assert.assertEquals(responseBody.contains(empID), true);
		} 
		
		@Test
		void checkStatusCode() {
			logger.info("******Status Code******");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
		}
		
		@Test
		void checkResponseTime() {
			logger.info("******Check Response Time******");
			long responseTime = response.getTime();
			Assert.assertTrue(responseTime<5000);
		}
		
		@Test
		void checkStatusLine() {
			logger.info("******Check Status Line******");
			String statusLine = response.getStatusLine();
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		}
		
		@Test
		void checkContentType() {
			logger.info("******Checking Content Type******");
			String contentType = response.header("Content-Type");
			Assert.assertEquals(contentType, "application/json");
		}
		
		@Test
		void checkServertype() {
			logger.info("******Checking Server Type******");
			String serverType = response.header("Server");
			logger.info("Server Type : " + serverType);
			Assert.assertEquals(serverType, "nginx/1.21.6");
		}
		
		@Test
		void checkContentLength() {
			logger.info("******Check Content Length******");
			String contentLength = response.header("Content-Length");
			Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		}

		@AfterClass
		void tearDown() {
			logger.info("******Finished TC001_Get_All_Employees******");
		}

}
