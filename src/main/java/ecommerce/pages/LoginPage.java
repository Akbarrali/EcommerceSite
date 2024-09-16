package ecommerce.pages;

import org.openqa.selenium.By;

import ecommerce.baseclass.baseclass;
import ecommerce.utility.utilities;

public class LoginPage extends baseclass {

	By myAccount = By.xpath("//a[@data-toggle='dropdown']//child::i");
	By loginlink = By.xpath("//span[normalize-space()='Login']");
	By loginbutton = By.xpath("//input[@value='Login']");
	By emailaddress = By.xpath("//input[@id='input-email']");
	By password = By.xpath("//input[@id='input-password']");
	By MyAccount= By.xpath("//h2[contains(text(),'My Account')]");
	
	Registerpage register = new Registerpage();
	
	public boolean navigateToLoginpage() {
		utilities.explicitwait(driver, myAccount);
		utilities.hoverOverElement(driver, myAccount);
		utilities.click(driver, loginlink);
		return utilities.isElementDisplayed(driver, loginbutton);
	}
	
	
	public boolean loginWithValidCredential(String email, String pass )
	{
		utilities.sendkeys(driver, emailaddress, email);
		utilities.sendkeys(driver, password, pass);
		utilities.click(driver, loginbutton);
		return utilities.isElementDisplayed(driver, MyAccount);			
	}
	
}
