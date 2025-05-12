package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends basePage{

	public LoginPage(WebDriver driver){
		super(driver);
	}
		//Locators
		@FindBy(xpath = "//input[@name = 'email']")
		WebElement lnkemailid;
		
		@FindBy(xpath="//input[@name = 'password']")
		WebElement lnkPassword;
		
		@FindBy(xpath = "//input[@type = 'submit']")
		WebElement btnlogin;
		
		//Action methods
		public void enterEmail(String email) {
			lnkemailid.sendKeys(email);
		}
		public void enterPassword(String password) {
			lnkPassword.sendKeys(password);
		}
		public void clicklogin() {
			btnlogin.click();
		
	}
}
