package testcases;

import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;
import utilities.TestData;
import utilities.TestUtility;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class DashboardTestcases {
	private WebDriver dr;
	private LoginPage lp;
	private DashboardPage dp;
	
  @Test(priority=2, description="Verify Quick Launch items", groups= {"Regression", "sanity"})
  public void verifyQuickLaunchItems() {
	  lp.loginToApp(TestData.username, TestData.password);
	  Assert.assertTrue(dp.isLoggedUserDisplayed());
	  TestUtility.attachScreenshotToReport();
	  List<String> actual = dp.getQuickLaunchItems();
	  List<String> exp=new ArrayList<String>();
	  exp.add("Assign Leave");
	  exp.add("Leave List");
	  exp.add("Timesheets");
	  exp.add("Apply Leave");
	  exp.add("My Leave");
	  exp.add("My Timesheet");
	  Reporter.log(actual+"-"+exp);
	  Assert.assertEquals(actual, exp);
	  
  }
  @Test(priority=1,description="Verify Quick launch count", groups={"sanity"})
  public void verifyQuickLaunchItemsCount() {
	  lp.loginToApp(TestData.username, TestData.password);
	  Assert.assertTrue(dp.isLoggedUserDisplayed());
	  int actual = dp.getQuickLaunchItemsCount();
	  TestUtility.attachScreenshotToReport();
	  Reporter.log(actual+" - "+6);
	  Assert.assertEquals(actual, 6);
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  dr=TestBase.getInstance();
	  lp= new LoginPage(dr);
	  dp= new DashboardPage(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  dp.logoutFromApp();
	  dr.quit();
  }

}
