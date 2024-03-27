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
            validateConfiguration();
            driver = setDriver(browser);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return driver;
    }

    private void validateConfiguration() {
        if (!isValidBoolean(isheadless)) {
            Assert.fail("Invalid value for isheadless: " + isheadless);
        }
        if (!isValidBrowser(browser)) {
            Assert.fail("Invalid browser: " + browser);
        }
    }

    private boolean isValidBoolean(String value) {
        return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
    }

    private boolean isValidBrowser(String value) {
        return "chrome".equalsIgnoreCase(value) || "ie".equalsIgnoreCase(value) || "firefox".equalsIgnoreCase(value);
    }

    private WebDriver setDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return initChromeDriver(isheadless);
            case "ie":
                return initIEDriver(isheadless);
            case "firefox":
                return initFirefoxDriver(isheadless);
            default:
                Assert.fail("Invalid browser: " + browser);
                return initChromeDriver(isheadless);
        }
    }

    private WebDriver initChromeDriver(String isheadless) {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if ("false".equalsIgnoreCase(isheadless)) {
            options.addArguments("--start-maximized");
        } else {
            options.addArguments("--window-size=1920,1080");
        }
        return new ChromeDriver(options);
    }

    private WebDriver initIEDriver(String isheadless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if ("false".equalsIgnoreCase(isheadless)) {
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
        } else {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }
        return new EdgeDriver(options);
    }

    private WebDriver initFirefoxDriver(String isheadless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if ("false".equalsIgnoreCase(isheadless)) {
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
        } else {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }
        return new FirefoxDriver(options);
    }

    private WebDriver initMobileChromeDriver(String isheadless) {
        WebDriverManager.chromedriver().setup();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 12 Pro");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        if ("false".equalsIgnoreCase(isheadless)) {
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
        } else {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
        }
        return new ChromeDriver(options);
    }
}
