package web.tests.admin;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;

import utils.listeners.TestListener;
import utils.report.ExtentService;
import utils.report.ExtentTestManager;
import utils.report.ReportLog;
import web.config.WebConfig;
import web.init.DriverFactory;
import web.objects.admin.CommonTestData;

public class BaseTest extends CommonTestData {

	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public DriverFactory driverFactory = new DriverFactory();
	public String URL = WebConfig.ADMIN_URL;

	@BeforeSuite(alwaysRun = true)
	public void suiteSetup() {
	}

	@AfterSuite(alwaysRun = true)
	public void suiteTeardown() {
	//	ExtentService.getExtent().setSystemInfo("========","==============");
		ExtentService.getExtent().setSystemInfo("Total TestCases",
				String.valueOf(TestListener.totalTestCases));
		ExtentService.getExtent().setSystemInfo("TotalCases Failed ",
				String.valueOf(TestListener.fcount));
		ExtentService.getExtent().setSystemInfo("Total Tests Passed ",
				String.valueOf(TestListener.pcount));
		ExtentService.getExtent().setSystemInfo("Total Tests Skipped ",
				String.valueOf(TestListener.totalTestCases - (TestListener.pcount + TestListener.fcount)));
		// String.valueOf((totalTestCases - failedtestcount) - scount));
		ExtentService.getExtent().flush();
		System.out.println(
				"================================================================================================");
		System.out.println(
				"================================================================================================");
		System.out.println("TOTAL TESTS: " + TestListener.totalTestCases);
		System.out.println("TOTAL PASSED TESTS: " + String.valueOf(TestListener.pcount));
		System.out.println("TOTAL FAILED TESTS: " + TestListener.fcount);
		System.out.println("TOTAL SKIPPED TESTS: " + String.valueOf(TestListener.totalTestCases - (TestListener.pcount + TestListener.fcount)));
		System.out.println(
				"=================================================================================================");
		System.out.println(
				"=================================================================================================");
	}

	@BeforeClass(alwaysRun = true)
	public void globalSetup() {
		ExtentTestManager.createParentTest(this.getClass().getSimpleName());
	}

	@AfterClass(alwaysRun = true)
	public void globalTeardown() {
		ExtentService.getExtent().flush();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				ReportLog.reportPass("Test Passed");
			} else if (result.getStatus() == ITestResult.FAILURE) {
				ExtentTestManager.getTest().fail(result.getThrowable());
				ExtentTestManager.getTest().fail(CaptureScreenshot());
			} else if (result.getStatus() == ITestResult.SKIP) {
				ReportLog.reportPass("Test Skipped");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	//	driver.get().quit();
	}

	public Media CaptureScreenshot() {
		String screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BASE64);
		return MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build();
	}
}
