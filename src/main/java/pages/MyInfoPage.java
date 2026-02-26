package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyInfoPage extends BasePage {

    private static final By AVATAR_WRAPPER = By.cssSelector("div.orangehrm-edit-employee-image-wrapper");

    private static final By PLUS_BTN = By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']");

    private static final By FILE_INPUT = By.cssSelector("input[type='file']");

    public MyInfoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void uploadAvatar(String absolutePath) throws InterruptedException {
        WebElement avatarEl = wait.until(ExpectedConditions.elementToBeClickable(AVATAR_WRAPPER));
        highlight(avatarEl);
        avatarEl.click();
        unHighlight(avatarEl);

        WebElement plusBtn = wait.until(ExpectedConditions.elementToBeClickable(PLUS_BTN));
        highlight(plusBtn);
        plusBtn.click();
        unHighlight(plusBtn);

        Thread.sleep(2000);

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(FILE_INPUT));
        fileInput.sendKeys(absolutePath);

        Thread.sleep(2000);
    }
}
