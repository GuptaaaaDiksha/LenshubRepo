package stepsPackage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When





class LoginSteps {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("user is on login page")
	public void user_is_on_login_page() {
		WebUI.openBrowser("")
		WebUI.navigateToUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {
		WebUI.setText(findTestObject('Object Repository/Page_Practice Page/Page_OrangeHRM/input_Username_username'),'Admin')
		WebUI.setText(findTestObject('Object Repository/Page_Practice Page/Page_OrangeHRM/input_Password_password'),'admin123')
	}


	@And("clicks on login button")
	public void clicks_on_login_button() {
		WebUI.click(findTestObject('Object Repository/Page_Practice Page/Page_OrangeHRM/button_Login'))
	}


	@Then("user is navigated to home page")
	public void user_is_navigated_to_home_page() {
		WebUI.closeBrowser()
	}
}