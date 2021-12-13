package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	//1. Declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. By Locators
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMesg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	//4. Page Actions
	@Step("getting login page title...")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitleWithFraction(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page URL...")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("Checking Register Link visiblity...")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}

	@Step("Checking ForgotPassword Link visiblity...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("Do login with username: {0} and password {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with : "+un+" : "+pwd);
		eleUtil.doSendKeys(emailID, un);
		eleUtil.doSendKeys(password, pwd);;
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("Login with invalid username: {0} and password: {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("Try login with wrong Credentials : "+un+" : "+pwd);
		eleUtil.doSendKeys(emailID, un);
		eleUtil.doSendKeys(password, pwd);;
		eleUtil.doClick(loginBtn);
		String errorMesg = eleUtil.doGetText(loginErrorMesg);
		System.out.println("The Error Mesg is : "+errorMesg);
		if (errorMesg.contains(Constants.LOGIN_ERROR_MESG)) {
			System.out.println("Login is not successful");
			return false;
		}
		return true;
	}
	
	@Step("Navigating to Registration page...")
	public RegistrationPage doClickRegisterLink() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
