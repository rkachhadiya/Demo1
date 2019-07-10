package com.demo.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.demo.utilities.TestBaseClass;

public class SignUpTest extends TestBaseClass{
	
	@BeforeTest
	public void beforeMethod(){
		System.out.println("working before test");
	}
	
	@Test
	public void testMethod() throws Exception{
		System.out.println("testing working");
		//readExcelData();
		//writeExcelData();
		writeExcelDataExample();
		/*homePage().launchHomePage();
		homePage().enterFirstName("R");
		homePage().enterSecondName("K");
		homePage().enterAddress("A-202 Random society");
		homePage().enterEmailID("rk@yahoo.com");
		homePage().enterPhone("1234567890");
		scroller(0, 250);
		homePage().selectGender("Male");
		homePage().selectHobbies(new String[]{"Movies", "Hockey"});
		homePage().selectCountry("India");
		homePage().switchToWindows();*/
		
	}

}
