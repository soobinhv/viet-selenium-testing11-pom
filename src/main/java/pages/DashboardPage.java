package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage{
    private static final By USER_DROPDOWN = By.cssSelector("li.oxd-userdropdown");
    private static final By LOGOUT_LINK = By.cssSelector("a[href='/web/index.php/auth/logout']");
    private static final By USER_ROLE = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']");
    private static final By ADMIN_ROLE = By.xpath("//div[@role='listbox']//*[text()='Admin']");
    private static final By ESS_ROLE = By.xpath("//div[@role='listbox']//*[text()='ESS']");
    private static final By ADMIN_TAB = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='Admin']");
    private static By sideMenuByText(String text){
        return By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text() ='"+text+"']");
    }

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

//    define test steps
    public void logout(){
//        wait until user dropdown is clickable
        wait.until(ExpectedConditions.elementToBeClickable(USER_DROPDOWN)).click();
//        wait until logout link is clickable, then click
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();
//        after click logout, wait until login page is loaded
        wait.until(ExpectedConditions.urlContains("/auth/login"));
    }

    public boolean areAllMenusDisplayed(){
        String[] menus = {
                "Admin",
                "PIM",
                "Leave",
                "Time",
                "Recruitment",
                "My Info",
                "Performance",
                "Dashboard",
                "Directory",
                "Maintenance",
                "Claim",
                "Buzz"
        };

        for(String menu: menus){
            By locator = sideMenuByText(menu);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if(!element.isDisplayed()){
                return false;
            }
        }
        return true;
    }

    public void selectUserRole(String role) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ADMIN_TAB)).click();
        wait.until(ExpectedConditions.elementToBeClickable(USER_ROLE)).click();
        if (role.equals("Admin")){
            wait.until(ExpectedConditions.elementToBeClickable(ADMIN_ROLE)).click();
        } else if (role.equals("ESS")){
            wait.until(ExpectedConditions.elementToBeClickable(ESS_ROLE)).click();
        }
        Thread.sleep(2000);
    }
}
