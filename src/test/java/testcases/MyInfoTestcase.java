package testcases;

import org.testng.annotations.Test;


import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;
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

public class MyInfoTestcase {
	
	private WebDriver dr;
	private LoginPage lp;
	private DashboardPage dp;
	private MyInfoPage ip;
  @Test(priority=1, enabled=true, description="Verify MyInfo Side menu items", dependsOnMethods="verifyMyInfoSideMenuCount"
		  , groups={"Regression"})
  public void verifyMyInfoSideMenuItems() {
	  lp.loginToApp(TestData.username, TestData.password);
	  Assert.assertTrue(dp.isLoggedUserDisplayed());
	  ip.gotoMyInfoPage();
	  List<String> actual = ip.getQuickLaunchItems();
	  
	  List<String> exp = new ArrayList<String>();
	  exp.add("Personal Details");
	  exp.add("Contact Details");
	  exp.add("Emergency Contacts");
	  exp.add("Dependents");
	  exp.add("Immigration");
	  exp.add("Job");
	  exp.add("Salary");
	  exp.add("Tax Exemptions");
	  exp.add("Report-to");
	  exp.add("Qualifications");
	  exp.add("Memberships");
	  TestUtility.attachScreenshotToReport();
	  Reporter.log(actual+"-"+exp);
	  Assert.assertEquals(actual, exp);
  }
  @Test(priority=2, enabled=true, description="Verify My Info Count", groups={"Regression"})
  public void verifyMyInfoSideMenuCount() {
	  lp.loginToApp(TestData.username, TestData.password);
	  Assert.assertTrue(dp.isLoggedUserDisplayed());
	  ip.gotoMyInfoPage();
	  int actual = ip.getMyInfoSideMenuItemsCount();
	  TestUtility.attachScreenshotToReport();
	  Reporter.log(actual+"-"+11);
	  Assert.assertEquals(actual, 11);
	 // Assert.fail();
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  dr=TestBase.getInstance();
	  lp=new LoginPage(dr);
	  dp=new DashboardPage(dr);
	  ip=new MyInfoPage(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  dp.logoutFromApp();
	  dr.quit();
  }

}
