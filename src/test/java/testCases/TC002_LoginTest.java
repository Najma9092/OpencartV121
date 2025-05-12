package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.myAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		
		logger.info("*****Starting TC002_LoginTest*****");
		
		try {
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login Page
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(prop.getProperty("email"));
		lp.enterPassword(prop.getProperty("password"));
		lp.clicklogin();
		
		//myAccount
		myAccountPage macc = new myAccountPage(driver);
		boolean targetPage = macc.isAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"Login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e ) {
			Assert.fail();
		}
		logger.info("*****Finished TC002_LoginTest*****");
	}
}
