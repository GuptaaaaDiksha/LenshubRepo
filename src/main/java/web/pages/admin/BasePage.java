package web.pages.admin;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import web.pages.BaseMethods;

public class BasePage extends BaseMethods {

	protected By toastText = By.cssSelector("[class*='alert-dismissible'] p");
	protected By tabs = By.cssSelector("[class*='sidebar'] a p");
	protected By calenderField = By.cssSelector("react-datepicker__month-container");
	protected By datePickerTableBodyRow = By.xpath("//div[@class='react-datepicker__month']/div");
	protected By tableDataMonths = By.xpath("//div[contains(@aria-label,'Choose')]");
	protected By sideBarList = By.xpath("//li[contains(@class,'relative')]/p");
	protected By scrollToModule = By.xpath("//p[contains(text(),'Manage FAQs')]");
	protected By pageTitle = By.xpath("//div[contains(@class,'flex justify-between')]/h5");
	protected By addNewBtn = By.xpath("//*[contains(text(),'Add New') or contains(text(),'Add')]");
	protected By priority = By.id("priority");
	protected By name = By.id("name");
	protected By addPageTilte = By
			.xpath("//div[contains(@class,'relative') or contains(@class,'justify-between')]//descendant::h2");
	protected By addButton = By.xpath("//button[contains(@class,'adduser')]");
	protected By tableRows = By.xpath("//tbody/tr[contains(@class,'bg')]");
	protected By tableRowsText = By.xpath("//tbody/tr[contains(@class,'bg')]/td");
	protected By tableHeaders = By.xpath("//thead//span");
	protected By themeDropdown = By.xpath("//div[@class='dropdown relative bg-white']");
	protected By tableRowCellData = By.cssSelector("tbody>tr td");
	protected By alreadyExistText = By.xpath("//form//p");
	protected By searchBox = By.cssSelector("input[placeholder='Search']");
	protected By actionThreeDot = By.id("actionButton");
	protected By acionMenuOption = By.cssSelector("ul[data-popper-placement] li");
	protected By entriesCount = By.cssSelector("div.justify-end button");
	protected By imageIconInTable = By.cssSelector("td>img");
	protected By slideIcon = By.xpath("//label[@class='switch']//child::span");
	protected By statusOfSlideIcon = By.xpath("//label[@class='switch']/following-sibling::span");
	protected By imagePresence = By.cssSelector("#custom-button1 img");
	protected By imageDelete = By.cssSelector("img:hover");
	protected By tableNextPageButton = By.cssSelector(
			"div.main-screen div:not(div[class])+button,div.main-screen div:not(div[class])>ul li:last-child a");
	protected By deactivate = By
			.xpath("//h5[contains(text(),'Deactivate')]/following-sibling::div/button[contains(text(),'Yes')]");
	protected By activate = By
			.xpath("//h5[contains(text(),'Activate')]/following-sibling::div/button[contains(text(),'Yes')]");
	protected By activateDeactivatePopUp = By.xpath("(//div[contains(@class,'relative text-center')])[2]");
	protected By entriesPerPage100Button= By.xpath("//a[text()='100']");
	protected By actionColumn = By.xpath("//span[contains(text(),'Actions')]");
	protected By nextArrowBtn = By.xpath("//button[@disabled]");

	public BasePage(WebDriver driver) {
		super(driver);
	}

	public List<String> clickWithOutSearchActionsThreeDots(String expectedName, String columnSequence,
			String expectedOption) {
		waitForElementsVisibility(tableRows, SHORT_TIMEOUT, false);
		List<String> options = new ArrayList<>();
		boolean loopIterator = true;
		outer: while (loopIterator) {
			waitForElementsVisibility(tableRows, SHORT_TIMEOUT, false);
			List<WebElement> allRows = getWebElements(tableRows);
			for (WebElement rows : allRows) {
				WebElement nameRow = rows.findElement(By.xpath("td[" + columnSequence + "]"));
				String name = nameRow.getText().trim();
				if (name.equalsIgnoreCase(expectedName)) {
			//		scrollToElement(actionColumn);
					WebElement threeDots = nameRow.findElement(By.xpath("following::a"));
					threeDots.click();
					hardWait(2000);
					List<WebElement> threeDotsOptions = threeDots.findElements(By.xpath("following::ul//li"));
					for (int j = 0; j < threeDotsOptions.size(); j++) {
						String text = threeDotsOptions.get(j).getText().trim();
						if (text.equalsIgnoreCase(expectedOption)) {
							clickElement(threeDotsOptions.get(j));
							break outer;
						}
					}
					break;
				}
			}
			waitForElementsVisibility(tableNextPageButton, SHORT_TIMEOUT, false);
			boolean nexBtn = isElementEnabled(tableNextPageButton, false);
			if (nexBtn) {
				moveToElementClick(tableNextPageButton);
			}
		}
		return options;
	}

