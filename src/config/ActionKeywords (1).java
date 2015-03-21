package config;

//import org.openqa.selenium.firefox.FirefoxDriver;
import static executionEngine.DriverScript.OR;
import utility.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import executionEngine.DriverScript;


public class ActionKeywords {
	public static WebDriver driver;
	
	 public static void openBrowser(String object){    
		 try {
			Log.info("Opening Browser");
			    driver=new FirefoxDriver();
		} catch (Exception e) {
			
			Log.info("Not able to open Browser --- "+ e.getMessage());
			
			DriverScript.bResult=false;
		}
	  }
	 
	 public static void closeBrowser(String object){
		 try {
			Log.info("Closing Browser");
			driver.quit();
		} catch (Exception e) {
			Log.info("Not able to close the browser --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
	        }
	 
	public static void navigate(String object){
		try {
			Log.info("Navigating to URL: "+ Constants.loginURL);
			driver.get(Constants.loginURL);
		} catch (Exception e) {
		Log.info("Not able to navigate --- "+ e.getMessage());
		DriverScript.bResult=false;
		}
		}
	
	public static void input(String object){
		 try {
			Log.info("Entering the text in ");
			//driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(props.getProperty(sTestData));
			//System.out.println("Inside Keyword: input "+id + " "+ sTestData);
		} catch (Exception e) {
			Log.info("Not able to enter --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void clickImageButton(String object){
		try {
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			Log.info("Not able to click --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
			}
	public static void waitFor(String object) throws Exception{
		try {
			Log.info("Waiting for");
			WebDriverWait wait = new WebDriverWait(driver, 2000);
			//WebElement element = wait.until(presenceOfElementLocated(org.openqa.selenium.By locator), timeout);
			//driver.wait();//waitTimeout
		} catch (Exception e) {
			Log.info("Not able to wait --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void verifyText(String object){
		try {
			Log.info("Verifying text " + object);
			driver.getPageSource().contains(object);
		} catch (Exception e) {
			Log.info("Not able verifyText --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
		
	}
	
/*	navigate
	input
	input
	clickImageButton
	wait
	verifyText
*/
}
