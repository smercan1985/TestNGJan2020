package com.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Listeners(com.hubspot.listeners.TestAllureListener.class)

public class LoginPageTest {
	
	Logger log = Logger.getLogger("LoginPageTest");
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		log.info("Browser is launching...");
		prop =basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		log.info("Set up is configured...");
		
		
	}
	
	@Test(priority=1, description="This method is getting title")
	
	public void loginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2, description="Valid username, invalid password")
	public void loginTest1() throws InterruptedException{
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(3000);
	}
	
	@Test(priority=3, description="valid username valid password")
	@Description("login test with correct user and correct pass")
	@Severity(SeverityLevel.CRITICAL)
	
	public void loginTest2() throws InterruptedException{
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		log.info("Login testlogs: correct user and correect pass");
		Thread.sleep(3000);
	}
	
	//priority- enabled=true, description--- soner Valid username

	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}

}
