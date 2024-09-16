package ecommerce.Testclasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ecommerce.baseclass.baseclass;
import ecommerce.constantData.constantData;
import ecommerce.pages.EditAccountInfoPage;
import ecommerce.pages.LoginPage;
import ecommerce.pages.Registerpage;

public class EditAccountTest extends baseclass{
	
	EditAccountInfoPage editaccount = new EditAccountInfoPage();
	Registerpage register = new Registerpage();
	LoginPage login = new LoginPage();
	

	@BeforeClass()
	public void setup() throws Exception
	{
		initialize();	
		register.launchUrl();
	}
	
	@Test (priority = 0)
	public void navigateToEditAccountpageTest() throws InterruptedException
	{
		login.navigateToLoginpage();
		login.loginWithValidCredential(prop.getProperty("emailAddress"), prop.getProperty("password"));
		boolean accountinfoTitleCheck = editaccount.navigateToEditAccountPage();
		Assert.assertTrue(accountinfoTitleCheck);
		//register.logout();
	}
	
	@Test (priority = 1)
	public void editAccountInfoTest() throws InterruptedException
	{
		//navigateToEditAccountpageTest();
		boolean accountInfoCheck = editaccount.editAccountInfo(constantData.FNameRename, constantData.LNameRename, 
		constantData.Emailaddr, constantData.TPhone);
		Assert.assertTrue(accountInfoCheck);
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
