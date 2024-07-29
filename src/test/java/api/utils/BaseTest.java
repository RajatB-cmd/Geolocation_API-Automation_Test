package api.utils;

import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;

public class BaseTest {
	
	protected ExtentReports extent;
	
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://www.googleapis.com";
        ExtentSparkReporter spark = new ExtentSparkReporter("GeolocationApiTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

}
