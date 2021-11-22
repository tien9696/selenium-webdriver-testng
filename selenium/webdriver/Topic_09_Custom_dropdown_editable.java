 package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_dropdown_editable {
	WebDriver driver; //interface
	String projectPath = System.getProperty("user.dir");
	WebDriverWait expliciWait; // class
	JavascriptExecutor jsExecutor; //interface
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 15);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
 	}

	
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemIncustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text() = '8']")).isDisplayed());
		selectItemIncustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "16");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text() = '16']")).isDisplayed());

		selectItemIncustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text() = '3']")).isDisplayed());

		
	}

	

	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemIncustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Justen Kitsune");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text() ='Justen Kitsune']")).isDisplayed());
		selectItemIncustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Stevie Feliciano");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text() ='Stevie Feliciano']")).isDisplayed());
		selectItemIncustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Matt");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text() ='Matt']")).isDisplayed());
		

		
	}

	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemIncustomDropdown("//div[@class='btn-group']", "//ul[@class ='dropdown-menu']//a", "Second Option");
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text() ,'Second Option')]")).isDisplayed());
		
		selectItemIncustomDropdown("//div[@class='btn-group']", "//ul[@class ='dropdown-menu']//a", "Third Option");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
		
		
		
		
	}
	
	public void TC_04_Default() {
		driver.get("https://demo.nopcommerce.com/register");

		selectItemIncustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "28");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='28']")).isSelected());
		selectItemIncustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "15");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='15']")).isSelected());
		selectItemIncustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "31");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='31']")).isSelected());
		
		
	}
	
	public void TC_05_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemIncustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "Basketball");
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),"Basketball");
		
		selectItemIncustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "American Football");
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),"American Football");
		
		selectItemIncustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "Badminton");
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),"Badminton");
		
		
	}
     @Test 
     public void TC_06_Editable() {
 		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

 		selectItemeditableDropdown("//div[@role='combobox']/input", "//div[@role='option']/span", "Afghanistan");
    	 sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='Afghanistan']")).isDisplayed());

     }
	
	
	
	@AfterClass
	public void afterclass(){
		driver.quit();
	}
	//click vao the cha cho xo ra cac the trong
	public void selectItemIncustomDropdown(String  parentXpath, String childxpath, String expectedTextItem) {
		
	}
		public void selectItemeditableDropdown(String  parentXpath, String childxpath, String expectedTextItem) {

			//click vao text box de hien con tro chuot len
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInsecond(3);

		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedTextItem);
		sleepInsecond(3);

		
		List<WebElement> allItem = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childxpath)));
		System.out.println("Item size = " + allItem.size());
		
		
		
		// neu muon chon 1 item nao do, phai get cua item do ra de so sanh
		//neu bang voi cai minh muon thi click -> thoat vong lap
		//neu chua bang thi duyet tiep
		for (WebElement item : allItem) {
			if(item.getText().trim().equals(expectedTextItem)) {
				System.out.println(item.getText());
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInsecond(3);

				item.click();
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
	