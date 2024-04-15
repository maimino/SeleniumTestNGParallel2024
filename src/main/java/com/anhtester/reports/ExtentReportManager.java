package com.anhtester.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    //Ham getExtentReports() de khoi tao thong so dau ra cua report
    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport/ExtentReport.html");
        reporter.config().setReportName("Extent Report | Mai Vu");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Mai Vu");
        extentReports.setSystemInfo("Author", "Mai Vu");
        return extentReports;
    }

}
