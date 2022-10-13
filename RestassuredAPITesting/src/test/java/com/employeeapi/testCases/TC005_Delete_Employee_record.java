package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
		@BeforeClass
		void getEmployeeData() throws InterruptedException {
			
			logger.info("*********TC005_Delete_Employee_Record*********");
			RestAssured.baseURI = "https://reqres.in/api";
			httpRequest = RestAssured.given();
			
			response = httpRequest.request(Method.GET, "/users");
			JsonPath jsonPathEvaluator = response.jsonPath();
			String empID = jsonPathEvaluator.get("[0].id");
			response = httpRequest.request(Method.DELETE, "/delete/" + empID);
			
			Thread.sleep(3);
			}
		
		@Test
		void checkResponseBody() {
			logger.info("******Response Body******");
			String responseBody = response.getBody().asString();
			logger.info(" Response Body : " + responseBody);
			Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
		} 
		
		@Test
		void checkStatusCode() {
			logger.info("******Status Code******");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 204);
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
			Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
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
			Assert.assertEquals(serverType, "cloudflare");
		}
		
		@Test
		void checkContentLength() {
			logger.info("******Check Content Length******");
			String contentLength = response.header("Content-Length");
			Assert.assertTrue(Integer.parseInt(contentLength)<1500);
		}

		@AfterClass
		void tearDown() {
			logger.info("******Finished TC005_Delete_Employee_Record******");
		}

}
