 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Alert_Authentication_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait expliciWait ;
	String projectPath = System.getProperty("user.dir");
	By result = By.xpath("//p[@id='result']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver,30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		//vua Wait vua switch vao
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		//get text de verify
		//alert.getText()
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		sleepInsecond(4);
		Assert.assertEquals(driver.findElement(result).getText(), "You clicked an alert successfully");
		
		
		////button[text()='Click for JS Confirm']
		////button[text()='Click for JS Prompt']
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		//kt mess hien thi trong alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//cancel alert va kt result
		alert.dismiss();
		Assert.assertEquals(driver.findElement(result).getText(), "You clicked: Cancel");
		

		
	}
	
	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String alertValue = "selenium";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(alertValue);
		alert.accept();
		Assert.assertEquals(driver.findElement(result).getText(), "You entered: " + alertValue );
   }
	//@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).isDisplayed() );
}
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(passInforToUrl(basicAuthenUrl));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).isDisplayed() );

	}
	public String passInforToUrl(String url) {
		String[] urlValue = url.split("//");
		url = urlValue[0] + "//" + "admin" + ":" + "admin" + "@" + urlValue[1];
		return url;
		
		
	}
	
	
	
	
	//@AfterClass
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
	