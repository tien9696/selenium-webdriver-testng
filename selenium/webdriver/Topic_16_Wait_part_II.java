 package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_part_II {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	By startbutton = By.xpath("//button[text()='Start']");
	By loadbutton = By.xpath("//div[@id='loading']");
	By helloworld = By.xpath("//h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Dont_set() {
		
				driver.get("https://automationfc.github.io/dynamic-loading/");
				
                 driver.findElement(startbutton).click();
                 Assert.assertTrue(driver.findElement(helloworld).isDisplayed());
                 
	}

	@Test
	public void TC_02_Set_3s() {		
				driver.get("https://automationfc.github.io/dynamic-loading/");
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				driver.findElement(startbutton).click();
                Assert.assertTrue(driver.findElement(helloworld).isDisplayed());
                
	}
	
	
	@Test
	public void TC_03_Set_6s() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(startbutton).click();
		
        Assert.assertTrue(driver.findElement(helloworld).isDisplayed());
        
	}
	@Test
	public void TC_05_FindElement() {
	//1.co duy nhat 1 element
		//ko can cho het timeout cua imlicit
		//tuong tac len element luon
		
	
	//2.ko co element nao het
		//cho het timeout cua implicit
		//trong time ch cu oi nua giay se load lai 1 lan
		//khi nao cho het timeout cua implicit se danh fail test case va throw exception
		
	}
	@Test
	public void TC_06_FindElements() {
	//1. co duy nhat 1 element
		//ko can cho het timout cua implicit
		//tuong tac len element luon
	//2. ko co element nao het => can test 1 element ko xuat hien tren UI va ko co trong DOM
		//cho het timeout cua implicit
		//trong time ch cu oi nua giay se load lai 1 lan
		//khi nao cho het timeout cua implicit se danh fail test case va throw exception
        // tra ve list empty/ko co phan tu nao het
		//chuyen qua cac steps tieps theo
	//3.nhieu hon 1 element
		//ko can cho het timeout
		//luu all element vao trong list
	}
	
	//@AfterClass
	public void afterclass(){
		driver.quit();
	}
	
	
	
	
	}
	