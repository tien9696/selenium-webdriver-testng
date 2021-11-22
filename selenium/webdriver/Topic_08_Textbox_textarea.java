package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	String loginPageUrl, userID, password, dod, address, name, city, state, pin, phone, gender, CustomerID;
	String editAddress, editcity, editstate, editpin, editphone, editemailAddress;

	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='m']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By dodTextbox = By.name("dob");
	By addressbox = By.name("addr");
	By CityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By PinTextbox = By.xpath("//input[@name='pinno']");
	By MobileNumberTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordsTextbox = By.xpath("//input[@name='password']");

	
	
	
	
	
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		emailAddress = getRandomEmail();
		gender = "male";
		dod = "1996-01-01";
		address = "77 Po new york";
		name = "John deep";
		city = "Califonia";
		state = "Hawai";
		pin = "012345";
		phone = "012345678";

		editAddress = "62 po Ca";
		editcity = "58 new York";
		editstate = "Califonia";
		editpin = "054321";
		editphone = "0935784521";
		editemailAddress = getRandomEmail();

	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get("http://demo.guru99.com/v4");

		driver.getCurrentUrl();
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
			 "Welcome To Manager's Page of Guru99 Bank");
		// input[@name = 'uid']

	}

	@Test
	public void TC_03_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(genderRadio).click();
		driver.findElement(dodTextbox).sendKeys(dod);
		driver.findElement(addressbox).sendKeys(address);
		driver.findElement(CityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(PinTextbox).sendKeys(pin);
		driver.findElement(MobileNumberTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordsTextbox).sendKeys("654321");

		driver.findElement(By.xpath("//input[@name='sub']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dod);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(), state);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailAddress);

		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	//@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name = 'cusid']")).sendKeys(CustomerID);
		driver.findElement(By.xpath("//input[@name = 'AccSubmit']")).click();

		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement((dodTextbox)).getAttribute("value"), dod);
		Assert.assertEquals(driver.findElement(addressbox).getAttribute("value"), address);
		Assert.assertEquals(driver.findElement(CityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(PinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(MobileNumberTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), emailAddress);

		
		driver.findElement(addressbox).clear();

		driver.findElement(addressbox).sendKeys(editAddress);
		driver.findElement(CityTextbox).clear();

		driver.findElement(CityTextbox).sendKeys(editcity);
		driver.findElement(stateTextbox).clear();

		driver.findElement(stateTextbox).sendKeys(editstate);
		driver.findElement(PinTextbox).clear();

		driver.findElement(PinTextbox).sendKeys(editpin);
		driver.findElement(MobileNumberTextbox).clear();

		driver.findElement(MobileNumberTextbox).sendKeys(editphone);
		driver.findElement(emailTextbox).clear();

		driver.findElement(emailTextbox).sendKeys(editemailAddress);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				 "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), CustomerID);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dod);
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editcity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editstate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editpin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editemailAddress);

		
		
		
		

		
		
		
		
		
		
		
	}

	@AfterClass
	// public void afterclass() {
	// driver.quit();
	// }

	public String getRandomEmail() {
		Random rand = new Randdom();
		return "Johndeep" + rand.nextInt(9999) + "@hotmail.com";
	}
}
