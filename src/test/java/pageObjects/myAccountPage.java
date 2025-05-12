package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends basePage{

	public myAccountPage(WebDriver driver) {
		super(driver);
	}	
	
	//Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;

	@FindBy(xpath = "//a[text()='Logout']")
	WebElement btnlogout;
	
	//Action Method
	public boolean isAccountPageExists() {
		try {
			return(msgHeading.isDisplayed());
		}catch(Exception e ) {
			return false;
		}
	}
	
	public void clickLogout() {
		btnlogout.click();
	}

}
