 package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Topic_10_Button_Radiobutton_checkbox {
	WebDriver driver;
	
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir");
	//WebDriverWait expliciWait; // class
	

	By loginbutton = By.xpath("//button[@class='fhs-btn-login']");
	By usernameTextbox = By.xpath("//input[@id='login_username']");
	By passwordTextbox = By.xpath("//input[@id='login_password']");


	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		//expliciWait = new WebDriverWait(driver, 15);


		jsExecutor = (JavascriptExecutor) driver ;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_ID() {
				driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
				driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
				Assert.assertFalse(driver.findElement(loginbutton).isEnabled());
				driver.findElement(usernameTextbox).sendKeys("automation@gmail.com");
				driver.findElement(passwordTextbox).sendKeys("123456");
				sleepInsecond(3);
				Assert.assertTrue(driver.findElement(loginbutton).isEnabled());
   
	}
	
   public void TC_02_Radio_checkbox() {
	   driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	   driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).click();
	   sleepInsecond(3);
	   
	   Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).isSelected());
	   driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input")).click();
	   sleepInsecond(3);
	   
	   Assert.assertFalse(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).isSelected());
	   Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
	   
	}

public void TC_03_try() {

	   
	   driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

       sleepInsecond(3);
       driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']")).click();
	   sleepInsecond(3);
	   
	   Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).isSelected());
    
	   
   }
	
	public void TC_04_Name() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');",driver.findElement(By.xpath("//div[@class='header-breadcrumbs  background-menu-none-homepage  ']//span[text()='Sách Trong Nước']")));
		sleepInsecond(5);
		driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']")).click();
		   sleepInsecond(3);
		   
		   Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).isSelected());
	       
	}
	
	public void TC_05_checkbox() {
		driver.findElement(By.xpath("//label[text()='Rear side airbags']")).click();
		driver.findElement(By.xpath("//label[text()='Heated front and rear seats']")).click();
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']")).click();
		sleepInsecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		}
	//@Test
	public void TC_06_all_checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		
		
		
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox' and not (@disabled)]"));
        
        //select all checkbox
        for (WebElement checkbox : allCheckboxes ) {
        	if(!checkbox.isSelected()) {
        	checkbox.click();
        	sleepInsecond(3);
        	}
      	   Assert.assertTrue(checkbox.isSelected());
      	   }
       //deselect-all checkbox
        for (WebElement checkbox : allCheckboxes) {
        	if (checkbox.isSelected()) {
        		checkbox.click();
        		sleepInsecond(2);
        	}
       	   Assert.assertFalse(checkbox.isSelected());

		}
      
	
	}
	
	public void TC_07_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		//driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input")).click();
		//sleepInsecond(2);
		//Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span")).isSelected());
		
		//driver.findElement(By.xpath("//span[contains(text(),' Sprin')]")).click();
		//sleepInsecond(2);
		//Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input")).isSelected());
		
		//dung label de click va input de verify
		//dung input de click (JS) va input de verify
		By springRadiobutton = By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();",driver.findElement(springRadiobutton));
		
        sleepInsecond(3);
		
		Assert.assertTrue(driver.findElement(springRadiobutton).isSelected());

	}
	@Test
	public void TC_08_Custom_checkbox_google_form() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		driver.findElement(By.xpath("//span[text()='Cần Thơ']/ancestor::div[@class='docssharedWizToggleLabeledContent']/preceding-sibling::div")).click();
		sleepInsecond(2);
		//div[@aria-label='Cần Thơ' and @aria-checked='true']
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		 
		//select all checkbox with start-with text = 'Quang'
		List<WebElement> allcheckboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng')]/div[contains(@class,'exportInnerBox')]"));
		
		for (int i = 0; i < allcheckboxes.size(); i++){
			allcheckboxes.get(i).click();
			sleepInsecond(2);
		}
		List<WebElement> allcheckboxesSelected = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role = 'checkbox' and @aria-checked='true']"));
        for (WebElement checkbox : allcheckboxesSelected ) {
        	Assert.assertTrue(checkbox.isDisplayed());
        }
        allcheckboxesSelected = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role = 'checkbox']"));
        for (WebElement checkbox : allcheckboxesSelected ) {
        	Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
        }
	}
	
	
	
	
	//@AfterClass
	//public void afterclass(){
		//driver.quit();
	//}
	public void sleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	}
	