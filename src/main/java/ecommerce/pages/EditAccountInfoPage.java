package ecommerce.pages;

import org.openqa.selenium.By;
import ecommerce.baseclass.baseclass;
import ecommerce.utility.utilities;

public class EditAccountInfoPage extends baseclass {

    private By myAccount = By.xpath("//a[@data-toggle='dropdown']//child::i");
    private By dashboard = By.linkText("Dashboard");
    private By editAccount = By.linkText("Edit Account");
    private By accountInfoTitle = By.xpath("//h1[contains(text(),'My Account Information')]");
    private By firstName = By.xpath("//input[@id='input-firstname']");
    private By lastName = By.xpath("//input[@id='input-lastname']");
    private By email = By.xpath("//input[@id='input-email']");
    private By telephone = By.xpath("//input[@id='input-telephone']");
    private By continuebutton = By.xpath("//input[@class='btn btn-primary']");
    private By returncustomer = By.xpath("//h2[text()='Returning Customer']");
    
    
    
    public boolean navigateToEditAccountPage() {
        //utilities.hoverOverElement(driver, myAccount);
        //utilities.click(driver, dashboard);
        utilities.explicitwait(driver, editAccount);
        utilities.click(driver, editAccount);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return utilities.isElementDisplayed(driver, accountInfoTitle);
    }

    public boolean editAccountInfo(String fName, String lName, String emailAddr, String phone) {
    	
    	utilities.clearText(driver, firstName);
        utilities.sendkeys(driver, firstName, fName);
        utilities.threadWait(1000);
        
        utilities.clearText(driver, lastName);
        utilities.sendkeys(driver, lastName, lName);       
        utilities.threadWait(1000);
        
        utilities.clearTextWithControll(driver, email);
        utilities.threadWait(1000);
        utilities.sendkeys(driver, email, emailAddr);        
       
        
        utilities.clearText(driver, telephone);
        utilities.threadWait(1000);
        utilities.sendkeys(driver, telephone, phone);
        
        
        utilities.click(driver, continuebutton);
        return utilities.isElementDisplayed(driver, returncustomer);
    }
    
    	
    
    
}
