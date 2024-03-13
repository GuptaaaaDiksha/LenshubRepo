package utils.report;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportLog {

	public static void reportInfo(String message) {
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().info(message);
		else if (ExtentTestManager.getParentTest() != null)
			ExtentTestManager.getParentTest().info(message);
	}

	public static void reportPass(String message) {
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().pass(message);
		else if (ExtentTestManager.getParentTest() != null)
			ExtentTestManager.getParentTest().pass(message);
	}

	public static void reportFail(String message) {
		if (ExtentTestManager.getTest() != null){
			ExtentTestManager.getTest().fail(message);
	}
		else if (ExtentTestManager.getParentTest() != null)
		{
			ExtentTestManager.getParentTest().fail(message);
}
	}

	public static void reportJsonCodeBlock(String json) {
		if (ExtentTestManager.getTest() != null)
			ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
		else if (ExtentTestManager.getParentTest() != null)
			ExtentTestManager.getParentTest().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
	}
}
