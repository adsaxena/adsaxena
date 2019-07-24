package testmeappUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Drivers {

		public static WebDriver getDriver(String browser) {
			if(browser.equals("chrome"))
			{
				  System.setProperty("webdriver.chrome.driver","C:\\NexGen Testing Stream\\DRIVERS\\chromedriver.exe");
				  return new ChromeDriver();
			}
			else if(browser.equals("ie"))
			{
				  System.setProperty("webdriver.ie.driver","C:\\NexGen Testing Stream\\DRIVERS\\IEDriverServer.exe");
				  return new ChromeDriver();
			}
			else if(browser.equals("ff"))
			{
				  System.setProperty("webdriver.gecko.driver","C:\\NexGen Testing Stream\\DRIVERS\\geckodriver.exe");
				  return new ChromeDriver();
			}
			else
				return null;		
		}
}
