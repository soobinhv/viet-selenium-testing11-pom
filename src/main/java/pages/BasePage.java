package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
    }

    public void highlight(WebElement el) {
        js.executeScript("arguments[0].style.border='3px solid red'", el);
        js.executeScript("arguments[0].style.backgroundColor='yellow'", el);
    }

    public void unHighlight(WebElement el) {
        try{
            js.executeScript("arguments[0].style.border=''", el);
            js.executeScript("arguments[0].style.backgroundColor=''", el);
        } catch (Exception e) {

        }
    }
}
