package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;
import com.mystore.actiondriver.Action;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;

	@BeforeTest
	public void loadConfig() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");

			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void launchApp() {
		WebDriverManager.chromedriver().setup();
		String browserName = prop.getProperty("browser");

		if (browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			driver = new InternetExplorerDriver();
		}

		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);
		Action.launchUrl(driver, prop.getProperty("url"));
	}
}