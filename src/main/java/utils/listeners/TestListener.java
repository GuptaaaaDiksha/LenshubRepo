package utils.listeners;

import org.testng.ITestContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.report.ExtentService;
import utils.report.ExtentTestManager;

public class TestListener implements ITestListener {
	public static int totalTestCases = 0;
	public static int scount = 0;
	public static ArrayList<String> ssPaths = new ArrayList<String>();
	public static int fcount = 0;
	public static int pcount = 0;
	public static ArrayList<String> fids = new ArrayList<>();
	public static String failids = "";
	public static String totaltestIds = "";
	public static ArrayList<String> pids = new ArrayList<>();
	static String passidss = "";
	static String sids = "";
	static String skipids = "";

	

	//public static String screenshotFolderPath;
	public static String reportpath;
	public static String environment;

	@Override
	public synchronized void onStart(ITestContext context) {
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
//		ExtentService.getExtent().setSystemInfo("========","==============");
//		ExtentService.getExtent().setSystemInfo("Total TestCases",
//				String.valueOf(totalTestCases));
//		ExtentService.getExtent().setSystemInfo("TotalCases Failed ",
//				String.valueOf(fcount));
//		ExtentService.getExtent().setSystemInfo("Total Tests Passed ",
//				String.valueOf(pcount));
//		ExtentService.getExtent().setSystemInfo("Total Tests Skipped ",
//				String.valueOf(totalTestCases - (pcount + fcount)));
//		// String.valueOf((totalTestCases - failedtestcount) - scount));
//		ExtentService.getExtent().flush();
//		System.out.println(
//				"================================================================================================");
//		System.out.println(
//				"================================================================================================");
//		System.out.println("TOTAL TESTS: " + totalTestCases);
//		System.out.println("TOTAL PASSED TESTS: " + String.valueOf(pcount));
//		System.out.println("TOTAL FAILED TESTS: " + fcount);
//		System.out.println("TOTAL SKIPPED TESTS: " + String.valueOf(totalTestCases - (pcount + fcount)));
//		System.out.println(
//				"=================================================================================================");
//		System.out.println(
//				"=================================================================================================");

	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		failids = "";
		passidss = "";
		skipids = "";
		System.out.println((result.getMethod().getMethodName() + " started!"));
		String desc = result.getMethod().getDescription();
		String[] testsIds = desc.split(",");
		int count = testsIds.length;
		totalTestCases = totalTestCases + count;
		totaltestIds = totaltestIds + desc;
		// ExtentTest extentTest = extent
		// .createTest(result.getMethod().getMethodName(),
		// result.getMethod().getDescription())
		// .assignCategory(result.getMethod().getRealClass().getName());
		// test.set(extentTest);
		ExtentTestManager.getTest().info("Total Tests in this Method: " + desc);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		System.out.println("PASS COUNT=== " + pcount);
	}

	// public void filterids(ITestResult result) {
	// 	ArrayList<String> plist = new ArrayList<>();
	// 	ArrayList<String> flist = new ArrayList<>();
	// ArrayList<String> tlist = new ArrayList<>();
	// 	System.out.println("PASSS****==== " + passidss);
	// 	System.out.println("FAIL****==== " + failids);

	// 	String desc = result.getMethod().getDescription();
	// 	String[] x = desc.split(",");
	// 	for (String val : x) {
	// 		tlist.add(val);
	// 	}
	// 	if (passidss.contains(",")) {
	// 		String[] y = passidss.split(",");

	// 		for (String val : y) {
	// 			plist.add(val);
	// 		}

		
	// 	} else {
	// 		plist.add(passidss);
	// 	}

	// 	if (failids.contains(",")) {
	// 		String[] y = failids.split(",");
	// 		for (String val : y) {
	// 			flist.add(val);
	// 		}
			
	// 	} else {
		
	// 		flist.add(failids);
	// 	}
		
	// 	Set<String> pistt = new HashSet<String>(plist);
	// 	pcount = pcount+pistt.size();
	// 	Set<String> fistt = new HashSet<String>(flist);
	// 	fcount = fcount +fistt.size();

	// }

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
	//	filterids(result);
		if((!(result.getThrowable() instanceof AssertionError)) ||(!(result.getThrowable().getMessage().contains("Total assertion failed")))){
			fcount = fcount+1;
		}
       
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
	//	filterids(result);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	public static void failcount(int count) {
		System.out.println("count======" + count);
		fcount = fcount + count;
		System.out.println("fcount==" + fcount);
	}

	public static void passcount(int count) {
		System.out.println("assert count= " + count);
		pcount = pcount + count;
		System.out.println("pcount==" + pcount);
	}

	public static void getAllScreenshots(String filename) {
		ssPaths.add(filename);
	}

public static void failIds( Set<String> ids) {
	for (String id : ids) {
		failids = failids +"," +id;
	}
}
	

	public static void passIds(Set<String> ids) {
		for (String id : ids) {
		passidss = passidss +"," +id;
	}
}
}