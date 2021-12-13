package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 300: Design Open Cart Product Information Page with multiple features")
@Story("US 301: Open Cart Product Information page Design with multiple features")
public class ProductInfoTest extends BaseTest{

	@BeforeClass
	public void productInfoPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("ProductInfo page Header Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");;
	}
	
	@Description("ProductInfo page ImageCount Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void prodImageCountTest() {
		searchResultsPage=accountsPage.doSearch("iMac");
		productInfoPage=searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImageCount(), Constants.IMAC_IMAGE_COUNT);
	}
	
	@Description("ProductInfo page Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void prodInfoTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProdInfoMap = productInfoPage.getProductInfo();
		actProdInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		softAssert.assertEquals(actProdInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProdInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProdInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();


	}
}
