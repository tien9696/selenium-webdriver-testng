 package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Windown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ID() {
				driver.get("https://kyna.vn/");
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@allowtransparency='false']")));
				
				String likeNumber = driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText();
				System.out.println();
				
				driver.switchTo().defaultContent();
				
				String like = driver.findElement(By.xpath("//img[@alt='Khóa học online nâng cao kỹ năng cùng các chuyên gia đầu ngành']")).getText();
				System.out.println();
				
				
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
				//driver.findElement(By.xpath("//label[text()='0 new messages']//parent::div//parent::div[@class='button_text']//following-sibling::div[@class='blinds blinds_top']")).click();

				//label[text()='0 new messages']//parent::div//parent::div[@class='button_text']//following-sibling::div[@class='blinds blinds_top']
				
				driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();

				driver.findElement(By.xpath("//label[contains(text(),'Nhập thông tin của bạn')] //following-sibling::input[@placeholder='Nhập tên của bạn']")).sendKeys("automation");
				
				driver.findElement(By.xpath("//label[contains(text(),'Nhập thông tin của bạn')] //following-sibling::input[@placeholder='Nhập số điện thoại của bạn']")).sendKeys("09123456");
				
				//driver.findElement(By.xpath("//label[text()='Chọn dịch vụ hỗ trợ'] //parent::label//following-sibling::select")).click();
				driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("automation boottrap");
				
				driver.switchTo().defaultContent();
				
				driver.findElement(By.xpath("//label[text()='Tìm kiếm']//following-sibling::input[@placeholder='Nhập tên khóa học/giảng viên']")).sendKeys("excel");
				driver.findElement(By.xpath("//button[@aria-label='search']")).click();
				List<WebElement> allexcel = driver.findElements(By.xpath("//h4[contains(text(),'Excel')]"));
				//List<WebElement> allexcel = driver.findElements(By.cssSelector(".content>h4"));

				for (WebElement course : allexcel) {
					
					Assert.assertTrue(course.getText().contains("Excel"));
					
				}

				
				
				
	}

	//@Test
	public void TC_02_Window_tab() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	// ham de get ra windown/tab. tai cai tab dang active
	
	String parentTabID = driver.getWindowHandle();
	System.out.println("parent ID = " + parentTabID);
	
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	
	//switch vao gg(vi phu dinh)
	switchToWindowByID(parentTabID);
	
	//verify da switch qua chua
	String GoogleTab = driver.getWindowHandle();
     driver.findElement(By.xpath("//input[@name='q']")).sendKeys("selenium");
     //switch vao parent
     switchToWindowByID(GoogleTab);
     driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
     
     //switch to FB tab
     switchToWindowByTitle("Facebook – log in or sign up");
     //switch to parent tab
     switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
     
     driver.findElement(By.xpath("//a[text()='TIKI']")).click();
     sleepInsecond(3);
     //switch to TiKi
     
     switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
     sleepInsecond(3);
     
     Assert.assertTrue(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).isDisplayed());
     
    // Assert.assertTrue(driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).isDisplayed());
     //swith vef parent
     
	
	}
	@Test
	public void TC_03_Window_tab() {
		driver.get("https://kyna.vn/");
		
		String parentTabID = driver.getWindowHandle();
		System.out.println("parent ID = " + parentTabID);
		
		driver.findElement(By.xpath("//footer//div[@id='k-footer']//img[@alt='facebook']")).click();
        switchToWindowByID(parentTabID);
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).isDisplayed());
        
    	String facebookTab = driver.getWindowHandle();
        switchToWindowByID(facebookTab);
        driver.findElement(By.xpath("//footer//div[@id='k-footer']//img[@alt='youtube']")).click();
        closeAllTabWithoutParent(parentTabID);
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
		
	public void switchToWindowByTitle(String TabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		//duyet qua cac gia trij trong all window
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(TabTitle)) {
				break;
			}
		}
	}
	public void closeAllTabWithoutParent(String parentID) {
		Set<String> allWinDowIDs = driver.getWindowHandles();
		//duyet qua cac gia tri trong tat ca win dow
		for (String id : allWinDowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
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
	