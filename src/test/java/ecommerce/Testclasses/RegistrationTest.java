package ecommerce.Testclasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ecommerce.baseclass.baseclass;
import ecommerce.pages.Registerpage;
import ecommerce.utility.utilities;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class RegistrationTest extends baseclass{
	
	Registerpage register = new Registerpage();
	
	@BeforeClass()
	public void setup() throws Exception
	{
		initialize();	
		register.launchUrl();
	}
	
//	@BeforeMethod()
	public void launchUrlTest()
	{
		
		
	}
	
	@Test (priority = 0)
	public void verifyHomepage()
	{
		register.verifyHomepage();
	}
	
	
	@Test (priority = 1)
	public void registerpagetest() throws InterruptedException
	{
		boolean registerPageCheck = register.navigateToWebsiteRegistrationPage();
		Assert.assertTrue(registerPageCheck);
	}
	
	@Test(priority = 2)
	public void fillupRegistrationPageTest() throws InterruptedException
	{	
		registerpagetest();
		boolean registrationsuccesscheck =  register.fillupRegistrationPage("Jenny", "Dee", "j2@mailinator.com", "985478965", "Test@123", "Test@123");
		Assert.assertTrue(registrationsuccesscheck);
	}
	
	@Test(priority = 3)
	public void logout() throws InterruptedException
	{	
		registerpagetest();
		fillupRegistrationPageTest();
		boolean logoutcheck = register.logout();
		Assert.assertTrue(logoutcheck);
	}
	
	
	
	@AfterClass()
	public void tearDown() 
	{    
	   driver.quit();	    
	}
	
	
	

}
