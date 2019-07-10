package com.demo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.demo.utilities.TestBaseClass;

public class HomePage extends TestBaseClass{
	
	private static class PageMembers{
		static By fName = By.cssSelector("input[ng-model='FirstName']");
		static By sName = By.xpath("//input[@ng-model='LastName']");
		static By address = By.xpath("//textarea[@ng-model='Adress']");
		static By emailID = By.cssSelector("input[type='email']");
		static By phone = By.xpath("//*[@class='form-control ng-pristine ng-untouched ng-invalid ng-invalid-required ng-valid-pattern']");
		static By genderRadioOptions = By.name("radiooptions");
		static By hobbies = By.cssSelector("input[type='checkbox']");
		static By country = By.cssSelector("select#countries");
		static By switchTO = By.className("dropdown-toggle");
		static By windowsOption = By.xpath("//ul/li/a[text()='Windows11']");
		
	}
	
	public void launchHomePage(){
		driver.get("http://demo.automationtesting.in/Register.html");
	}
	
	public void enterFirstName(String name){
		getWebElement(PageMembers.fName).sendKeys(name);
	}
	
	public void enterSecondName(String name){
		getWebElement(PageMembers.sName).sendKeys(name);
	}
	
	public void enterAddress(String address){
		getWebElement(PageMembers.address).sendKeys(address);
	}
	
	public void enterEmailID(String address){
		getWebElement(PageMembers.emailID).sendKeys(address);
	}
	
	public void enterPhone(String phone){
		getWebElement(PageMembers.phone).sendKeys(phone);
	}
	
	public void selectGender(String gender){
		List<WebElement> lst = getWebElements(PageMembers.genderRadioOptions);
		
		for(byte b=0; b<lst.size(); b++){
			System.out.println(lst.get(b).getAttribute("value"));
		}
		
		for(byte b=0; b<lst.size(); b++){
			if(lst.get(b).getAttribute("value").equals(gender)){
				lst.get(b).click();
			}
		}
	}
	
	public void selectHobbies(String[] hobbies){
		List<WebElement> lst = getWebElements(PageMembers.hobbies);
		
		for(WebElement hobby:lst){
			System.out.println(hobby.getAttribute("value"));
		}
		
		for(byte b=0; b<hobbies.length; b++){
			for(WebElement hobby:lst){
				System.out.println(hobby.getAttribute("value"));
				if(hobby.getAttribute("value").equalsIgnoreCase(hobbies[b])){
					hobby.click();
				}
			}
		}
	}
	
	public void selectCountry(String country){
		mouseHover(PageMembers.country);
		getWebElement(PageMembers.country).click();
		Select select = new Select(getWebElement(PageMembers.country));
		select.selectByValue(country);
		getWebElement(PageMembers.country).click();
	}
	
	public void switchToWindows(){
		mouseHover(PageMembers.switchTO);
		isElementVisibleByLocator(PageMembers.windowsOption, 1);
		getWebElement(PageMembers.windowsOption).click();
	}
	
	

}
