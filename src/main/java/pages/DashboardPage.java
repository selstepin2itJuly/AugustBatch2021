package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.SyncClass;

public class DashboardPage {

	private WebDriver dr;
	private SyncClass sync;
	private static Logger log=Logger.getLogger(DashboardPage.class);
	
	public DashboardPage(WebDriver driver)
	{
		log.info("Dashboard Page Constructor");
		this.dr=driver;
		sync=new SyncClass(dr);
		log.info("Initializing Page Elements");
		PageFactory.initElements(dr, this);//important to initialize the element of class
	}
	//Elements of Dashbaord Page
	@FindBy(id="welcome")
	WebElement loggeduser;
	
	@FindBy(linkText="Logout")
	WebElement logout;
	
	@FindBy(xpath="//div[@id='group_0']/descendant::legend")
	WebElement quickLaunchLegend;
	
	@FindBy(xpath="//div[@id='group_0']/descendant::span[@class='quickLinkText']")
	List<WebElement> quickLaunchItems;
	
	// funtionality of Dashboard Page
	public Boolean isLoggedUserDisplayed()
	{
		boolean b=false;
		try {
			b=loggeduser.isDisplayed();
		}catch(Exception e)
		{
			
		}
		log.info("Verifying Logged user displayed");
		return b;
	}
	
	public void logoutFromApp()
	{
		log.info("Logout of application");
		loggeduser.click();
		sync.waitForElement(logout);
		logout.click();
	}
	
	public Boolean isQuickLaunchLegendDisplayed()
	{
		boolean b=false;
		try {
			b=quickLaunchLegend.isDisplayed();
			log.info("Quick Launch displayed");
		}catch(Exception e)
		{
			log.info("Quick Launch not displayed");
		}
		
		return b;
	}
	
	public int getQuickLaunchItemsCount()
	{
		int i=quickLaunchItems.size();
		log.info("Quick Launch item count:"+ i);
		return i;
	}
	
	public List<String> getQuickLaunchItems()
	{
		List<String> items=new ArrayList<String>();
		for(WebElement e:quickLaunchItems)
		{
			items.add(e.getText());
		}
	    log.info("Quick Launch items:"+ items);
		return items;
	}
}
