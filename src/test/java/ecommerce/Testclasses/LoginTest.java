package ecommerce.Testclasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ecommerce.baseclass.baseclass;
import ecommerce.pages.LoginPage;
import ecommerce.pages.Registerpage;

	public class LoginTest extends baseclass{
		
		Registerpage register = new Registerpage();
		LoginPage login = new LoginPage();
		
	
		@BeforeClass()
		public void setup() throws Exception
		{
			initialize();	
			register.launchUrl();
		}
		
		@Test(priority = 0)
		public void navigatetoLoginpageTest()
		{
			boolean loginpagecheck = login.navigateToLoginpage();
			Assert.assertTrue(loginpagecheck, "Fail to navigate loginpage");
		}
			
		@Test(priority = 1)
		public void loginWithValidCredentialsTest() throws InterruptedException
		{
			navigatetoLoginpageTest();
			boolean logincheck = login.loginWithValidCredential(prop.getProperty("emailAddress"), 
			prop.getProperty("password"));
			Assert.assertTrue(logincheck);	
		}
		
		@Test (priority = 2)
		public void logout()
		{
			register.logout();
		}
		
		
		@AfterClass()
		public void tearDown() 
		{    
		   driver.quit();	    
		}
	
	
	
	
	

}
