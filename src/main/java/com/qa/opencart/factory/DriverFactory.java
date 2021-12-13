package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This Method is used to initialize the WebDriver
	 * @param browserName
	 * @return this will return the driver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		System.out.println("Browser name is : "+browserName);
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} 
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOption());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
		} 
		
		else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tlDriver.set(new SafariDriver());
		}else {
			System.out.println("Pass the right Browser :"+browserName);
		}
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("loginpageurl"));
		return getDriver();
	}
	
	/**
	 * getdriver(): it will return a thread local copy of the webdriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	/**
	 * This method is used to initialize the properties
	 * @return this will return Properties prop reference
	 */
	
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env"); //qa/stage/dev/uat
		
		if (envName==null) {
			System.out.println("Running on Prod environment");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on env : "+envName);
			try {
			switch (envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			default:
				System.out.println("Please pass the correct environment");
				break;
			}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * Screenshot method for Extent reports
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}