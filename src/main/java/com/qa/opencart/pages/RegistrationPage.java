package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	//1. declare private driver
	private ElementUtil eleUtil;
	
	//2. page constructor
	public RegistrationPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}
	
	//By locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailid = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By passConfirm = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline']) [position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline']) [position()=2]/input[@type='radio']");
	
	private By policyCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By successMsg = By.cssSelector("div#content h1");
	
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.linkText("Logout");
	
	//4.Page Actions
	
	public boolean doRegister(String firstName, String lastName, String emailid, String telephone, String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.emailid, emailid);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.passConfirm, password);
		if (subscribe.equals("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(policyCheckBox);
		eleUtil.doClick(continueBtn);
		String msg = eleUtil.waitForElementToBeVisible(successMsg, 5, 1000).getText();
		if (msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
