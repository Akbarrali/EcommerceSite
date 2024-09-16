package ecommerce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ecommerce.baseclass.baseclass;
import ecommerce.constantData.constantData;
import ecommerce.utility.utilities;

public class Registerpage extends baseclass{
	
	By home = By.xpath("//span[normalize-space()='Home']");
	By myAccount = By.xpath("//a[@data-toggle='dropdown']//child::i");
	By registerLink = By.linkText("Register");
	By RegistrationTitle = By.xpath("//h1[contains(text(),'Register Account')]");
	By firstname = By.xpath("//input[@id='input-firstname']");
	By lastname = By.xpath("//input[@id='input-lastname']");
	By email = By.xpath("//input[@id='input-email']");	
	By telephone = By.xpath("//input[@id='input-telephone']");	
	By password = By.xpath("//input[@id='input-password']");
	By confirmpassword = By.xpath("//input[@id='input-confirm']");
	By subscribeYES = By.xpath("//input[@type='radio' and @id='input-newsletter-yes']");
	By subscribeNO = By.xpath("//input[@type='radio' and @id='input-newsletter-no']");
	By checkbox = By.xpath("//label[@for='input-agree']");
	By continuee = By.xpath("//input[@value='Continue']");
	By accountCreated = By.xpath("//h1[@class='page-title my-3']");
	By continueRegister = By.xpath("//a[@class='btn btn-primary']");
	By logout = By.linkText("Logout");
	By accountLogout = By.xpath("//h1[@class='page-title my-3']");
	
	
	
	
	public void launchUrl()
	{	
		driver.get(prop.getProperty("url"));
	}

	public void verifyHomepage()
	{	
		utilities.explicitwait(driver, home);
		utilities.isElementDisplayed(driver, home);
	}
	
	public boolean navigateToWebsiteRegistrationPage() throws InterruptedException
	{
		utilities.explicitwait(driver, myAccount);
		utilities.hoverOverElement(driver, myAccount);		
		utilities.click(driver, registerLink);
		return utilities.isElementDisplayed(driver, RegistrationTitle);
	}
	
	public boolean fillupRegistrationPage(String fname, String lname, String mail, String phone, String pass, String Cpass) throws InterruptedException
	{
		utilities.sendkeys(driver, firstname, fname);
		utilities.sendkeys(driver, lastname, lname);
		utilities.sendkeys(driver, email, mail);
		utilities.sendkeys(driver, telephone, phone);
		utilities.sendkeys(driver, password, pass);
		utilities.sendkeys(driver, confirmpassword, Cpass);	
		utilities.explicitwait(driver, checkbox);	
		utilities.javascriptexecutorClick(driver, checkbox);
		Thread.sleep(1000);
		utilities.javascriptexecutorClick(driver, continuee);
		return utilities.isElementDisplayed(driver, accountCreated);
	}
	
	public boolean logout()
	{	
		utilities.explicitwait(driver, myAccount);
		utilities.hoverOverElement(driver, myAccount);
		utilities.click(driver, logout);
		return utilities.isElementDisplayed(driver, accountLogout);
	}
		
}
