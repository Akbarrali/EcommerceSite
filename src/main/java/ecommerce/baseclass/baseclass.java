package ecommerce.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


import ecommerce.constantData.constantData;

public class baseclass {

	public static Properties prop;
	public static FileInputStream fis;
	public static WebDriver driver;
	public static ChromeOptions options;

	public static void initialize() throws IOException {
		prop = new Properties();
		try
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(constantData.PROJECTPATH + constantData.PROPERTY_FILEPATH);
			prop.load(fis);	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		String browsername = prop.getProperty("browser");
		options = new ChromeOptions();
		
		if(browsername.equals("chrome")) {
			
			options.addArguments("--disable-notifications");
			options.addArguments("--use-fake-ui-for-media-stream");
			options.addArguments("--use-fake-device-for-media-stream");
			options.addArguments("disable-geolocation");
			options.addArguments("--disable-gpu");
			driver = new ChromeDriver(options);
			
		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(constantData.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(constantData.PAGE_LOAD_TIMEOUT));  
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

	}
}
