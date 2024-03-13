package web.steps.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.report.ReportLog;
import web.pages.admin.BasePage;

public class BaseStep extends BasePage {

	public BaseStep(WebDriver driver) {
		super(driver);
	}

	public String verifyToastMessage() {
		return getElementText(toastText);
	}

	public void waitForWholePageToLoad() {
		waitForCompletePageLoad();
	}

	public void sideBarModuleList(String moduleName) {
		waitForElementVisibility(sideBarList, TIMEOUT, false);
		List<WebElement> list = getElements(sideBarList, TIMEOUT, false);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equalsIgnoreCase(moduleName)) {
				hardWait(2000);
				scrollToElement(sideBarList);
				list.get(i).click();
				break;
			}
		}
	}

	public void sideBarSubModuleList(String moduleName) {
		List<WebElement> list = getElements(sideBarList, TIMEOUT, false);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equalsIgnoreCase(moduleName)) {
				hardWait(2000);
				clickElementJavaScript(list.get(i))	; 
				break;
			}
		}
	}
	

	public String getPagetitle() {
		String title = getElementText(pageTitle);
		return title;
		
	}
	
	public void clickOnAddNewButton() {
		waitForElementVisibility(addNewBtn, SHORT_TIMEOUT, false);
		clickElement(addNewBtn);
	}
	
	public String getAddPagetitle() {
		String title = getElementText(addPageTilte);
		return title;	
	}

	public String getAddToastMsg() {
		String title = getElementText(toastText);
		return title;
	}
	
	public String getActivateDeactivatePopUpText() {
		String popUpText = getPopUpText();
		return popUpText;
	}

	public void waitForElementVisibilityTable() {
		waitForElementVisibility(tableRows, TIMEOUT, false);
	}

	public List<String> getMatchRowData(String expectedName, String columnSequence, int columnCount) {
		return getMatchedRowData(expectedName, columnSequence, columnCount);
	}
}
