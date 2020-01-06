package com.hubspot.pages;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//Locators

	By header = By.xpath("//i18n-string[contains(text(), 'Set up')]");
	By userInfo = By.xpath("//span[contains(text(), 'Ilhan')]");
	
	//To navigate to Contacts page
	By contactsMainTab =By.id("nav-primary-contacts-branch");
	By contactsChildTab = By.id("nav-secondary-contacts");
	
	//Constructor
	
	public HomePage(WebDriver driver){
		this.driver =driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//Methods
	
	public String getHomePageTitle(){
		return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
		
		//return driver.getTitle();
	}
	
	public boolean verifyLoggedinUserInfoName(){
		return elementUtil.isElementDisplayed(userInfo);
		
		//return driver.findElement(userInfo).isDisplayed();
	}
	
	public String getHomePageHeaderValue(){
		return elementUtil.doGetText(header);
		
		//return driver.findElement(header).getText();
	}
	
	public String getLoggedinUserName(){
		return elementUtil.doGetText(userInfo);
		
		//return driver.findElement(userInfo).getText();
	}
	
	private void clickOnContacts(){
		elementUtil.doClick(contactsMainTab);
		elementUtil.doClick(contactsChildTab);
	}
	
	public ContactsPage gotoContactsPage(){
		clickOnContacts();
		
		return new ContactsPage(driver);
	}

	

}
