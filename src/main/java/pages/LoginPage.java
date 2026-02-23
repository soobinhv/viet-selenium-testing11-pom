package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
//    định nghĩa các elements, locators cho page Login
    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.cssSelector("input[name='password']");
    private static final By LOGIN_BTN    = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

//    define test steps
    public void open(){
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(url);
//      wait until the username input is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
    }

    public void enterUsername(String username) throws InterruptedException {
//        find element username input
        WebElement usernameInput = driver.findElement(USERNAME_INPUT);
//        clear username input
        usernameInput.clear();
//        fill username input
        usernameInput.sendKeys(username);
//        add wait if need
        Thread.sleep(2000);
    }

    public void enterPassword(String password) throws InterruptedException {
        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        Thread.sleep(2000);
    }

    public void clickLoginBtn(){
        WebElement loginBtn = driver.findElement(LOGIN_BTN);
        loginBtn.click();
    }

    public void login(String username, String password) throws InterruptedException {
        open();
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
    }
}
