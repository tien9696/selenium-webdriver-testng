 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05 {
	RemoteWebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@Test
	public void TC_01_Run_on_Firefox() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("https://www.facebook.com/");
		SleepInSecond(10);
	
	
	}

	//@Test
	//public void TC_02_class() {
	//	driver.findElement(By.className(projectPath))
		
	private void SleepInSecond(int i) {
		// TODO Auto-generated method stub
		
	}

	//}
	@Test
	public void TC_02_Run_on_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com/");
		SleepInSecond(10);
	
	
	}
	
	
	@Test
	public void TC_03_Run_on_Egde_chromium() {
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		
		driver.get("https://www.facebook.com/");
		SleepInSecond(10);
	
	
	
	}
	
	

	
	}
	