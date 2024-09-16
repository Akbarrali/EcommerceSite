package ecommerce.constantData;

import java.io.File;

import com.itextpdf.text.log.SysoCounter;

import ecommerce.baseclass.baseclass;

public class constantData extends baseclass{
	
	public static final int PAGE_LOAD_TIMEOUT = 20;
	public static final int IMPLICIT_WAIT_TIME = 30;
	public static final int WAIT_TIME = 30;
	
	public static final String PROJECTPATH = System.getProperty("user.dir");
	
	public static final String PROPERTY_FILEPATH = File.separator + "src" + File.separator + "main"  
	+ File.separator + "java" + File.separator + "config" + File.separator + "properties" 
	+ File.separator + "data.properties" ;
	
	
	public static final String FNameRename = "jammes";
	public static final String LNameRename = "Deo";
	public static final String Emailaddr = "jammes@mailinator.com";
	public static final String TPhone = "98745321";
	
	//ecommerce.constantData
	
	

}
