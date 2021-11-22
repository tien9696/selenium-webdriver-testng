 package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Javascrip_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	private String emailAddress;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Validation_message() {

		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='fname']","Automation");
		sleepInsecond(3);
		
		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='pass']","123456");
		sleepInsecond(3);
		
		sendkeyToElementByJS("//input[@id='em']","123");
				clickToElementByJS("//input[@name='submit-btn']");
        sleepInsecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");
		
		sendkeyToElementByJS("//input[@id='em']","123@234");  

		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");
		sendkeyToElementByJS("//input[@id='em']","123!@#");	
		sleepInsecond(3);

		clickToElementByJS("//input[@name='submit-btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");

		sendkeyToElementByJS("//input[@id='em']","123@234.345");
		sleepInsecond(3);
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");

		


		
		

	}

	@Test
	public void TC_02_New_customer() {
		driver.get("http://demo.guru99.com/v4");

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

		emailAddress = getRandomEmail();
		gender = "male";
		dod = "1996-01-01";
		address = "77 Po new york";
		name = "John deep";
		city = "Califonia";
		state = "Hawai";
		pin = "012345";
		phone = "012345678";

		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
			 "Welcome To Manager's Page of Guru99 Bank");
		// input[@name = 'uid']

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(genderRadio).click();
		
		removeAttributeInDOM("//input[@name='dob']","type");
		sleepInsecond(3);
		
		driver.findElement(dodTextbox).sendKeys(dod);
		
		
		
		driver.findElement(addressbox).sendKeys(address);
		driver.findElement(CityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(PinTextbox).sendKeys(pin);
		driver.findElement(MobileNumberTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordsTextbox).sendKeys(password);

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


		
		
	}
	
	
	//@AfterClass
	public void afterclass(){
		driver.quit();
	}
	public String getRandomEmail() {
		Random rand = new Randdom();
		return "Johndeep" + rand.nextInt(9999) + "@hotmail.com";
	}
	
	
	public void sleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInsecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
	if (status) {
		return true;
	}
	return false;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}



}