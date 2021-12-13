package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object[][] invalidLoginCredentials() {
	return new Object[][] {
		{"test11@gmail.com", "test@12345"},
		{"naveenanimation20@gmail.com", "test@12345"},
		{" ", "test@12345"},
		{"",""},
	};
	}
	@Test(dataProvider = "invalidLoginCredentials")
	public void loginPageNegativeTest(String username, String password) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));;
	}
}
