package web.pages.admin;

import org.openqa.selenium.WebDriver;

public class ManageActualSizePage extends BasePage{

	public ManageActualSizePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String enterName(String colorName) {
		enterData(name, colorName);
		return colorName;
	}
	
	public String enterActualSizePriority(String value) {
		enterData(priority, value);
		return value;
	}
	
	public void clickOnaddActualSizeButton() {
		clickElement(addButton);
	}
	
}
