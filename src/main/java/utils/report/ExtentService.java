package utils.report;

import java.io.File;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.GenericMethods;

public class ExtentService {
	private static ExtentReports extent;

	public static ExtentReports getExtent() {
		if (extent == null) {
			extent = new ExtentReports();
			String reportDir = Paths.get(GenericMethods.getProjectRootDirectory(), "reports").toString();
			File dir = new File(reportDir);
			if (!dir.exists())
				dir.mkdir();
			String path = Paths.get(reportDir, "report.html").toString();
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("MyTyles");
			reporter.config().setDocumentTitle("Test Results");
			extent.attachReporter(reporter);
		}
		return extent;
	}
}
