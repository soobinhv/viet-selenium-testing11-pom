package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;

@Epic("Dashboard")
@Feature("Admin Page")
public class AdminTest extends BaseTest {
    @Test(description = "Filter admin account")
    public void testFilterAdmin() throws Exception {
        Allure.step("Login with Admin account", () -> {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.login("Admin","admin123");
        });

        Allure.step("Click Admin menu", () -> {
            DashboardPage dashboardPage = new DashboardPage(driver, wait);
            dashboardPage.clickMenuAdmin();
        });
        AdminPage adminPage = new AdminPage(driver, wait);
        Allure.step("Fill Admin filter", adminPage::enterAdminName);
        Allure.step("Click User role dropdown", adminPage::clickUserRoleDropdown);
        Allure.step("Select Admin role", adminPage::selectAdminRole);
        Allure.step("Click Search button", adminPage::clickSearchBtn);
        Allure.step("Verify data is displayed", () -> {
            Assert.assertTrue(
                    adminPage.hasData(),
                    "Admin filter applied, data is displayed."
            );
        });

    }
}
