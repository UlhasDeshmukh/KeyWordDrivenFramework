package utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import config.Constants;
import executionEngine.DriverScript;
 
public class ExcelUtils {

	private static XSSFWorkbook ExcelWbook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	
	public static void setExcelFile(String Path) throws Exception{
		try {
			FileInputStream ExcelFile = new FileInputStream (Path);
			ExcelWbook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			 Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
             DriverScript.bResult = false;
		}
		
	}
	
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{
		ExcelWSheet = ExcelWbook.getSheet(SheetName);
		try{
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		if (Cell==null){
			return "";
		}
		else{
		String CellData = Cell.getStringCellValue();
		return CellData;
		}
		}catch(Exception e){
			 Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
             DriverScript.bResult = false;
			return "";
		}
	}
	
	public static int getRowCount(String SheetName){
		int number=0;	
		try {
				ExcelWSheet = ExcelWbook.getSheet(SheetName);
				number = ExcelWSheet.getLastRowNum() + 1;
				
			} catch (Exception e) {
				 Log.error("Class Utils | Method getRowCoun | Exception desc : "+e.getMessage());
	                DriverScript.bResult = false;
			}
			return number;
		} 
	
	 //This method is to get the Row number of the test case
    //This methods takes three arguments(Test Case name , Column Number & Sheet name)
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        int i=0;    
        try {
			//ExcelWSheet = ExcelWbook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for (i = 0; i < rowCount; i++) {
				if (ExcelUtils.getCellData(i, colNum, SheetName)
						.equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
		} catch (Exception e) {
			 Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
             DriverScript.bResult = false;
		}
			return i;
            }

    //This method is to get the count of the test steps of test case
    //This method takes three arguments (Sheet name, Test Case Id & Test case row number)
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        try {
			for (int i = iTestCaseStart; i <= ExcelUtils.getRowCount(SheetName); i++) {
				if (!sTestCaseID.equals(ExcelUtils.getCellData(i,
						Constants.Col_TestCaseID, SheetName))) {
					int number = i;
					return number;
				}
			}
			ExcelWSheet = ExcelWbook.getSheet(SheetName);
			int number = ExcelWSheet.getLastRowNum() + 1;
			return number;
		} catch (Exception e) {
			Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
            DriverScript.bResult = false;
            return 0;
		}
    }
    
    @SuppressWarnings("static-access")
	public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
        try{
  
            ExcelWSheet = ExcelWbook.getSheet(SheetName);
            XSSFRow Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
             } else {
                 Cell.setCellValue(Result);
             }
 // Constant variables Test Data path and Test Data file name
              FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
              ExcelWbook.write(fileOut);
              fileOut.flush();
              fileOut.close();
          }catch(Exception e){
              DriverScript.bResult = false;
          }
     }
	
}
