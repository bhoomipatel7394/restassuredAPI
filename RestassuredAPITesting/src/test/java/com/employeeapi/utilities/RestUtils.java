package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
		public static String empName() {
			String generatedString = RandomStringUtils.randomAlphabetic(1);
			return ("Jonny" + generatedString);
		}
		
		public static String empSal() {
			String generatredString = RandomStringUtils.randomNumeric(7);
			return (generatredString);		
		}
		
		public static String empAge() {
			String generatredString = RandomStringUtils.randomNumeric(3);
			return (generatredString);		
		}
	
}
