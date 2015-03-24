package executionEngine;

import config.ActionKeywords;
import config.Constants; 
import utility.ExcelUtils;

import java.util.Properties;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import utility.Log;

import org.apache.log4j.xml.DOMConfigurator;

public class DriverScript {

	
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Properties OR;
	public static Method method[];
	public static String sTestCaseID;
	public static String sRunMode;
	public static boolean bResult;
	public static int iTestStep;
//    public static int iTestLastStep;
	public static String sData;
	
	public DriverScript() {
		actionKeywords = new ActionKeywords();
		
		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception {
		
		ExcelUtils.setExcelFile(Constants.Path_TestData);
		
		 //This is to start the Log4j logging in the test case
        DOMConfigurator.configure("log4j.xml");
        
		String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR = new Properties();
		OR.load(fs);
		
		DriverScript startEngine = new DriverScript();
		
		startEngine.execute_TestCases();
		
		
		
	}

	private void execute_TestCases() throws Exception{
		//This will return the total number of test cases mentioned in the Test cases sheet
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestSuite);
        bResult=true;
        //This loop will execute number of times equal to Total number of test cases
        for (int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
        	iTestStep=1;
        	//this is to get test case name from the suite sheet
        	sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestSuite);
        	
        	sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestSuite);
        	
        	if (sRunMode.equals("Y")){
        		Log.startTestCase(sTestCaseID);
        		
        		bResult=true;
        		int iTotalTeststep = ExcelUtils.getRowCount(sTestCaseID)-1; //skipping 1st heading row
        		
        		for (;iTestStep<=iTotalTeststep;iTestStep++){
        			
        			 sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,sTestCaseID);
        			 sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject,sTestCaseID);
        			 sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, sTestCaseID);
        			execute_Actions();
        			if(bResult==false){
        				//set result fail in Excel
        				 ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestSuite);
                         //End the test case in the logs
                         Log.endTestCase(sTestCaseID);
        				break;
        			}
        		}
        		if(bResult==true){
        			//set result in test suite worksheet
        			ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestSuite);
        			Log.endTestCase(sTestCaseID);
        		}
        		
        	}
        	
        }
	}
	private static void execute_Actions() throws Exception{
		for (int i=0;i<method.length;i++){
			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords,sPageObject,sData);
				if(bResult==true){
                    //If the executed test step value is true, Pass the test step in Excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepresult, sTestCaseID);
                    break;
                }else{
                    //If the executed test step value is false, Fail the test step in Excel sheet
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepresult, sTestCaseID);
                    //In case of false, the test execution will not reach to last step of closing browser
                    //So it make sense to close the browser before moving on to next test case
                    ActionKeywords.closeBrowser("","");
                    break;
                    }
			}
		}
	}

}
/*	navigate
input
input
clickImageButton
wait
verifyText
*/
