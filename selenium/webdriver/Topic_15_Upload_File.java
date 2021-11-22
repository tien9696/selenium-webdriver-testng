 package webdriver;

import java.util.List;
import java.util.Set;
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

public class Topic_15_Upload_File {
	WebDriver driver;
	WebDriverWait expliciwait;
	String haifilename = "2.png";
	String excelfilename = "excel.png";
	String sefilename = "se.png";

	String projectPath = System.getProperty("user.dir");
	String haifilepath = projectPath + "\\UploadFile\\" + haifilename ;
	String excelfilepath = projectPath + "\\UploadFile\\" + excelfilename ;
	String sefilepath = projectPath + "\\UploadFile\\" + sefilename ;

	
	 
    @BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		expliciwait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ID() {
				driver.get("https://blueimp.github.io/jQuery-File-Upload/");
			
				driver.findElement(By.xpath("//input[@type='file']")).sendKeys(haifilepath);
				Assert.assertTrue(driver.findElement(By.xpath("//p[text()='2.png']")).isDisplayed());
				driver.findElement(By.xpath("//span[text()='Start']")).click();
				Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2.png']")).isDisplayed());

	}

	//@Test
	public void TC_02_Multiple_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(haifilepath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(sefilepath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(excelfilepath);
        
		List<WebElement> startbuton = driver.findElements(By.xpath("//span[text()='Start']"));
		 for (WebElement startbuton1 : startbuton) {
			 startbuton1.click();
			 sleepInsecond(1);
		}
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2.png']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='excel.png']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//a[text()='se.png']")).isDisplayed());


		
	}
	@Test
	public void TC_03_Upload_flow() {
	
	   driver.get("https://gofile.io/uploadFiles");
	   
	   String parentID = driver.getWindowHandle();
	   driver.findElement(By.xpath("//input[@type='file']")).sendKeys(haifilepath + "\n" + excelfilepath + "\n" + sefilepath );
	//wait cho h5 visible
	   //trc khi upload hinh thi text h5 nay da co nen dung ham Wait 
	   expliciwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
	   
       Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());

       driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();
       switchToWindowByID(parentID);
       Assert.assertTrue(driver.findElement(By.xpath("//span[text()='2.png']")).isDisplayed());
	}
	
	//@AfterClass
	public void afterclass(){
		driver.quit();
	}
	public void switchToWindowByID(String Window) {
		//get het ra id dang co
		Set<String> allWindownIDs = driver.getWindowHandles();
		//duyet qua cac gia tri trong all windows
		for (String id : allWindownIDs) {
			//kiem tra dk neu nhu kha vs window truyen vao thi switch
			if (!id.equals(Window)) {
				driver.switchTo().window(id);
				//thoat khoi vong lap
				break;
			}
		}
	}
	
	
	public void sleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
	