	public List<String> getMatchedRowData(String expectedName, String columnSequence, int columnCount) {
		List<WebElement> allRows = getWebElements(tableRows);
		List<String> options = new ArrayList<>();
		for (WebElement rows : allRows) {
			WebElement nameRow = rows.findElement(By.xpath("td[" + columnSequence + "]"));
			String name = nameRow.getText().trim();
			if (name.equalsIgnoreCase(expectedName)) {
				options.add(name);
				for (int i = 0; i < columnCount - 1; i++) {
					String value = nameRow.findElement(By.xpath("following::td[" + (i + 1) + "]")).getText().trim();
					options.add(value);
				}
				break;
			}
		}
		return options;
	}

	public List<String> getMatchedRowDataTable(String expectedName, String columnSequence, int columnCount) {
		List<String> options = new ArrayList<String>();
		WebElement nextPageElement;
		boolean loopIterator = true;
		outer: while (loopIterator) {
			List<WebElement> allRows = getWebElements(tableRows);
			for (WebElement rows : allRows) {
				WebElement nameRow = rows.findElement(By.xpath("td[" + columnSequence + "]"));
				String name = nameRow.getText().trim();
				if (name.equalsIgnoreCase(expectedName)) {
					options.add(name);
					for (int i = 0; i < columnCount - 1; i++) {
						String value = nameRow.findElement(By.xpath("following::td[" + (i + 1) + "]")).getText().trim();
						options.add(value);
					}
					break outer;
				}
			}
			moveToElementClick(tableNextPageButton);
			waitForElementVisibility(tableNextPageButton, TIMEOUT, false);
			nextPageElement = getElement(tableNextPageButton, false);
			loopIterator = getAllAttributes(nextPageElement).contains("disabled");
		}
		return options;
	}

	public void selectThemeDropdown(String optionName) {
		clickElement(themeDropdown);
		waitForElementVisibility(acionMenuOption, TIMEOUT, false);
		List<WebElement> options = getWebElements(acionMenuOption);
		for (WebElement option : options) {
			String text = getElementText(option);
			if (text.equalsIgnoreCase(optionName)) {
				clickElement(option);
				break;
			}
		}
	}

	public String enterDataInSearchField(String itemName) {
		String searchedDataFromTable = null;
		waitForElementVisibility(searchBox, TIMEOUT, false);
		clearElement(searchBox);
		enterDataAndPressEnter(searchBox, itemName);
		waitForElementsVisibility(tableRowCellData, TIMEOUT, false);
		List<WebElement> elements = getWebElements(tableRowCellData);
		for (WebElement cellElement : elements) {
			searchedDataFromTable = getElementText(cellElement);
			if (searchedDataFromTable.equalsIgnoreCase(itemName))
				break;
		}
		return searchedDataFromTable;
	}

	public void clickOnEntriesCount(String count) {
		waitForElementVisibility(entriesCount, TIMEOUT, false);
		List<WebElement> options = getWebElements(entriesCount);
		for (WebElement option : options) {
			String text = getElementText(option);
			if (text.equalsIgnoreCase(count)) {
				clickElement(option);
				break;
			}
		}
	}

	public void selectAction(String optionName) {
		waitForElementVisibility(actionColumn, TIMEOUT, false);
		scrollToElement(actionColumn);
		clickElement(actionThreeDot);
		waitForElementVisibility(acionMenuOption, TIMEOUT, false);
		List<WebElement> options = getWebElements(acionMenuOption);
		for (WebElement option : options) {
			String text = getElementText(option);
			if (text.equalsIgnoreCase(optionName)) {
				clickElement(option);
				break;
			}
		}
	}

