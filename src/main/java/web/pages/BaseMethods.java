package web.pages;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseMethods {

	public final int TIMEOUT = 20;
	public final int SHORT_TIMEOUT = 2;

	private WebDriver _driver;

	public BaseMethods(WebDriver driver) {
		_driver = driver;
	}

	public void navigateToURL(String url) {
		_driver.navigate().to(url);
	}

	public String getTitle() {
		return _driver.getTitle().trim();
	}

	public void hardWait(long milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void urlToBe(String url, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.urlToBe(url));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void urlContains(String url, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.urlContains(url));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementValue(By locator, String value, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.attributeToBe(locator, "value", value));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementPresence(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementAbsence(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(elem -> elem.findElements(locator).size() == 0);
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementVisibility(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementInVisibility(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementVisibility(WebElement element, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementsVisibility(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementClickable(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementClickable(WebElement element, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public void waitForElementsPresence(By locator, int timeout, boolean ignoreException) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(_driver, Duration.ofSeconds(timeout));
			webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			if (!ignoreException) {
				Assert.fail(e.getMessage());
			}
		}
	}

	public List<WebElement> getElements(By locator, int timeout, boolean ignoreException) {
		waitForElementsPresence(locator, timeout, ignoreException);
		return _driver.findElements(locator);
	}

	public String getElementText(By locator) {
		waitForElementVisibility(locator, TIMEOUT, false);
		return _driver.findElement(locator).getText().trim();
	}

	public String getElementText(WebElement element) {
		waitForElementVisibility(element, TIMEOUT, false);
		return element.getText().trim();
	}

	public void enterData(By locator, String value) {
		waitForElementVisibility(locator, TIMEOUT, false);
		waitForElementClickable(locator, TIMEOUT, false);
		clearElement(locator);
		hardWait(1000);
		_driver.findElement(locator).sendKeys(value);
	}

	public void enterDataAndPressEnter(By locator, String value) {
		waitForElementVisibility(locator, TIMEOUT, false);
		waitForElementClickable(locator, TIMEOUT, false);
		_driver.findElement(locator).clear();
		_driver.findElement(locator).sendKeys(value);
		_driver.findElement(locator).sendKeys(Keys.ENTER);
	}

	public void enterData(WebElement element, String value) {
		waitForElementVisibility(element, TIMEOUT, false);
		waitForElementClickable(element, TIMEOUT, false);
		element.sendKeys(value);
	}

	public void enterDataWithoutClearingField(By locator, String value) {
		waitForElementClickable(locator, TIMEOUT, false);
		_driver.findElement(locator).sendKeys(value);
	}
	
	public void enterDataWithoutCheckingClickability(By locator, String value) {
		_driver.findElement(locator).sendKeys(value);
	}

	public WebElement getElement(By locator, boolean ignoreException) {
		waitForElementPresence(locator, TIMEOUT, ignoreException);
		return _driver.findElement(locator);
	}

	public String getElementValue(By locator) {
		waitForElementPresence(locator, TIMEOUT, false);
		String value = _driver.findElement(locator).getAttribute("value");
		if (value != null)
			return value.trim();
		else
			return "";
	}

	public String getElementAttributeValue(By locator, String attribute) {
		waitForElementPresence(locator, TIMEOUT, false);
		String value = _driver.findElement(locator).getAttribute(attribute);
		if (value != null)
			return value.trim();
		else
			return "";
	}

	public List<String> getAllAttributes(WebElement element) {
		JavascriptExecutor execute = (JavascriptExecutor) _driver;
		String script = "return arguments[0].getAttributeNames().toLocaleString();";
		String objList = (String) execute.executeScript(script, element);
		List<String> attributes = Arrays.asList(objList.split(","));
		return attributes;
	}

	public void clearElement(By locator) {
		while (!getElementValue(locator).equals("")) {
			_driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			_driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
		}
	}

	public void clickElement(By locator) {
		waitForElementVisibility(locator, TIMEOUT, false);
		waitForElementClickable(locator, TIMEOUT, false);
		try {
			_driver.findElement(locator).click();
		} catch (ElementClickInterceptedException e) {
			clickElementJavaScript(getElement(locator, false));
		}
	}

	public void clickElement(WebElement element) {
		waitForElementClickable(element, TIMEOUT, false);
		element.click();
	}

	public void moveToElementClick(By locator) {
		waitForElementClickable(locator, TIMEOUT, false);
		Actions actions = new Actions(_driver);
		actions.moveToElement(_driver.findElement(locator)).click().perform();
	}

	public void moveToElementClick(WebElement element) {
		waitForElementClickable(element, TIMEOUT, false);
		Actions actions = new Actions(_driver);
		actions.moveToElement(element).click().perform();
	}

	public void clickElementJavaScript(By locator) {
		JavascriptExecutor executor = (JavascriptExecutor) _driver;
		executor.executeScript("arguments[0].click();", getElement(locator, false));
	}

	public void clickElementJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) _driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void enterValueByJavaScript(WebElement element,String text) {
		JavascriptExecutor executor = (JavascriptExecutor) _driver;
		executor.executeScript("arguments[0].value = '"+text+"';", element);
	}

	public boolean isElementPresent(By locator, boolean ignoreException) {
		waitForElementPresence(locator, TIMEOUT, ignoreException);
		return (_driver.findElements(locator).size() == 1);
	}

	public boolean isElementPresentShort(By locator, boolean ignoreException) {
		waitForElementPresence(locator, SHORT_TIMEOUT, ignoreException);
		return (_driver.findElements(locator).size() == 1);
	}

	public boolean isElementEnabled(By locator, boolean ignoreException) {
		return _driver.findElement(locator).isEnabled();

	}

	public boolean isElementVisible(By locator, boolean ignoreException) {
		waitForElementVisibility(locator, TIMEOUT, ignoreException);
		try {
			return _driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementVisibleShort(By locator, boolean ignoreException) {
		waitForElementVisibility(locator, SHORT_TIMEOUT, ignoreException);
		try {
			return _driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementVisible(WebElement element, boolean ignoreException) {
		waitForElementVisibility(element, TIMEOUT, ignoreException);
		return element.isDisplayed();
	}

	public boolean isElementVisibleShort(WebElement element, boolean ignoreException) {
		waitForElementVisibility(element, SHORT_TIMEOUT, ignoreException);
		return element.isDisplayed();
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollToElement(String element) {
		((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollToElement(By locator) {
		((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(locator));
	}

	public void scrollDownn() {
		((JavascriptExecutor) _driver).executeScript("window.scrollBy(0,200)");
	}

	public void scrollDown() {
		((JavascriptExecutor) _driver).executeScript("window.scrollBy(0,700)");
	}

	public List<WebElement> getWebElements(By locator) {
		return _driver.findElements(locator);
	}

	public static int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000000);
	}

	public static int randommNumber() {
		Random random = new Random();
		return random.nextInt(20);
	}

	public static int randommNumber(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}

	public void hoverOvertheElement(By locator) {
		waitForElementClickable(locator, TIMEOUT, false);
		Actions actions = new Actions(_driver);
		actions.moveToElement(_driver.findElement(locator)).build().perform();
	}

	public void hoverOvertheElement(WebElement webElement) {
		waitForElementClickable(webElement, TIMEOUT, false);
		Actions actions = new Actions(_driver);
		actions.moveToElement(webElement).build().perform();
	}

	public String randomStringGenerator(int len) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public void enterTextByJavaScript(WebElement element, String text) {
		JavascriptExecutor executor = (JavascriptExecutor) _driver;
		executor.executeScript("arguments[0].innerText = '" + text + "';", element);
	}

	public void enterTextByJavaScript(By locator, String text) {
		JavascriptExecutor executor = (JavascriptExecutor) _driver;
		executor.executeScript("arguments[0].innerText = '" + text + "';", _driver.findElement(locator));
	}

	public void waitForPageToLoad() {
		JavascriptExecutor j = (JavascriptExecutor) _driver;
		hardWait(1000);
		if (j.executeScript("return document.readyState").toString().equals("complete")) {
		} else {
			// iterate 60 times after every one second to verify if in ready state
			for (int i = 0; i < 60; i++) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					System.out.println("Page has not loaded yet ");
				}
				// again check page state
				if (j.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		}
	}
	
	public void refreshPage() {
		_driver.navigate().refresh();
	}
	
	
	public void switchTabs(String tab) {
			Set<String> tabs = _driver.getWindowHandles();
			for (String aTab : tabs) {
				_driver.switchTo().window(aTab);
				if (_driver.getTitle().contains(tab)) {
					break;
				}
			}
	}
  
	public void openNewTab() {
			_driver.switchTo().newWindow(WindowType.TAB);
	}
	
	public void switchWindows(Integer index) {
			Set<String> allWindows = _driver.getWindowHandles();
			String[] windows = allWindows.toArray(new String[allWindows.size()]);
			_driver.switchTo().window(windows[index]);
	}
}
