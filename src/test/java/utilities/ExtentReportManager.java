package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentTest test;
	public ExtentReports extent;
	
	String repName;
	
	

public void onStart(ITestContext testContext) {
	
	/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Date dt = new Date();
	String currentdatetimestamp = df.format(dt);
	*/
	
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	repName = "Test-report-" + timeStamp + ".html";
	sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //Specify the location of the Report 
	
	sparkReporter.config().setDocumentTitle("openCart Automation Report");
	sparkReporter.config().setReportName("opencart Functional testing");
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application","opencart");
	extent.setSystemInfo("Module","Admin");
	extent.setSystemInfo("Sub Module","Customers");
	extent.setSystemInfo("User Name",System.getProperty("user.name"));
	extent.setSystemInfo("Enviornamet", "QA");
	
	String os = testContext.getCurrentXmlTest().getParameter("os"); //os will be captured from xml file 
	extent.setSystemInfo("Operating System ", os);
	
	String browser = testContext.getCurrentXmlTest().getParameter("browser"); //os will be captured from xml file 
	extent.setSystemInfo("Operating System ", browser);
	
	List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups",includedGroups.toString()); //this will capture the groups in the include section
	}
}

public void onTestSuccess(ITestResult result) {
	test = extent.createTest(result.getClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	
	test.log(Status.PASS,result.getName()+"got successfully executed");
}

public void onTestFaliure(ITestResult result) {
	test = extent.createTest(result.getClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	
	test.log(Status.FAIL,result.getName()+"got failed");
	test.log(Status.FAIL, result.getThrowable().getMessage());
	
	try {
	String imgPath = new BaseClass().captureScreen(result.getName());
	test.addScreenCaptureFromPath(imgPath);
	
	} catch (IOException e1 ) {
		e1.printStackTrace();
		
	}
}

public void  onTestSkipped(ITestResult result) {
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, result.getName()+"got skipped");
	test.log(Status.INFO, result.getThrowable().getMessage());
	
}

public void onFinish(ITestContext testContext) { //Open Report Automatically post execution
	extent.flush();
	
	String pathOfExtentreport = System.getProperty("user.dir")+"\\reports\\"+ repName;
	File extentReport = new File(pathOfExtentreport);
	
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	} catch (IOException e) {
		e.printStackTrace();
	}
/*  //To Send report into email 
try {
URL url = new URL("file:///"+ System.getProperty("user.dir")+"\\reports\\+repName");
//create Email email message 
ImageHtmlEmail email = new ImageHtmlEmail(); 
email.setDataSourceResolver(new DataSourceUrlResolver(url));
email.setHostName("smpt.googlemail.com");
email.setSmtpPort(465);
email.setAuthenticator(new DefaultAuthenticator("nazzzma@gmail.com","password"));
email.setSSLOnConnect(true);
email.setFrom("nazzzma@gmail.com");//Sender
email.setSubject("Test Results");
email.setMsg("Please Find Attached report...");
email.addTo("najmayesmin57@gmail.com");
email.attach(url,"extent report","please check report...");
email.send(); //send the email
}catch(Exception e) {
	e.printStackTrace();
}
*/
}

}
