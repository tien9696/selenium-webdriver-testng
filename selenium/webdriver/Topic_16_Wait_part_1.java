 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_part_1 {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible() {
				driver.get("https://www.facebook.com/");
				
				//Wait cho 1 element hien thi
				expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
				
				Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}

	//@Test
	public void TC_02_Invisible() {
		//Wait cho buton tao tai khoan cos the duoc click
		driver.get("https://www.facebook.com/");

		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		
		//Action
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		
		//ko co tren UI nhung co trong DOM
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")));

		driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")).click();
		
		
		//khong co tren UI va ko co trong DOM
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));

	}
	
	//@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");
		
		//co trong DOM va UI
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();


		//ko co tren UI nhung co trong DOM
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")));
        driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")).click();
		
		
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
	    
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
        //hien thi tren UI va co trong DOM
		WebElement Registerform = driver.findElement(By.xpath("//form[@id='reg']"));
		//ko hien thi tren UI va co trong DOM
		WebElement confirmemail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")));
        driver.findElement(By.xpath("//div[text()='Sign Up']//parent::div//preceding-sibling::img")).click();
		//tai thoi diem nay ko muon cos trong DOM
        //wait confirmemail Staleness(ko hien thi tren UI va ko co trong DOM)
        expliciWait.until(ExpectedConditions.stalenessOf(confirmemail));
        //wait registerform Staleness
        expliciWait.until(ExpectedConditions.stalenessOf(Registerform));
		
	}
	

	//@AfterClass
	public void afterclass(){
		driver.quit();
	}
	
	
	

	}
	