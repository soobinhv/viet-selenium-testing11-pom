package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;

import java.net.URL;
import java.nio.file.Path;

@Epic("Profile")
@Feature("Avatar Upload")
public class AvatarUploadTest extends BaseTest{
    private String getAvatarPath() throws Exception{
        URL url = getClass().getResource("/avatar/testing11.jpg");
        Assert.assertNotNull(url, "Avatar image not found in resources.");
        return Path.of(url.toURI()).toAbsolutePath().toString();
    }

    @Test
    public void testUploadAvatar() throws Exception{
        String avatarPath = getAvatarPath();

        Allure.step("Login with Admin account", () -> {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.login("Admin","admin123");
            wait.until(ExpectedConditions.urlContains("dashboard"));
        } );

        Allure.step("Negative to My Info Page", () -> {
            DashboardPage dashboardPage = new DashboardPage(driver, wait);
            dashboardPage.clickMenuMyInfo();
        });

        Allure.step("Upload Avatar", () -> {
            MyInfoPage myInfoPage = new MyInfoPage(driver, wait);
            myInfoPage.uploadAvatar(avatarPath);
        });

        Allure.step("Verify My Info Page", () -> {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("/pim/viewPhotograph"),
                    "Avatar uploaded, My Info URL verified."
            );
        });




    }
}
