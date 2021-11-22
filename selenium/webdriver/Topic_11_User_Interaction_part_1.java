 package webdriver;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import sun.awt.util.PerformanceLogger;


public class Topic_11_User_Interaction_part_1 {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		

	}

	//@Test
	public void TC_02_Click_and_Hold_1() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
		
		sleepInsecond(3);
		
		action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
        //Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Home Furnishing']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//span[@itemprop='title' and text()='Home']")).getText(), "Home");
		}
	

	//@Test
	public void TC_03_Click_And_Hold_2() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangel = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		action.clickAndHold(rectangel.get(0))
		.moveToElement(rectangel.get(3))
		.release()
		.perform();
		sleepInsecond(5);
		Assert.assertEquals(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(),4);
		
		
	}
	private void perform() {
		// TODO Auto-generated method stub
	}

	//@Test
	public void TC_04_Click_and_select() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangel = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(rectangel.get(0))
		.click(rectangel.get(2))
		.click(rectangel.get(5))
		.click(rectangel.get(10))
		.perform();
		action.keyUp(Keys.CONTROL).perform();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(),4);

	}
	//@Test
	public void TC_05_Right_click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste:not(.context-menu-hover):not(.context-menu-visible)")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-paste"))).perform();
	}
	@Test
	public void TC_06_Drag_and_drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourcecircle = driver.findElement(By.id("draggable"));
		WebElement targetcircle = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(sourcecircle, targetcircle).perform();
		Assert.assertEquals(targetcircle.getText(), "You did great!");

		
		
	}
	//@Test
	public void TC_07_Right_click() {
		
		
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
	