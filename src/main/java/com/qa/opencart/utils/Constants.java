package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE= "Account Login11";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	
	
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MACBOOKPRO_IMAGE_COUNT = 4;
	public static final int MACBOOKAIR_IMAGE_COUNT = 4;
	public static final String LOGIN_ERROR_MESG = "No match for E-Mail Address and/or Password";
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "registration";
	public static final String PRODUCT_SHEET_NAME = "product";
	public static final String PRODUCTLIST_SHEET_NAME="productlist";
	
	public static List<String> expAccSecList() {
	List<String> expAccPageSecList = new ArrayList<String> (Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter"));
		return expAccPageSecList;
	}
}
