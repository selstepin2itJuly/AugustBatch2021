package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.SyncClass;

public class ForgotPasswordPage {
	private WebDriver dr;
	private SyncClass sync;
	private static Logger log=Logger.getLogger(DashboardPage.class);
	
	public ForgotPasswordPage(WebDriver driver)
	{
		log.info("ForgotPassword Page Constructor");
		this.dr=driver;
		sync=new SyncClass(driver);
		log.info("Initilizing Forgot Password Page");
		PageFactory.initElements(dr, this);//important to initialize the element of class
	}
	
	@FindBy(css="input[id='btnSearchValues']")
	WebElement resetPassworButton;
	
	
	public Boolean isResetPasswordButtonDisplayed()
	{
		sync.waitForElement(resetPassworButton);
		boolean b=false;
		try {
			b=resetPassworButton.isDisplayed();
			log.debug("Reset Password Button displayed");
		}catch(Exception e)
		{
			log.info("Reset Password Button not displayed");
		}
		return b;
	}
	
	public String getResetButtonText()
	{
		log.info("Reset button text:"+resetPassworButton.getText());
		return resetPassworButton.getText();
	}
}
