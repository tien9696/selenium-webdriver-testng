package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	@Test(description = "JIRA#1241")
	public void TC_01() {
		 String employeename = "Tran Van Em";
		 Assert.assertTrue(employeename.equals("Tran Van Em"));
		 Assert.assertFalse(employeename.equals("Tran Van Anh"));

	}
	@Test(description = "Create a new User")
	public void TC_02() {
		 String employeename = "Tran Van Em";
		 Assert.assertTrue(employeename.equals("Tran Van Em"));
		 Assert.assertFalse(employeename.equals("Tran Van Anh"));

	}
}
