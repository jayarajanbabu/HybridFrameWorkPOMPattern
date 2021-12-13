package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 200: Design Open Cart Accounts Page with multiple features")
@Story("US 201: Open Cart Account page Design with multiple features")

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Account page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountsPageTitle();
		System.out.println("Account Page Title : "+actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("Account page Header Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header = accountsPage.verifyHeader();
		System.out.println("Account Page header is : "+header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Description("Logout link visibility Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void IsLOgoutExist() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());;
	}
	
	@Description("Account page Sections Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void accPageSecsTest() {
		List<String> actAccSecList = accountsPage.verifyAccountsSection();
		Assert.assertEquals(actAccSecList, Constants.expAccSecList());
	}
	
//	@DataProvider
//	public Object[][] productData() {
//		return new Object[][] {
//			{"MacBook"},
//			{"Apple"},
//			{"SamSung"}
//		};
//	}
	@DataProvider
	public Object[][] productData(){
		return ExcelUtil.getTestData(Constants.PRODUCTLIST_SHEET_NAME);
	}
	
	@Description("Account page Search Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String prodName) {
		searchResultsPage = accountsPage.doSearch(prodName);
		Assert.assertTrue(searchResultsPage.getProductResultsCount()>0);
	}
// Search Results Page Tests
	
//	@DataProvider
//	public Object[][] productSelectData() {
//		return new Object[][] {
//			{"MacBook", "MacBook Pro"},
//			{"iMac", "iMac"},
//			{"Apple", "Apple Cinema 30\""},
//			{"SamSung", "Samsung SyncMaster 941BW"}
//		};
//	}
//	
	@DataProvider
	public Object[][] getProductData(){
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
	
	@Description("Account page Select Product Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6, dataProvider = "getProductData")
	public void selectProductTest(String prodName, String mainProdName) {
		searchResultsPage = accountsPage.doSearch(prodName);
		productInfoPage = searchResultsPage.selectProduct(mainProdName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProdName);;
	}
	
	
}
