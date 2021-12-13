package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		}
		
	private By header = By.cssSelector("div#logo a");
	private By accountsSection = By.cssSelector("div#content h2");
	private By searchBox = By.name("search");
	private By searchBtn = By.xpath("//div[@id='search']//button");
	private By logoutLink = By.linkText("Logout");
	
	@Step("getting Accounts page title...")
	public String getAccountsPageTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting Account page header...")
	public String verifyHeader() {
		return eleUtil.doGetText(header);
	}
	
	@Step("Verifying accounts sections...")
	public List<String> verifyAccountsSection() {
		List<WebElement> accSecList = eleUtil.waitForElementsToBeVisible(accountsSection, 10);
		List<String> accSecTextList = new ArrayList<String>();
		for(WebElement e:accSecList) {
			String text = e.getText();
			accSecTextList.add(text);
		}
		return accSecTextList;
	}
	
	@Step("Verifying logout link visibility...")
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	@Step("logout test...")
	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	@Step("verifying Search field...")
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(searchBox);
	}
	
	@Step("searching for product..")
	public SearchResultsPage doSearch(String prodName) {
		System.out.println("Searching for the Product : "+prodName);
		eleUtil.doSendKeys(searchBox, prodName);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}

}
