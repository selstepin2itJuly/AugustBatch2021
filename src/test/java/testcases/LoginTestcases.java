package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import testbase.TestBase;
import utilities.TestData;
import utilities.TestUtility;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class LoginTestcases {
	private WebDriver dr;
	private LoginPage lp;
	private DashboardPage dp;
	private ForgotPasswordPage fpp;
  @Test(priority=1, description="Verify Login Successful", groups={"sanity"})
  public void verifyLoginSuccessful_001() {
	  lp.loginToApp(TestData.username, TestData.password);
	  boolean b=dp.isLoggedUserDisplayed();
	  TestUtility.attachScreenshotToReport();
	  Assert.assertTrue(b);
	  dp.logoutFromApp();
	  TestUtility.attachScreenshotToReport();
  }
  
  @Parameters({"user", "pass"})
  @Test(priority=2, description="Verify Login UnSuccessful",groups= {"Regression","sanity"})
  public void verifyLoginUnsuccessful_002(String user, String pass) {
	  lp.loginToApp(user, pass);
	  boolean b=lp.isErrorDisplayed();
	  TestUtility.attachScreenshotToReport();
	  Assert.assertTrue(b);
	  Reporter.log(lp.getErrorText()+"-"+"Invalid credentials");
	  Assert.assertEquals(lp.getErrorText(), "Invalid credentials");
	  
	  
  }
  
  @Test(priority=3, description="Verify Forgot Password link", groups={"sanity"})
  public void verifyForgotPassword_003() {
	  lp.clickOnForgotPassword();
	  boolean b=fpp.isResetPasswordButtonDisplayed();
	  TestUtility.attachScreenshotToReport();
	  Assert.assertTrue(b);
	  //Assert.assertEquals(fpp.getResetButtonText(), "Reset Password");
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  dr=TestBase.getInstance();
	  lp=new LoginPage(dr);
	  dp=new DashboardPage(dr);
	  fpp=new ForgotPasswordPage(dr);
	  
  }

  @AfterMethod(alwaysRun=true, enabled=true)
  public void afterMethod() {
	  dr.quit();
  }

}
