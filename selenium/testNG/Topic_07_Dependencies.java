package testNG;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dependencies {

	@BeforeClass()
	public void initBrowser() {
	System.out.println("Open Browser");

	
}
	@Test
	public void TC_01_Create_User() {
		 Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "TC_01_Create_User" )
	public void TC_02_View_User() {
		 
	}
	@Test(dependsOnMethods = "TC_02_Create_User" )
	public void TC_03_Edit_User() {
		 
	}
	@Test(dependsOnMethods = "TC_03_Create_User" )
	public void TC_04_Delete_User() {
		 
	}

	@AfterClass()
	public void cleanBrowser() {
		System.out.println("Close Browser");
	}
	
}
