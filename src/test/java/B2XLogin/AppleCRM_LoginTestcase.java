package B2XLogin;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class AppleCRM_LoginTestcase {

		public static WebDriver driver;
				
		@BeforeMethod
		public void beforeLoginSteps()
		{
			System.setProperty("webdriver.chrome.driver","D:\\chrome238\\chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("http://10.0.2.49/AppleRussiaUI/#/login");
			
		}
		
	
		@Test(dataProvider = "Auth")
		public void loginTest(String uname, String pwd)
		{
			driver.findElement(By.name("userId")).sendKeys(uname);
			driver.findElement(By.name("password")).sendKeys(pwd);
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			
			if(driver.findElements(By.xpath("//a/i[@name='sign-out']")).size() == 0)
			{
				System.out.println("User fail to login ....!!");
			}
			else
			{
				System.out.println("Login sucessfully");
				driver.findElement(By.xpath("//a/i[@name='sign-out']")).click();
			}
				
		}

 
	 @Test
	 public static void captureScreenMethod() throws Exception{
	                
	         try{
	        	 driver.get("http://10.0.2.49/AppleRussiaUI/#/login");
	     driver.navigate().refresh();
	     //driver.findElement(By.xpath("//*[@id='cse-search-box']/div/input[4]")).sendKeys("agile"); //Statement with correct Xpath
	    // driver.findElement(By.xpath("//*[@id='cse']")).sendKeys("agile"); //Statement with incorrect Xpath	
	     
	     driver.findElement(By.xpath("//button[@type='submit']")).click();
	                }catch(Exception e){
	             File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	     FileUtils.copyFile(screenshotFile, new File("D:\\Images_Automation.png"));
	 }
	 }

@AfterMethod
public void aftertest()
{
	driver.quit();	
}

@DataProvider(name = "Auth")
public static Object[][] credentials() 
{
	Object[][] data = new Object[3][2];
	
	data[0][0] = "tssandheri";
	data[0][1] = "tTF97SA3";
	
	data[1][0] = "Test";
	data[1][1] = "tTF97SA3";
	
	data[2][0]="wasi";
	data[2][1]="tTF97SA3";
	
	return data;
}
}
	