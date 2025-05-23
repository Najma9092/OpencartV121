package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass (groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws InterruptedException, IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		prop= new Properties();
		prop.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		logger.info("====== Starting test execution on browser: " + br + " ======");
		
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//OS
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);	
			}else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching os");
				return;
			}
			//BROWSER
			switch(br.toLowerCase()) {
			
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("No matching browser"); return;
			}
		
			driver= new RemoteWebDriver(new URL("http://192.168.0.109:4444/wd/hub"),capabilities); 	
			
		}else if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
				
				switch(br.toLowerCase()) {
				case "chrome" : driver = new ChromeDriver(); break;
				case "edge" : driver = new EdgeDriver(); break; 
				case "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("Invalid browser name..."); return;
				}	
			}
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	//	driver.get("https://demo.opencart.com/");
		logger.info("Application launched: " + prop.getProperty("appURL2"));
		
		driver.get(prop.getProperty("appURL2"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	
	@AfterClass(groups= {"Sanity","Regression", "Master"})
	public void teardown() {
		logger.info("====== Test execution completed, closing browser ======");
		driver.quit();
		
	}

	public String randomString() { // user-defined method
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() { // phoneNumber
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomAlphanumeric() { // alphanumeric Password
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatednumber);

	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath; 
		
	}
}
