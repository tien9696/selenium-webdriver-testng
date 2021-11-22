package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Default_Dropdown {
	WebDriver driver;
	Select daydropdown, monthdropdown, yeardropdown;
	String projectPath = System.getProperty("user.dir");
	String firstname, lastname, email, password, day, month, year;
	By maleRadioBy = By.id("gender-male");
	By firstnameTextboxBy = By.id("FirstName");
	By lastnameTextboxBy = By.id("LastName");
	By emailTextboxBy = By.id("Email");
	By datedropdownBy = By.name("DateOfBirthDay");
	By monthdropdownBy = By.name("DateOfBirthMonth");
	By yeardropdownBy = By.name("DateOfBirthYear");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstname = "Automation";
		lastname = "FC";
		email = getRandomEmail();
		password = "automationfc";
		day = "31";
		month = "March";
		year = "1915";
				

	}

	@Test
	public void TC_01_ID() {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(maleRadioBy).click();
		driver.findElement(firstnameTextboxBy).sendKeys(firstname);
		driver.findElement(lastnameTextboxBy).sendKeys(lastname);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		daydropdown = new Select(driver.findElement(datedropdownBy));
		daydropdown.selectByVisibleText(day);
		sleepInSecond(5);

		// kiem tra ko phai multi
		Assert.assertFalse(daydropdown.isMultiple());
		//kt co bn item
		Assert.assertEquals(daydropdown.getOptions().size(), 32);
		//kt xem chon duoc ngay 31 chua
		Assert.assertEquals(daydropdown.getFirstSelectedOption().getText(), "31");
		
		monthdropdown = new Select(driver.findElement(monthdropdownBy));
		monthdropdown.selectByVisibleText(month);
		
		yeardropdown = new Select(driver.findElement(yeardropdownBy));
		yeardropdown.selectByVisibleText(year);
		
		driver.findElement(By.id("register-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector(".ico-account")).click();
		Assert.assertTrue(driver.findElement(maleRadioBy).isSelected());
		Assert.assertEquals(driver.findElement(firstnameTextboxBy).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(lastnameTextboxBy).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
		
		daydropdown = new Select(driver.findElement(datedropdownBy));
		Assert.assertEquals(daydropdown.getFirstSelectedOption().getText(), day);
		monthdropdown = new Select(driver.findElement(monthdropdownBy));
		Assert.assertEquals(monthdropdown.getFirstSelectedOption().getText(), month);
		yeardropdown = new Select(driver.findElement(yeardropdownBy));
		Assert.assertEquals(yeardropdown.getFirstSelectedOption().getText(), year);

		
		

		
		

	}

	private void sleepInSecond(int i) {
		// TODO Auto-generated method stub
		
	}

	// @Test
	public void TC_02_class() {

	}

	@Test
	public void TC_03_Name() {

	}

	@AfterClass
	public void afterclass() {
		driver.quit();
	}
	public String getRandomEmail() {
		Random rand = new Randdom();
		return "automation" + rand.nextInt(9999) + "@hotmail.com";
	}
}
