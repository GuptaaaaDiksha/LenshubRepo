package web.steps.admin;

import org.openqa.selenium.WebDriver;
import web.pages.admin.ManageActualSizePage;

public class ManageActualSizeStep extends ManageActualSizePage{

	public ManageActualSizeStep(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String enterActualSizeName(String name) {
		int randomNumber = ManageActualSizePage.randomNumber();
		String actualName = name.replaceAll("[0-9]", "");
		String createActualSizeName = actualName + randomNumber;
		String actualSizeName = enterName(createActualSizeName);
		return actualSizeName;
	}
	
	public String enterPriority(String number) {
		int randomNumber = ManageActualSizePage.randommNumber();
		String actualNumber = number.replaceAll("[0-9]", "");
		String createPriority = actualNumber + randomNumber;
		String priority = enterActualSizePriority(createPriority);
		return priority;
	}
	
}
