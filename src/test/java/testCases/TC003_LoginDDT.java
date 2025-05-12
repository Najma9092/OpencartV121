package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//Data is valid -- login success --test passed -- logout
//Data is valid -- login failed --test failed 

//Data is invalid -- login failed --test passed
//Data is invalid -- login success --test failed -- logout

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class,groups="Datadriven") //getting data provider from class
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		logger.info("******Starting TC003_LoginDDT******");
		
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login Page
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clicklogin();
		
		//myAccount
		myAccountPage macc = new myAccountPage(driver);
		boolean targetPage = macc.isAccountPageExists();
		
		//Data is valid -- login success --test passed -- logout
		//Data is valid -- login failed -- test failed 

		//Data is invalid --login success--test failed -- logout
		//Data is invalid --login failed --test passed 
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e ) {
			Assert.fail();
		}
		logger.info("******Finished TC003_LoginDDT******");
}

}
