package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.SyncClass;

public class LoginPage {

	private WebDriver dr;
	
	private static Logger log=Logger.getLogger(LoginPage.class);
	public LoginPage(WebDriver driver)
	{
		log.info("Login Page Constructor");
		this.dr=driver;
		
		log.info("Initializing Login Page");
		PageFactory.initElements(dr, this);//important to initialize the element of class
	}
	//login page elements
	@FindBy(id="txtUsername")
	WebElement user;
	
	@FindBy(name="txtPassword")
	WebElement pass;
	
	@FindBy(className="button")
	WebElement loginButton;
	
	@FindBy(linkText="Forgot your password?")
	WebElement forgotPass;
	
	@FindBy(id="spanMessage")
	WebElement error;
	//login page functionality
	
	public void loginToApp(String username, String password)
	{
		log.info("Login to app");
		user.sendKeys(username);
		pass.sendKeys(password);
		loginButton.click();
	}
	
	public void clickOnForgotPassword()
	{
		log.info("going to Forgot Password Page");
		forgotPass.click();
		
	}
	
	public Boolean isErrorDisplayed()
	{
		boolean b=false;
		try {
			b=error.isDisplayed();
			log.info("Error text displayed");
		}catch(Exception e)
		{
			log.info("Error text not displayed");
		}
		return b;
	}
	public String getErrorText()
	{
		log.info("erros Text:"+error.getText());
		return error.getText();
	}
}
