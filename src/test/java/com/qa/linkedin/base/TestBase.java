package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	private Logger log = Logger.getLogger(TestBase.class);
	public static WebDriver driver = null;
	public WebDriverWait wait = null;
	public Properties prop = null;

	public String readPropertyValue(String key) throws IOException {

		log.info("Create object for properties");
		prop = new Properties();
		log.debug("Read the property value");
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
			log.info("Load all the properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	@BeforeSuite
	public void setup() throws IOException {

		log.debug("Lanching the browser and application");
		
		String browsername = readPropertyValue("browser");
		
		if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if (browsername.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		} else if (browsername.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		log.debug("Maximise the window");
		driver.manage().window().maximize();
		log.debug("add implicit wait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		log.debug("Open the application url");
		driver.get(readPropertyValue("applicationurl"));

	}

	@AfterSuite
	public void tearDown() {
		log.debug("Closing the browser");
		if (driver != null) {
			driver.quit();
		}

	}
}
