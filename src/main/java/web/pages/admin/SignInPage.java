package web.pages.admin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import web.config.WebConfig;

public class SignInPage extends BasePage {

	protected By emailInput = By.xpath("//input[@type='email']");
	protected By passwordInput = By.xpath("//input[@type='password']");
	protected By loginButton = By.xpath("//button[@type='submit']");
	protected By password = By.xpath("//button[@type='submit']");
	protected By loginText = By.xpath("//h2");

	public SignInPage(WebDriver driver) {
		super(driver);
	}

	public void loginUser() {
		enterData(emailInput, WebConfig.EMAIL);
		enterData(passwordInput, WebConfig.PASSWORD);
		clickElement(loginButton);
	
	}
	
	
	public String getPageTitle() {
		return getElementText(loginText);
	}
	
	
	
	
	
}

