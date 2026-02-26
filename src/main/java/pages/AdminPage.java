package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminPage extends  BasePage {
    private static final By userInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
    private static final By userRoleDropdown = By.xpath("//label[text()='User Role']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']");
    private static final By adminRoleOption = By.xpath("//div[@role='listbox']//*[text()='Admin']");
    private static final By ESSRoleOption = By.xpath("//div[@role='listbox']//*[text()='ESS']");
    private static final By searchBtn = By.xpath("//button[text()=' Search ']");
    private static final By dataRows = By.xpath("//div[@class='oxd-table']");

    public AdminPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void enterAdminName() throws Exception {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(userInput));
        highlight(input);
        input.sendKeys("BAdmin");
        Thread.sleep(2000);
        unHighlight(input);
    }

    public void clickUserRoleDropdown() throws Exception {
        WebElement dropdown = driver.findElement(userRoleDropdown);
        highlight(dropdown);
        dropdown.click();
        Thread.sleep(2000);
        unHighlight(dropdown);
    }

    public void selectAdminRole() throws Exception {
        WebElement adminRole = driver.findElement(adminRoleOption);
        highlight(adminRole);
        adminRole.click();
        Thread.sleep(2000);
        unHighlight(adminRole);
    }

    public void clickSearchBtn() throws Exception {
        WebElement searchButton = driver.findElement(searchBtn);
        highlight(searchButton);
        searchButton.click();
        Thread.sleep(5000);
        unHighlight(searchButton);
    }

    public boolean hasData(){
        List<WebElement> rows = driver.findElements(dataRows);
        return !rows.isEmpty();
    }
}
