package web.tests.admin;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.SoftAssert;
import utils.report.ExtentTestManager;
import web.constants.Constants;
import web.helper.admin.JsonHelper;
import web.steps.admin.BaseStep;
import web.steps.admin.ManageActualSizeStep;
import web.steps.admin.SignInPageStep;

public class ManageActualSize extends BaseTest{
	
	public BaseStep baseStep;
	public ManageActualSizeStep manageActualSizeStep;
	SoftAssert softAssert;
	public SignInPageStep signInPageStep;

	public static final String FOLDER_NAME = "testdata";
	public static final String ADMIN_TYPE = Constants.USER_ADMIN;
	public static final String FILE_NAME = "ManageActualSize";
	
	//public String loginPageTitle = JsonHelper.getTestDataString(FOLDER_NAME, ADMIN_TYPE, FILE_NAME, "LoginTitle");

	
	@BeforeMethod(alwaysRun = true)
	public void setup(Method m) {
		driver.set(driverFactory.initialize());
		baseStep = new BaseStep(driver.get());
		signInPageStep = new SignInPageStep(driver.get());
		manageActualSizeStep = new ManageActualSizeStep(driver.get());
		ExtentTestManager.createTest(m.getName());
		softAssert = new SoftAssert();
	}
	
	@Test(priority = 1, description = "TC_01", groups ={"smoke"})
	/*Test Case Description (Manage Attribute - Manage Actual Size - TC_060,TC_065,TC_069 )*/
	public void addManageActualSize() {
		signInPageStep.navigateToURL(URL);
		signInPageStep.loginUser();
		String pageTitle = signInPageStep.getPageTitle();

		softAssert.assertEquals(pageTitle, "Login","TC_013,TC_014: Page Title not matching.");
		softAssert.assertAll();
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
	