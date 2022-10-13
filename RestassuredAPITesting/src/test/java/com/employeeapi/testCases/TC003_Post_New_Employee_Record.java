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

public class TC003_Post_New_Employee_Record extends TestBase {
	
			RequestSpecification httpRequest;
			Response response;
			
			String empName = RestUtils.empName();
			String empSalary = RestUtils.empSal();
			String empAge = RestUtils.empAge();
			
			@BeforeClass
			void getEmployeeData() throws InterruptedException {
				
				logger.info("*********TC003_Post_New_Employee_Record*********");
				RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
				httpRequest = RestAssured.given();
				
				JSONObject requestParams=new JSONObject();
				requestParams.put("name",empName);
				requestParams.put("salary",empSalary);
				requestParams.put("age",empAge);
				
				
				httpRequest.header("Content-Type", "application/json");
				
				httpRequest.body(requestParams.toJSONString());
				
				response = httpRequest.request(Method.POST, "/create");
				
				Thread.sleep(5000);
				
			}
				
		@Test
		void checkResponseBody() {
			String responsebody=response.getBody().asString();
			Assert.assertEquals(responsebody.contains(empName),true);
			Assert.assertEquals(responsebody.contains(empSalary),true);
			Assert.assertEquals(responsebody.contains(empAge),true);
		}
		@Test
		void checkStatuscode() {
			int statuscode=response.getStatusCode();
			Assert.assertEquals(statuscode,200);
			
		}
		@Test
		void chechStatusLine() {
			String StatusLine=response.getStatusLine();
			Assert.assertEquals(StatusLine,"HTTP/1.1 200 OK");
		}
		@Test
		void checkContentType() {
			String contentType=response.header("Content-Type");
			Assert.assertEquals(contentType,"application/json");
		}
		@Test
		void checkserverType() {
			String serverType=response.header("Server");
			Assert.assertEquals(serverType, "Apache");
			
		}
		@Test
		void checkcontentEncoding() {
			String contentEncoding= response.header("Content-Encoding");
			Assert.assertEquals(contentEncoding,"br");
		}
		@AfterClass
		void tearDown() {
		logger.info("****Finished TC003_Post_Employee_Record****");	
		}

}