	public void selectAction(String optionName, String withRespectTo) {
		clickElement(RelativeLocator.with(actionThreeDot)
				.toRightOf(By.xpath("//tbody//td//*[contains(text(),'" + withRespectTo + "')]")));
		waitForElementsPresence(acionMenuOption, TIMEOUT, false);
		List<WebElement> options = getWebElements(acionMenuOption);
		for (WebElement option : options) {
			String text = getElementText(option);
			if (text.equalsIgnoreCase(optionName)) {
				clickElement(option);
				break;
			}
		}
	}

	public double amountTextToDouble(String actualAmount, String currency) {
		String amount = actualAmount.replaceAll(",", "");
		if (actualAmount == "")
			amount = "0";
		double price = 0;

		if (amount.contains(currency) && amount.contains("/")) {
			amount = amount.substring(amount.indexOf("₹") + 1, amount.indexOf("/"));
		}
		if (currency != "" && amount.contains(currency)) {
			amount = amount.substring(actualAmount.indexOf(currency) + 1);
		}
		if (amount.contains("/")) {
			amount = amount.substring(0, amount.indexOf("/"));
		}
		price = Double.parseDouble(amount);
		amount = String.format("%.2f", price);
		price = Double.parseDouble(amount);
		return price;
	}

	public void waitForCompletePageLoad() {
		waitForPageToLoad();
	}

	public boolean deleteImageFromEditPage() {
		boolean result = true;
		hoverOvertheElement(imagePresence);
		moveToElementClick(imageDelete);
		try {
			waitForElementAbsence(imageDelete, TIMEOUT, false);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public String getImageSourceValueFromEditPage() {
		waitForElementsPresence(imagePresence, TIMEOUT, false);
		List<WebElement> images = getWebElements(imagePresence);
		if (images.size() > 0)
			return getElementAttributeValue(imagePresence, "src");
		else
			return null;

	}

	public String getPopUpText() {
		hardWait(1000);
		String[] getPopUpText = getElementText(activateDeactivatePopUp).split("\n");
		String popUpText = String.join(" ", getPopUpText).replace("?", "").trim();
		return popUpText;

	}

	public String getImageSourceValueFromTable() {
		waitForElementsPresence(imageIconInTable, TIMEOUT, false);
		List<WebElement> images = getWebElements(imageIconInTable);
		if (images.size() > 0)
			return getElementAttributeValue(imageIconInTable, "src");
		else
			return null;
	}

	public void clickOnDeactivateYesButton() {
		waitForElementsVisibility(deactivate, SHORT_TIMEOUT, false);
		clickElement(deactivate);
	}

	public void clickOnActivateYesButton() {
		waitForElementsVisibility(activate, SHORT_TIMEOUT, false);
		clickElement(activate);
	}

	public String getStatusOfSlideIcon() {
		return getElementText(statusOfSlideIcon);
	}
	
	public void selectDate(String date) {
		waitForElementsVisibility(tableDataMonths, SHORT_TIMEOUT, false);
		List<WebElement> list = getElements(tableDataMonths, TIMEOUT, false);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(date)) {
				hardWait(1000);
				list.get(i).click();
				break;
			}
		}
	}
	public static boolean dateComparison(String date1, String startDate, String endDate) throws Exception {
		boolean flag = false;
		Date actualDate, newStartDate, newEndDate;
		actualDate = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
		newStartDate = new Date();
		if (startDate != null && !startDate.isEmpty()) {
			newStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate); }
		if (endDate != null && !endDate.isEmpty()) {
			newEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate); }
		else  {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			newEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(dtf.format(localDate));  }
		if ((actualDate.compareTo(newStartDate) >= 0) && (actualDate.compareTo(newEndDate) <= 0)) 
			flag = true;
		return flag;
	}
	

	
	public double getDoubleValueFromString(String amountToBeParsedAsDouble)
	{
		return Double.parseDouble(amountToBeParsedAsDouble.replaceAll("[^0-9.]", ""));
	}
	
	
	
	public void clickOnEntriesPerPage100Button() {
		clickElement(entriesPerPage100Button);
	}
}
