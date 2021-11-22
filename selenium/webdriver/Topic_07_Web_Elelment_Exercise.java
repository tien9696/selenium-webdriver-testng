 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Topic_07_Web_Elelment_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");

	}
	
	
 	public void TC_01_Displayed() {
 		driver.get("https://automationfc.github.io/basic-form/index.html");
 	
 		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
 		boolean status = emailTextbox.isDisplayed();
 		System.out.println(status);
 		if(status) {
 			System.out.println("Email textbox is displayed");
			emailTextbox.sendKeys("Automation Testing");
 		} else {
 			System.out.println("Email is not displayed");
 		}
 		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
 		if (ageUnder18Radio.isDisplayed()) {
 			System.out.println("Age Under 18 Radio button is displayed");
 			ageUnder18Radio.click();
 			
		} else {
 			System.out.println("Age Under 18 Radio button is not displayed");

		}
 		WebElement EducationTextarea = driver.findElement(By.xpath("//textarea[@name='user_edu']"));
 		if (EducationTextarea.isDisplayed()) {
			System.out.println("Education textarea is displayed ");
			EducationTextarea.sendKeys("Automation");
		} else {
			System.out.println("Education textarea is not displayed ");

		}
 		WebElement User5 = driver.findElement(By.xpath("//h5[text()= 'Name: User5']"));
 		if (User5.isDisplayed()) {
			System.out.println("User5 is displayed ");
		} else {
			System.out.println("User5 is not displayed ");

		}
 	}
	

	
	@Test
	public void TC_02_Enabled() {
 		driver.get("https://automationfc.github.io/basic-form/index.html");
 		WebElement passwordTextbox = driver.findElement(By.id("password"));
 		if (passwordTextbox.isEnabled()) {
 			System.out.println("password textbox is enable");
			
		} else {
 			System.out.println("password textbox is not enable");

		}
 		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
 		if (emailTextbox.isEnabled()) {
 			System.out.println("Email textbox is enable");
			
		} else {
 			System.out.println("Email textbox is not enable");

		}
 		WebElement education = driver.findElement(By.xpath("//label[text() = 'Education:']"));
 		if (education.isEnabled()) {
 			System.out.println("education textbox is enable");
			
		} else {
 			System.out.println("education textbox is not enable");

		}

		
	}
	
	
	
	
	@Test
	public void TC_03_Selected() {
 		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
 		ageUnder18Radio.click();
 		if (ageUnder18Radio.isSelected()) {
 			System.out.println("Age Under 18 Radio button is selected");
			
		} else {
 			System.out.println("Age Under 18 Radio button is de-selected");
        }
 		WebElement Javacheckbox = driver.findElement(By.id("java"));
 		Javacheckbox.click();
 		if (Javacheckbox.isSelected()) {
 			System.out.println("Java checkbox is selected");
 			
		} else {
 			System.out.println("Java checkbox is de-selected");
        }
 		Javacheckbox.click();
 		if (Javacheckbox.isSelected()) {
 			System.out.println("Java checkbox is selected");
 			
		} else {
 			System.out.println("Java checkbox is de-selected");
        }
 	}
	@Test
	public void TC_04_Enabled() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("cam@gmail.com");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cam");
		
		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='new_password']"));
		WebElement signUpButton = driver.findElement(By.id("create-account"));

		//lowcase
		passwordTextbox.sendKeys("abc");
		sleepInSecond(5);
		//Expected displyed (assertTrue)
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text () = 'One lowercase character']")).isDisplayed());
		//Expected signUpbutton is disable
		Assert.assertFalse(signUpButton.isEnabled());
		
		
		//2- Upper case
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text () = 'One uppercase character']")).isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());
		sleepInSecond(5);

		//3 - number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text () = 'One number']")).isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());
		sleepInSecond(5);

		//4- special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text () = 'One special character']")).isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());
		sleepInSecond(5);

		//5- 8 chac
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text () = '8 characters minimum']")).isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());
		sleepInSecond(5);


 
		// combine
		passwordTextbox.clear();
		passwordTextbox.sendKeys("abcABC123!");
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		Assert.assertTrue(signUpButton.isEnabled());
		sleepInSecond(5);

		
		


		
		

		
		
		
		
		
	}
 	
	
	private void sleepInSecond(int i) {
		// TODO Auto-generated method stub
		
	}


	@AfterClass
	public void afterclass(){
		//emailTextboxdriver.quit();
	}
	
	}
	