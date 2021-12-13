package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By productResults = By.cssSelector("div.caption a");
	
	public int getProductResultsCount() {
		int resultsCount = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000).size();
		System.out.println("The number of results are : "+resultsCount);
		return resultsCount;
	}

	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("Main Product Name is : "+mainProductName);
		List<WebElement> searchResult = eleUtil.waitForElementsToBeVisible(productResults, 10);
		for (WebElement e: searchResult) {
			String text = e.getText();
			if (text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
