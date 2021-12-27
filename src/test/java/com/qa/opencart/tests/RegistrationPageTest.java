package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;


public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registrationPageSetup() {
		regPage = loginPage.doClickRegisterLink();
	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "seleautomation"+randomGenerator.nextInt(10000)+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.doRegister(firstName, lastName, getRandomEmail(), telephone, password, subscribe));;
	}
}
