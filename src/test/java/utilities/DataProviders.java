package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data Providers
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\Opencart_loginData.xlsx"; //taking the Excell file from test data
		
		ExcelUtility xlutil = new ExcelUtility(path); //creating an object for XLUtility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols]; //Create Two Dimension array which can store total rows and cols
		for(int i = 1; i<=totalrows; i++) { //1 read data from excel stored in two dimention array
			for(int j = 0; j<totalcols; j++) { //0 i is row and j is col
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata; //returning two dimention array
	}
}
