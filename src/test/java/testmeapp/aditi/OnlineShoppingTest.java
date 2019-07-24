package testmeapp.aditi;

import java.io.File;
import java.io.IOException;
//import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testmeappUtilities.package_info;


public class OnlineShoppingTest extends package_info {
	
	 WebDriver driver=package_info.getDriver("chrome");
	 ExtentHtmlReporter htmlReporter;
	 ExtentReports extent;
	 ExtentTest test;
	 
	 @BeforeClass
	 public void open()
	 { 
		 driver.get("http://10.232.237.143:443/TestMeApp");
		 driver.manage().window().maximize();
	 }
	 	 
  @AfterTest
  public void endReportAfterTest()
  {
	  extent.flush();
  }
  
  @AfterMethod
  public void getResultAfterMethod(ITestResult result) throws IOException, InterruptedException
  {		
	  Thread.sleep(5000);
	  if(result.getStatus() == ITestResult.FAILURE) {
		  test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"FAILED", ExtentColor.RED));
		  TakesScreenshot snapshot = (TakesScreenshot)driver;
		  File src = snapshot.getScreenshotAs(OutputType.FILE);
		  String Path = System.getProperty("user.dir")+"/test-output/screens/result.getName()"+".png";
		  FileUtils.copyFile(src,new File(Path));
		  test.addScreenCaptureFromPath(Path, result.getName());
		  test.fail(result.getThrowable());

		  }
		  else if(result.getStatus()==ITestResult.SUCCESS)
		  {
		  test.log(Status.PASS,  MarkupHelper.createLabel(result.getName()+"PASSED", ExtentColor.GREEN));
		  }
		  else
		  {
		  test.log(Status.SKIP,  MarkupHelper.createLabel(result.getName()+"SKIPPED", ExtentColor.ORANGE));
		  }
		  Thread.sleep(5000);
  }
  
  @BeforeTest
  public void startReportBeforeTest()
  {
	  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/testReport.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  htmlReporter.config().setDocumentTitle("Extent Report Demo");
	  htmlReporter.config().setReportName("Test Report");
	  htmlReporter.config().setTheme(Theme.STANDARD);
	  htmlReporter.config().setTimeStampFormat("EEEE,MMMM dd,yyyy, hh:mm a '('zzz')' ");
 }
  
  @Test (priority=3)
  public void testCart() throws InterruptedException {
	  test = extent.createTest("Test Case 3","Cart");
	  driver.findElement(By.xpath("//input[@name='products']")).click();
	  WebElement search = driver.findElement(By.xpath("//*[@id='myInput']"));
	  search.sendKeys("Headphone");
	  driver.findElement(By.xpath("//input[@value='FIND DETAILS']")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
	  driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
	  String E_Title="Checkout";
	  String A_Title=driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).getText();
	  Assert.assertEquals(A_Title, E_Title); 	  	  
  }
  
  @Test (priority=2)
  public void testLogin() throws InterruptedException {
	  test = extent.createTest("Test Case 2","Login");
	  WebElement user = driver.findElement(By.xpath("//input[@id='userName']"));
	  user.sendKeys("aditikijaiho");
	  WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
	  pass.sendKeys("aditikijaiho");
	  driver.findElement(By.xpath("//input[@value='Login']")).click();
	  Thread.sleep(5000);
	  String E_Title="Home";
	  String A_Title=driver.getTitle();
	  Assert.assertEquals(A_Title, E_Title); 
}
  
  @Test (priority=4)
  public void testPayment() throws InterruptedException {
	  test = extent.createTest("Test Case 4","Payment");
	  driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
	  driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	  Thread.sleep(10000);
	  driver.findElement(By.xpath("//*[@id='swit']/div[1]/div/label/i")).click();
	  driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
	  driver.findElement(By.xpath("//input[@name='username']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Pass@456");
	  driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	  driver.findElement(By.xpath("//input[@value='PASSWORD']")).sendKeys("Trans@456");
	  driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	  String E_Title="Order Details";
	  String A_Title=driver.getTitle();
	  Assert.assertEquals(A_Title, E_Title);
  }
  
  @Test (priority=1)
  public void testRegistration() throws InterruptedException {
	   test = extent.createTest("Test Case 1","Registration");
	   driver.findElement(By.xpath("//a[contains(text(), 'SignUp')]")).click();
	   driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("aditikijaiho");
	   driver.findElement(By.xpath("//input[@name='firstName']")).click();
	   Thread.sleep(10000);
	   String A_value= driver.findElement(By.xpath("//span[@id='err']")).getText();
	   System.out.println(A_value);
	   String E_value= "Available";
	   Assert.assertEquals(E_value, A_value);
	   driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("aditiHuMain");
	   driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("ghjh");
	   driver.findElement(By.xpath("//input[@name='password']")).sendKeys("aditikijaiho");
	   driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("aditikijaiho");
	   driver.findElement(By.xpath("//span[text()='Female']")).click();
	   driver.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("abc123@ajkj.com");
	   driver.findElement(By.xpath("//input[@name='mobileNumber']")).sendKeys("6756886998");
	   driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("01/05/1997");
	   driver.findElement(By.xpath("//textarea[@name='address']")).sendKeys("afsbusdbfudysbgf123");
	   driver.findElement(By.xpath("//select[@name='securityQuestion']")).sendKeys("What is your favourite color?");
	   driver.findElement(By.xpath("//input[@name='answer']")).sendKeys("afsbusdbfudysbgf");
	   driver.findElement(By.xpath("//input[@name='Submit']")).click();
  }
}
