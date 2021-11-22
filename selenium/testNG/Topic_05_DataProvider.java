package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_05_DataProvider {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	
	@Test(dataProvider = "user_pass")
	public void TC_01_Login_to_System(String username, String Password) {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(Password);
		driver.findElement(loginButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']")).getText(). contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		 driver.findElement(By.xpath("//a[  text()='Log Out']")).click();
		 
			driver.get("http://live.techpanda.org/index.php/customer/account/login/");
}
	@DataProvider(name = "user_pass")
	public Object [][] UserAndPasswordData(){
		return new Object[][] {
			{"selenium_11_01@gmail.com","111111"},
			{"selenium_11_02@gmail.com","111111"},
			{"selenium_11_03@gmail.com","111111"}};
	}
	
 



@Test
  public void TC_01() {
	  System.out.println("run tc 01");
  }
  public void TC_02() {
	  System.out.println("run tc 02");
  }
  public void TC_03() {
	  System.out.println("run tc 03");
  }
  public void TC_04() {
	  System.out.println("run tc 04");
  }
  
  @AfterClass
	public void afterclass(){
		driver.quit();
	}
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }


  
  

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
