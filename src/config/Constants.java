package config;

public class Constants {

	public static final String loginURL = "http://www.sc-ksc.pupsqc01.local/";
	public static final String Path_TestData="src\\dataEngine\\TestSuite.xlsx";
	public static final String Path_OR="src\\config\\OR.properties";
	public static final String File_testData = "TestSuite.xlsx";
	
	 //List of Data Sheet Column Numbers
	 public static final int Col_TestCaseID = 0;    
    public static final int Col_TestStepID = 0;
    public static final int Col_TestStepDescription =1 ;
    public static final int Col_ActionKeyword =2 ;
    public static final int Col_PageObject =3 ;
    public static final int Col_DataSet=5;
    
    public static final int Col_RunMode=2;
    public static final int Col_Result=3;
    public static final int Col_TestStepresult=6;
    
  //List of Data Engine Excel sheets
    public static final String Sheet_TestSuite = "Suite";
    public static final String Sheet_TestSteps = "TC01";
 
    // List of Test Data
    public static final String UserName = "9999922211@physiciansinteractive.com";
    public static final String Password = "Password1234";

    public static final String KEYWORD_FAIL="FAIL";
    public static final String KEYWORD_PASS="PASS";
    
/*//	Browser=Firefox
//			
//			screenshotPath=C:\\Users\\Udeshmukh\\Google Drive\\Testing_General\\selenium\\SampleCenterKeyDriFrm\\screenshots
//			waitTimeout=2000
*/
	}
