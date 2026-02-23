package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import listener.ExtentTestListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CSVDataProvider;
import utils.ExtentManager;

public class LoginTest extends BaseTest {
    @Test(description = "Test login success")
    public void testLoginSuccess() throws InterruptedException {
//        init LoginPage object to create test case

        LoginPage loginPage = new LoginPage(driver, wait);

        ExtentTest currentTest = ExtentManager.getCurrentTest();
        loginPage.login("Admin","admin123");
        currentTest.log(Status.INFO, "Login with admin account");
        wait.until(ExpectedConditions.urlContains("dashboard"));
        currentTest.log(Status.INFO, "Wait for dashboard URL to load");
//        verify
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/dashboard"),
                "Login successful, dashboard URL verified."
        );
    }

    @Test(dataProvider = "csvData", dataProviderClass = CSVDataProvider.class, description = "test login with multiple data")
    public void testLoginData(String username, String password, String expectedResult) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        ExtentTest currentTest = ExtentManager.getCurrentTest();
        loginPage.login(username, password);

        if(expectedResult.equals("success")){
            wait.until(ExpectedConditions.urlContains("dashboard"));
            currentTest.log(Status.INFO, "Wait for dashboard URL to load");
//        verify
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("/dashboard"),
                    "Login successful, dashboard URL verified."
            );
        } else {
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("login"),
                    "Login failed, login URL verified."
            );
        }
    }
}
