package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest{
    @Test(description="Test logout")
    public void testLogout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin","admin123");

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.logout();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/auth/login"),
                "Logout successful, login URL verified."
        );
    }

    @Test(description ="Verify dashboard menus are displayed")
    public void testMenusDisplayed() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin","admin123");

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        boolean allMenusDisplayed = dashboardPage.areAllMenusDisplayed();

        Assert.assertTrue(
                allMenusDisplayed,
                "All dashboard menus are displayed."
        );
    }

    @Test
    public void selectAdminRole() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin","admin123");

        DashboardPage dashboardPage = new DashboardPage(driver, wait);

        dashboardPage.selectUserRole("Admin");
    }
}
