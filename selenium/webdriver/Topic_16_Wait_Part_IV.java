 package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Wait_Part_IV {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		explicitWait = new WebDriverWait(driver, 30);
//	}
	}
	//@Test
	public void TC_01_Element_found_implicit_explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
	
				driver.get("https://www.facebook.com/");
				showDateTimeNow("Start explicit : ");
				explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
				showDateTimeNow("End explicit :  ");
				
				showDateTimeNow("Start implicit : ");
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
				showDateTimeNow("End implicit : ");

	}

	@Test
	public void TC_02_Not_Found_Implicit() {
		//cho het timeout cua implicit
				//trong time ch cu oi nua giay se load lai 1 lan
				//khi nao cho het timeout cua implicit se danh fail test case va throw exception
		driver.get("https://www.facebook.com/");
		showDateTimeNow("Start Implicit :  ");
		try {
			driver.findElement(By.xpath("//input[@id='tao_ko_co']")).sendKeys("automation@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDateTimeNow("End Implicit :  ");
}
	public void showDateTimeNow(String status) {
		Date date = new Date();
		System.out.println("--------------" + status + " : " + date.toString() + "-------------");
	}

	@Test
	public void TC_03_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		showDateTimeNow("Start explicit : ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='tao_ko?_thay']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDateTimeNow("End explicit :  ");

	}
	@Test
	public void TC_04_Element_Not_Found_Explicit_param_By() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		showDateTimeNow("Start explicit (By) : ");
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='tao_ko?_thay']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        showDateTimeNow("End explicit (By) : ");

	}
	@Test
	public void TC_05_Element_Not_Found_Explicit_param_WebElement() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		showDateTimeNow("Start explicit (WebElement) : ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='tao_ko?_thay']"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        showDateTimeNow("End explicit (WebElement) : ");

	}
	
	@AfterClass
	public void afterclass(){
		driver.quit();
	}
	
	
//	public String GetDateTimeNow() {
//		Date date = new Date();
//		return date.toString();
//		
//	}
	
	
	
//	public void sleepInsecond(long time) {
//		try {
//			Thread.sleep(time*1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	}
	