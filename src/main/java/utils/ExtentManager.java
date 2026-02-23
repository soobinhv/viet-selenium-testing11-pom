package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    public static void setCurrentTest(ExtentTest test){
        currentTest.set(test);
    }
//    Each thread will have its own instance of ExtentTest
    public static ExtentTest getCurrentTest(){
        return currentTest.get();
    }

    public static void removeCurrentTest(){
        currentTest.remove();
    }

    public static synchronized  ExtentReports getExtentReports(){

        if(extent!=null){
            return extent;
        }
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Selenium POM - TestNG");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        return extent;
    }
}
