package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

		@Test(groups = {"Regression","Master"})
		public void verify_account_Registration() {
			
			logger.info("*****Starting TC001_AccountRegistrationTest****");
			
			try {
				
			HomePage hp = new HomePage(driver);
			logger.info("Clicked on MyAccount Link");
			hp.clickMyAccount();
			
			logger.info("Clicked on Register Link");
			hp.clickRegister();
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("Providing Customer Details");
			regpage.setFirstName(randomString());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setemail(randomString()+"@gmail.com");
			regpage.setphone(randomNumber());
			
			String password = randomAlphanumeric(); //** confirm password then save it in a variable
			regpage.setpwd(password);
			regpage.confpwd(password);
			regpage.privacy();
			regpage.submit();
			
			logger.info("Validating expected message..");
			String confmsg = regpage.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!")){
				Assert.assertTrue(true);
			}else{
				logger.error("Test failed.. Actual message : "  + confmsg );
				Assert.fail("Confirmation message mistmatch");
			}
			}
			catch(Exception e ){
				logger.error("Exception occured : " +e.getMessage());
				Assert.fail("Test Case Fail due to exception ");
			}
			logger.info("*****Finished TC001_AccountRegistrationTest*****");
			
		}
		

}
