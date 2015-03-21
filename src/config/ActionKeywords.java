package config;

//import org.openqa.selenium.firefox.FirefoxDriver;
import static executionEngine.DriverScript.OR;

import java.util.concurrent.TimeUnit;

import utility.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import executionEngine.DriverScript;


public class ActionKeywords {
	public static WebDriver driver;
	
	 public static void openBrowser(String object,String data){    
		 Log.info("Opening Browser");
		 try {
			if(data.equals("Firefox")){
				driver=new FirefoxDriver();
				Log.info("Mozilla Browser started");
			}
			else if(data.equals("IE")){
				driver=new InternetExplorerDriver();
				Log.info("IE Browser started");
			}
			else if (data.equals("Chrome")){
				driver = new ChromeDriver();
				Log.info("Chrome Browser started");
			}
		int implicitWaitTime=(10);
		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			
			Log.info("Not able to open Browser --- "+ e.getMessage());
			
			DriverScript.bResult=false;
		}
	  }
	 
	 public static void closeBrowser(String object,String data){
		 try {
			Log.info("Closing Browser");
			driver.quit();
		} catch (Exception e) {
			Log.info("Not able to close the browser --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
	        }
	 
	public static void navigate(String object, String data){
		try {
			Log.info("Navigating to URL: "+ Constants.loginURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.loginURL);
		} catch (Exception e) {
		Log.info("Not able to navigate --- "+ e.getMessage());
		DriverScript.bResult=false;
		}
		}
	
	public static void input(String object,String data){
		 try {
			Log.info("Entering the text in "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			//System.out.println("Inside Keyword: input "+id + " "+ sTestData);
		} catch (Exception e) {
			Log.info("Not able to enter --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void clickImageButton(String object,String data){
		try {
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			Log.info("Not able to click --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
			}
	public static void waitFor(String object,String data) throws Exception{
		try {
			Log.info("Waiting for 5 seconds");
			Thread.sleep(5000);
			//WebDriverWait wait = new WebDriverWait(driver, 2000);
			//WebElement element = wait.until(presenceOfElementLocated(org.openqa.selenium.By locator), timeout);
			//driver.wait();//waitTimeout
		} catch (Exception e) {
			Log.info("Not able to wait --- "+ e.getMessage());
			DriverScript.bResult=false;
		}
	}
	
	public static void verifyText(String object,String data){
		try {
			Log.info("Verifying text " + data +" in "+object);
			driver.findElement(By.xpath(OR.getProperty(object))).getText().contains(data);
			//driver.getPageSource().contains(data);
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
