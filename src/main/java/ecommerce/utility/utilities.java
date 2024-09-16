package ecommerce.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecommerce.constantData.constantData;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class utilities {


	public static WebElement explicitwait(WebDriver driver, By locator) 
	{    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constantData.WAIT_TIME));
        WebElement element = null;
        try 
        {
        	
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } 
        catch (Exception e) 
        {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        return element;
    }
	
	
	public static void implicitWait(WebDriver driver) 
	{    
		try 
        {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(constantData.IMPLICIT_WAIT_TIME));          
        } 
        catch (Exception e) 
        {
           
        }
        
    }
	
	
	public static WebElement FindbyElement(WebDriver driver, By locator)
	{
		WebElement element = driver.findElement(locator);
		return element;
	}
	
	public static void threadWait(long miliSeconds) {
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			//Log.info(e);
		}
	}
	
	public static WebElement findElement(WebDriver driver, By locator) {
		WebElement element = null;
		try {
			element = utilities.explicitwait(driver, locator);
		} catch (Exception e) {
			element = driver.findElement(locator);
		}
		return element;
	}
	
	
	public static boolean isElementDisplayed(WebDriver driver, By locator) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constantData.WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		try {
			return findElement(driver, locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	public static String getTextofElement(WebDriver driver, By locator) {
		
		WebElement element = utilities.findElement(driver, locator);
		return element.getText();
	}
	
	public static Boolean explicitwaitTextToBePresent(WebDriver driver, By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constantData.WAIT_TIME));
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
	}
	
	
	public static WebElement explicitwaitclickable(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constantData.WAIT_TIME));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
	public static WebElement explicitwaitElementToBePresent(WebDriver driver, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constantData.WAIT_TIME));	    
	    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));	    
	}
	
	public static void sendkeys(WebDriver driver, By locator, String text) {
		WebElement element = explicitwait(driver, locator);
		//element.clear();
		element.sendKeys(text);
	}
	
	
	public static void clearText(WebDriver driver, By locator) {
		WebElement element = explicitwait(driver, locator);
		Actions act = new Actions(driver);
		act.moveToElement(element).doubleClick().sendKeys(Keys.BACK_SPACE).perform();
	}
	
	public static void clearTextWithControll(WebDriver driver, By locator) {
		WebElement element = explicitwait(driver, locator);
		Actions act = new Actions(driver);
		//act.moveToElement(element).doubleClick().sendKeys(Keys.BACK_SPACE).perform();
		//act.moveToElement(element).click().sendKeys(Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE).perform();
		act.moveToElement(element).click().sendKeys(Keys.CONTROL).sendKeys(Keys.valueOf("a")).sendKeys(Keys.BACK_SPACE).perform();
	}
	
	public static void click(WebDriver driver, By locator) {
		WebElement element = explicitwaitclickable(driver, locator);
		try {
			element.click();
		} catch (Exception e) {
			
		}
	}

	public static void clickElementwithjs(WebDriver driver, By locator) {
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		WebElement element = explicitwaitclickable(driver, locator);
		try {
			jsexecutor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			//Log.error("Getting exception in click element with JS --> " + e.toString());
			jsexecutor.executeScript("arguments[0].click();", element);
		}
	}
	
	public static String switchToChildWindow(WebDriver driver, By Locator, String mainWindow) {
		utilities.explicitwait(driver, Locator);
		utilities.clickElementwithjs(driver, Locator);
		Set<String> windowsIDs = driver.getWindowHandles();
		for (String Whandle : windowsIDs) {
			if (!Whandle.equalsIgnoreCase(mainWindow)) {
				driver.switchTo().window(Whandle);
			}
		}
		return mainWindow;
	}
	
	public static ChromeOptions allowBrowserNotification(WebDriver driver, ChromeOptions options)
	{
		options = new ChromeOptions();	
		options.addArguments("--disable-notifications");
		options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("disable-geolocation");
        options.addArguments("--disable-gpu");
		return options;
	}
	
	
	public static List<WebElement> findElements(WebDriver driver, By locator)
	{	
		List<WebElement> element;
		try {
			element = driver.findElements(locator);
		} catch (StaleElementReferenceException e) {
			element = driver.findElements(locator);
		}
		return element;
		}
	
	
		public static void selectValueFromDropdown(WebDriver driver, By locator, String value) {
			List<WebElement> dropdownOptions = driver.findElements(locator);
			for (WebElement element : dropdownOptions) {
				if (element.getText().equalsIgnoreCase(value)) 
				{
					element.click();
				}
			}
		}
	
	
	//Upload file
	public static void uploadFile(String filePath) throws AWTException
	{
		Robot rb = new Robot();
		rb.delay(1000);	
		StringSelection Sselection = new StringSelection(filePath);		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Sselection, null);		
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.delay(1000);	
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.delay(1000);	
		rb.keyRelease(KeyEvent.VK_V);
		rb.delay(1000);	
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	public static void downloadImage(WebDriver driver, WebElement element, By locator, String fileFormat, String downloadedFilePath) throws IOException
	{
		//element = utilities.findElement(driver, locator);	
		element = driver.findElement(locator);
		Screenshot imageScreenshot = new AShot().takeScreenshot(driver, element);
		ImageIO.write(imageScreenshot.getImage(),fileFormat,new File(downloadedFilePath));			
	}
	
	
	public static void compareImages(WebDriver driver, WebElement element, By locator, String expectedFilePath) throws IOException {
		
	BufferedImage expectedImage = ImageIO.read(new File(expectedFilePath));
	element = utilities.findElement(driver, locator);		
	Screenshot imageScreenshot = new AShot().takeScreenshot(driver, element);
	BufferedImage actualImage = imageScreenshot.getImage();
	
	ImageDiffer imgdif = new ImageDiffer();
	ImageDiff imageDifferent =  imgdif.makeDiff(expectedImage, actualImage);
	
	if(imageDifferent.hasDiff()==true)
	{
		boolean image = driver.findElement(locator).isDisplayed();
		Assert.assertTrue(image);
	}
		
	}
	
	
	private ImageOutputStream File(String string) 
	{		
		return null;
	}

	//JavaScriptExecutor Click 
	public static WebElement javascriptexecutorClick(WebDriver driver, By locator)
	{
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		WebElement element = utilities.findElement(driver, locator);
		jsexecutor.executeScript("arguments[0].click();", element);
		return element;
	}
	
	//JavaScriptExecutor Scroll 
	public static By javascriptExecutorScroll(WebDriver driver, By locator)
	{
		WebElement element = driver.findElement(locator);
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("arguments[0].scrollIntoView();", element);
		return locator;		
	}
	
	//JavaScriptExecutor DrawLine
	public static WebElement drawBorderJS(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("arguments[0].style.border='3px solid red'", element);
        return element;		
	}
	
	//Action Class Hover	
	public static void hoverOverElement(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	
	

	
	//Action Class Click 
	public static void actionClassClick (WebDriver driver,By locator)
	{	
		WebElement element = utilities.findElement(driver, locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	
	public static String captureScreenshot(WebDriver driver, String testName) 
	{		
		File srsScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath =  testName;
		System.out.println(destinationScreenshotPath);
		try 
		{
			FileHandler.copy(srsScreenshot, new File(destinationScreenshotPath));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
	
	//System.getProperty("user.dir") + "\\Screenshots\\" +
	
	//Screenshot of Webelement
	public static void screenshotOfElement(By locator, String srt, WebDriver driver) throws IOException
	{
		WebElement element = driver.findElement(locator);
		File src = element.getScreenshotAs(OutputType.FILE);
		File trg = new File((System.getProperty("user.dir") + "/Screenshots/" + srt));
		FileUtils.copyFile(src, trg);
	}

	
	
	

}

