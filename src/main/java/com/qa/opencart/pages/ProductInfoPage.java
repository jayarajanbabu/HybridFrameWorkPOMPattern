package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {
	
	ElementUtil eleUtil;
	
	public ProductInfoPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}
	
	private By productHeader = By.cssSelector("div#content h1");
	private By images = By.cssSelector("a.thumbnail img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By prodPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addCartBtn = By.id("button-cart");
	
	private Map<String, String> prodInfoMap;
	
	@Step("getting ProductInfo page header...")
	public String getProductHeader() {
		String productHeaderText =  eleUtil.doGetText(productHeader);
		System.out.println("Product Header is : "+productHeaderText);
		return productHeaderText;
	}
	
	@Step("getting ProductInfo page imagecount...")
	public int getProductImageCount() {
		int imgCount = eleUtil.waitForElementsToBeVisible(images, 10).size();
		System.out.println("Total Images are : "+imgCount);
		return imgCount;
	}
	
	@Step("getting Product meta data...")
	public Map<String,String> getProductInfo() {
		prodInfoMap = new LinkedHashMap<String,String>();
		prodInfoMap.put("name", getProductHeader());
		getProdMetaData();
		getProdPriceData();
		return prodInfoMap;
	}
	
	private void getProdMetaData() {
		List<WebElement> metaData = eleUtil.getElements(productMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		for(WebElement e:metaData) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			prodInfoMap.put(metaKey, metaValue);
		}
	}
	private void getProdPriceData() {
		List<WebElement> priceData = eleUtil.getElements(prodPriceData);
//		$2,000.00
//		Ex Tax: $2,000.00
		String price = priceData.get(0).getText().trim();
		String exTaxPrice = priceData.get(1).getText().trim();
		prodInfoMap.put("price", price);
		prodInfoMap.put("ExTaxPrice", exTaxPrice);
	}

}
