package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {

	public static WebDriver dr;
	public static Properties prop;
	public static String browser;
	public static Logger log=Logger.getLogger(TestBase.class);
	public static WebDriver getInstance() throws IOException {

		PropertyConfigurator.configure("./src/main/java/log4j.properties");
		 
	        //Log in console in and log file
	    log.debug("Log4j appender configuration is successful !!");
		String config = "./src/main/java/config/config.properties";

		FileInputStream file = new FileInputStream(new File(config));
		log.info("Reading configuration file");
		prop = new Properties();
		log.info("loading file to Properties");
		prop.load(file);
		browser = prop.getProperty("browser");
		log.info("Reading browser file");
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("start-maximized");
			// opt.addArguments("start-fullscreen");
			opt.addArguments("disable-popup-blocking");
			// opt.setExperimentalOption("excludeSwitches",
			// Arrays.asList("disable-popup-blocking"));
			opt.addArguments("disable-security-errors");
			opt.setCapability("platformName", "Windows");
			opt.setCapability("platformVersion", "10");
			opt.setCapability("resolution", "1280x1024");
			// opt.setCapability(opt., "1280x1024");

			// opt.setCapability(, null);
			String key = "webdriver.chrome.driver";
			String value = prop.getProperty("chromedriver");//"./drivers/chromedriver.exe";
			System.setProperty(key, value);
			log.info("starting chrome");
			dr = new ChromeDriver(opt);
		} else if(browser.equalsIgnoreCase("firefox"))
		{
			String key = "webdriver.gecko.driver";
			String value = prop.getProperty("geckodriver");//"./drivers/chromedriver.exe";
			System.setProperty(key, value);
			log.info("starting firefox");
			dr = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("edge"))
		{
			String key = "webdriver.edge.driver";
			String value = prop.getProperty("edgedriver");//"./drivers/chromedriver.exe";
			System.setProperty(key, value);
			log.info("starting edge");
			dr = new EdgeDriver();
		} else if(browser.equalsIgnoreCase("ie"))
		{
			String key = "webdriver.ie.driver";
			String value = prop.getProperty("iedriver");//"./drivers/chromedriver.exe";
			System.setProperty(key, value);
			log.info("starting IE");
			dr = new InternetExplorerDriver();
		}
		else 
		{
			log.info("Throwing null for browser");
			Throwable thr=new Throwable();
			thr.initCause(null);
		}
	    dr.manage().window().maximize();
		// dr.manage().window().fullscreen();
	    log.info("opening test url");
	    dr.get(prop.getProperty("url"));
		return dr;
	}

}
