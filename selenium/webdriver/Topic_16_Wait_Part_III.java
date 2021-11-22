 package webdriver;

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

public class Topic_16_Wait_Part_III {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By startbutton = By.xpath("//button[text()='Start']");
	By loadbutton = By.xpath("//div[@id='loading']");
	By helloworld = By.xpath("//h4[text()='Hello World!']");
	
	String haifilename = "2.png";
	String excelfilename = "excel.png";
	String sefilename = "se.png";

	String haifilepath = projectPath + "\\UploadFile\\" + haifilename ;
	String excelfilepath = projectPath + "\\UploadFile\\" + excelfilename ;
	String sefilepath = projectPath + "\\UploadFile\\" + sefilename ;



	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 60);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible() {
				driver.get("https://automationfc.github.io/dynamic-loading/");
				driver.findElement(startbutton).click();
				
				explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloworld));
				Assert.assertTrue(driver.findElement(helloworld).isDisplayed());

	}

	//@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startbutton).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadbutton));
		
		Assert.assertTrue(driver.findElement(helloworld).isDisplayed());

		
	}
	//@Test
	public void TC_03_Ajax_loading() { 
		
	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	//Wait cho datetimepicker hien thi(visibillity)
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")));
	//in ra ngay da chon
	Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");
	//cho click
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//a[text()='27']")));
	driver.findElement(By.xpath("//td//a[text()='27']")).click();
	
	//cho ajax ko con visible
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']")));
	

	
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='27']")));
Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='27']")).isDisplayed());
Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Saturday, November 27, 2021");

	}
	@Test
	public void TC_04_upload_file() {
		driver.get("https://gofile.io/?t=uploadFiles");
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-upload mr-1']")));
		
		
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary btn-lg mb-1 uploadButton']")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(haifilepath + "\n" + excelfilepath );

		//wait chooseSever icon invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress-selectServer")));
	
		//wai for file upload success + //wait for progessbar visible
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='progress position-relative mt-1']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'text-truncate')]")));
	 //wait success message display
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		//wait show file button clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='rowUploadSuccess-showFiles']")));
		driver.findElement(By.xpath("//button[@id='rowUploadSuccess-showFiles']")).click();
		
		//verify img upload success
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()= '" + haifilename + "']")).isDisplayed());
	
	
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
	