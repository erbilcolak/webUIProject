package Pages;

import Utility.TLDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class basePage {

    private String base;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait waitRed;
    protected Actions action;


    public basePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        waitRed = new WebDriverWait(driver, 3);
        action = new Actions(driver);
    }

    public <T> void click(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.elementToBeClickable((By) elementAttr)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable((WebElement) elementAttr)).click();
        }
    }

    protected boolean exists(By by) {
        return driver.findElements(by).size() > 0;
    }


    protected void hover(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        action.moveToElement(element).build().perform();
    }

    public boolean checkImageStatus(WebElement image) throws IOException {
        int response = 0;

        String source = image.getAttribute("src");

        if (source.equals("#"))
            return false;

        HttpURLConnection Connect = (HttpURLConnection) (new URL(source)).openConnection();
        response = Connect.getResponseCode();

        if (!((response + "").startsWith("4") || (response + "").startsWith("5"))) {
            System.out.println("Valid Source:" + ((source == null) ? "null" : source));
            return true;
        } else {
            System.out.println("Invalid source:" + ((source == null) ? "null" : source));
            return false;
        }
    }
}
