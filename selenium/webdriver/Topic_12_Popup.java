 package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	private long defaultTimeout;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ID() {
				driver.get("https://www.zingpoll.com/about");
				sleepInsecond(2);
				
				By signPopup = By.xpath("//h4[@id='myModalLabel_signUpLbl']/preceding-sibling::h4");
				driver.findElement(By.xpath("//a[@id='Loginform']")).click();
				Assert.assertTrue(driver.findElement(signPopup).isDisplayed());

				Assert.assertEquals(driver.findElement(signPopup).getText(), "Sign In");
				sleepInsecond(2);
				driver.findElement(By.xpath("//input[@id='loginEmail']")).sendKeys("cam@gmail.com");
				driver.findElement(By.xpath("//input[@id='loginPassword']")).sendKeys("123456");
				driver.findElement(By.xpath("//span[@aria-hidden='true']")).click();
				Assert.assertFalse(driver.findElement(signPopup).isDisplayed());
	}

	//@Test
	public void TC_02_Home_pupup() {
		driver.get("https://www.zingpoll.com/about");
		sleepInsecond(2);
		
		By signPopup = By.xpath("//h4[@id='myModalLabel_signUpLbl']/preceding-sibling::h4");
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		Assert.assertTrue(driver.findElement(signPopup).isDisplayed());

		Assert.assertEquals(driver.findElement(signPopup).getText(), "Sign In");
		sleepInsecond(2);
		driver.findElement(By.xpath("//input[@id='loginEmail']")).sendKeys("cam@gmail.com");
		driver.findElement(By.xpath("//input[@id='loginPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//span[@aria-hidden='true']")).click();
		Assert.assertFalse(driver.findElement(signPopup).isDisplayed());

	}
	@Test
	public void TC_04_RanDom_popup() {
		driver.get("https://shopee.vn/");
		String searchKeyword = "Macbook Pro";
		By homePopup = By.xpath("//img[@alt='home_popup_banner']");
		if (isElementDisplay(homePopup)) {
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
			sleepInsecond(3);
		}
		driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys("searchKeyword");
		driver.findElement(By.xpath("//button[@class='btn btn-solid-primary btn--s btn--inline']")).click();
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='col-xs-2-4 shopee-search-item-result__item'] //div[@data-sqe='name']//div[@class='_1N6I4W']"));


		for (WebElement product : products) {
			String productName =  product.getText().toLowerCase();
			
			
			
			Assert.assertTrue(productName.contains(searchKeyword.split(" ")[0].toLowerCase()) || (productName.contains(searchKeyword.split(" ")[1].toLowerCase())));
		}
		
				
				
				
		
	}
	public boolean isElementDisplay (By by) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		
		if(elements.size()==0) {
			return false;
		}else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			return false;
		}else {
			return true;
		}
				
				
		
		
		
		
		
		
		
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
	