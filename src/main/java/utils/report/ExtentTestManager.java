package utils.report;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	public static ThreadLocal<ExtentTest> parentThread = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> childThread = new ThreadLocal<ExtentTest>();

	private static ExtentTest parentTest;
	private static ExtentTest childTest;

	public static synchronized ThreadLocal<ExtentTest> createParentTest(String testName) {
		parentTest = ExtentService.getExtent().createTest(testName);
		parentThread.set(parentTest);
		return parentThread;
	}

	public static synchronized ThreadLocal<ExtentTest> createTest(String testName) {
		childTest = getParentTest().createNode(testName);
		childThread.set(childTest);
		return childThread;
	}

	public static synchronized ExtentTest getParentTest() {
		ExtentTest name =  parentThread.get();
		return name;//parentThread.get();
	}

	public static synchronized ExtentTest getTest() {
		return childThread.get();
	}
}