package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SyncClass {

	private WebDriver dr;
	public SyncClass(WebDriver driver)
	{
		this.dr=driver;
	}
	
	public void waitForElement(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(dr,15);
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	public void waitForElementClickable(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(dr,15);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		
	}
	
}
