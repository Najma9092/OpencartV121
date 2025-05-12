package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends basePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
@FindBy(xpath="//input[@name='firstname']")
WebElement txtFirstname;

@FindBy(xpath="//input[@name='lastname']")
WebElement txtLastname;

@FindBy(xpath="//input[@name='email']")
WebElement txtemail;

@FindBy(xpath = "//input[@name = 'telephone']")
WebElement txtphone;

@FindBy(xpath="//input[@name='password']")
WebElement txtpassword;

@FindBy(xpath="//input[@name = 'confirm']")
WebElement txtconfpassword;

@FindBy(xpath = "//input[@name='agree']")
WebElement confirmPrivacyPol;

@FindBy(xpath="//input[@class='btn btn-primary']")
WebElement btnSubmit;

@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement confirmationMessage;


//action methods
public void setFirstName(String fname) {
	txtFirstname.sendKeys(fname);
}
public void setLastName(String lname) {
	txtLastname.sendKeys(lname);
}
public void setemail(String email) {
	txtemail.sendKeys(email);
}
public void setphone(String phone) {
	txtphone.sendKeys(phone);
}
public void setpwd(String pwd) {
	txtpassword.sendKeys(pwd);
}
public void confpwd(String confpwd) {
	txtconfpassword.sendKeys(confpwd);
}

public void privacy() {
	confirmPrivacyPol.click();
}

public void submit() {
	btnSubmit.click();
}
public String getConfirmationMsg() {
	
	try{
		return confirmationMessage.getText();
		}catch(Exception e) {
		return (e.getMessage());
	}
} 

}
