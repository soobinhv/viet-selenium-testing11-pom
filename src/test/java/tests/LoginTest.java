package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Allure;
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
        Allure.step("Login with Admin account", () -> {
            loginPage.login("Admin","admin123");
        });
        Allure.step("Wait for URL contain dashboard", () -> {
            wait.until(ExpectedConditions.urlContains("dashboard"));
        });
//        verify
        String currentUrl = driver.getCurrentUrl();
        Allure.step("Verify URL", () -> {
            Assert.assertTrue(
                    currentUrl.contains("/dashboard"),
                    "Login successful, dashboard URL verified."
            );
        });
    }

    @Test(dataProvider = "csvData", dataProviderClass = CSVDataProvider.class, description = "test login with multiple data")
    public void testLoginData(String username, String password, String expectedResult) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);
        if(expectedResult.equals("success")){
            wait.until(ExpectedConditions.urlContains("dashboard"));
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
