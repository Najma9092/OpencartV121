package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends basePage{
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lykRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	//action Methods
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	public void clickRegister(){
		lykRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}

}
