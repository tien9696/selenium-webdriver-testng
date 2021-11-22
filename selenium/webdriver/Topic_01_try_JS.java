 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_try_JS {
	WebDriver driver;
	JavascripExecutor jsExecutor;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
				jsExecutor = (JavascripExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("pass")).sendKeys("123456");
		
	}

	@Test
	public void TC_02_Name() {
		driver.findElement(By.name("login")).isDisplayed();
	}
	
	
	@AfterClass
	public void afterclass(){
		driver.quit();
	}
	public void sleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
	