 package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.sun.org.apache.xml.internal.utils.QName;


public class Topic_16_Wait_Part_V {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	FluentWait<WebElement> fluentElement;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
}

	//@Test
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement CountdownTimer = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement> (CountdownTimer);
		fluentElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function <WebElement, Boolean>() {

			@Override
			public Boolean apply (WebElement countdown) { 
				boolean status = countdown.getText().endsWith("00");
				System.out.println("Text = " + countdown.getText() + "------" + status);
				// TODO Auto-generated method stub
				return status;
				
			}
			
		});

	}
//	
	//@Test
	public void TC_02_Name() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()='Hello World!']")));
	}
	
	
	@Test
	public void TC_03_Name() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
	    driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	    
	    Assert.assertTrue(isJQueryLoadSuccess(driver));
	    
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='total']//span")).getText(), "3 month(s)");
        driver.findElement(By.xpath("//b[text()='PIM']")).click();
        

		driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys("Peter Mac");
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
	    Assert.assertTrue(isJQueryLoadSuccess(driver));
	    Assert.assertTrue(driver.findElement(By.xpath("//tr//td[text()='Chief Financial Officer']")).isDisplayed());

	}
	
	public boolean isJQueryLoadSuccess (WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return(Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0)");
				// TODO Auto-generated method stub
				
			}
			
		};
		return explicitWait.until(jQueryLoad);
		
	}
	
	
	
	@AfterClass
	public void afterclass(){
		driver.quit();
	}
	long timeout = 15;
	long polling = 1;
	
	public WebElement getElement(By locator) {
		FluentWait <WebDriver> wait = new FluentWait <WebDriver> (driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery (Duration.ofSeconds(polling))
				.ignoring (NoSuchElementException.class);
		
		WebElement element = wait.until(new Function <WebDriver, WebElement>(){
			public WebElement apply (WebDriver driver) {
			return driver.findElement(locator);
		}
	});
		return element;
	}
	
	
	public void  clickToElement(By locator) {
		FluentWait <WebDriver> wait = new FluentWait <WebDriver> (driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery (Duration.ofSeconds(polling))
				.ignoring (NoSuchElementException.class);
		
		WebElement element = wait.until(new Function <WebDriver, WebElement>(){
			public WebElement apply (WebDriver driver) {
			return driver.findElement(locator);
		}
	});
		element.click();
	}
	
	public Boolean isElementDisplayed(By locator) {
		FluentWait <WebDriver> wait = new FluentWait <WebDriver> (driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery (Duration.ofSeconds(polling))
				.ignoring (NoSuchElementException.class);
		
		Boolean status = wait.until(new Function <WebDriver, Boolean>(){
			public Boolean apply (WebDriver driver) {
			return driver.findElement(locator).isDisplayed();
		}
	});
		return status;
	}
	
	public void sleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
	