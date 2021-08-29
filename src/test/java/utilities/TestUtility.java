package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import testbase.TestBase;

public class TestUtility extends TestBase{

	
	public static void scrollToElement(WebElement e)
	{
		//JavascriptExecutor je= (JavascriptExecutor) dr;
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true)", e);
		((JavascriptExecutor) dr).executeScript("window.scrollBy(0,-200)", "");
	}
	
	public static void clickOnElementJS(WebElement e)
	{
		//JavascriptExecutor je= (JavascriptExecutor) dr;
		((JavascriptExecutor) dr).executeScript("arguments[0].click();", e);
	} 
	
	public static void captureScreen() throws IOException
	{
		//TakesScreenshot tc=(TakesScreenshot) dr;
		File file=((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File(getDate()+"_image.jpg"));
	}
	
	private static String getDate()
	{
		Date dt=new Date();
		SimpleDateFormat sft=new SimpleDateFormat("YYYY_MMM_dd_HH_mm_ss_S");
		String s=sft.format(dt);
		return s;
	}
	
	public static void attachScreenshotToReport()
	{
		TakesScreenshot c=(TakesScreenshot) dr;
		String file = c.getScreenshotAs(OutputType.BASE64);
		String st= "<img src=\"data:image/png;base64, " + file + "\" height=\"600\" width=\"800\" />";
		Reporter.log(st);
	}
}
