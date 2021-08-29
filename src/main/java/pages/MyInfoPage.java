package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.SyncClass;

public class MyInfoPage {

	private WebDriver dr;
	private SyncClass sync;
	private static Logger log=Logger.getLogger(LoginPage.class);
	public MyInfoPage(WebDriver driver)
	{
		log.info("MyInfo Page Constructor");
		this.dr=driver;
		sync=new SyncClass(dr);
		log.debug("Initilizing MyInfo Page");
		PageFactory.initElements(dr, this);//important to initialize the element of class
	}
	
	//WebElements
	@FindBy(css="a[id='menu_pim_viewMyDetails']")
	WebElement myInfoTab;
	
	@FindBy(css="div[id='content'] li>a")
	List<WebElement> sidemenu;
	
	public void gotoMyInfoPage()
	{
		log.info("Go to My info Page");
		myInfoTab.click();
		sync.waitForElement(sidemenu.get(0));
	}
	
	public int getMyInfoSideMenuItemsCount()
	{
		int i=sidemenu.size();
		log.info("Myinfo Side Menu item count:"+ i);
		return i;
	}
	
	public List<String> getQuickLaunchItems()
	{
		List<String> items=new ArrayList<String>();
		for(WebElement e:sidemenu)
		{
			items.add(e.getText());
		}
	    log.info("Myinfo Side Menu items:"+ items);
		return items;
	}
	
}
