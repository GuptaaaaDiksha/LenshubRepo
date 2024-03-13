package web.init;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import web.config.WebConfig;

public class DriverFactory {

	private WebDriver driver;
	private String isheadless = WebConfig.HEADLESS;
	private String browser = WebConfig.BROWSER;

	public WebDriver initialize() {
		try {
				driver = setDriver(browser);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		return driver;
	}

	private WebDriver setDriver(String browser) {

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = initChromeDriver(isheadless);
			break;
		case "ie":
			driver = initIEDriver(isheadless);
			break;
		case "firefox":
			driver = initFirefoxDriver(isheadless);
			break;
		default:
			driver = initChromeDriver(isheadless);
		}
		return driver;
	}

	private WebDriver initChromeDriver(String isheadless) {
		 WebDriverManager.chromedriver().clearDriverCache().setup();
	     WebDriverManager.chromedriver().clearResolutionCache().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		if (isheadless.toLowerCase().equals("false")) { 
			options.addArguments("--start-maximized");
		} else {
			//options.addArguments("--headless");	
			options.addArguments("--window-size=1920,1080");
		}
		driver = new ChromeDriver(options);
		return driver;
	}

	private WebDriver initIEDriver(String isheadless) {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		if (isheadless.toLowerCase().equals("false")) {
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
		} else {
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
		}
		driver = new EdgeDriver(options);
		return driver;
	}

	private WebDriver initFirefoxDriver(String isheadless) {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		if (isheadless.toLowerCase().equals("false")) {
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
		} else {
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
		}
		driver = new FirefoxDriver(options);
		return driver;
	}

	private WebDriver initMobileChromeDriver(String isheadless) {
		WebDriverManager.chromedriver().setup();
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "iPhone 12 Pro");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);

		if (isheadless.toLowerCase().equals("false")) {
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
		} else {
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
		}
		driver = new ChromeDriver(options);
		return driver;
	}
}
