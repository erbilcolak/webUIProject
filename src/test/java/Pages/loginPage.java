package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.AssertJUnit.assertEquals;

public class loginPage extends basePage {
    public loginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#accountBtn > div.icon-container")
    WebElement navigationIconUser;

    @FindBy(css = "div.account-button.login")
    WebElement loginButton;

    @FindBy(id = "login-email")
    WebElement loginEmailInput;

    @FindBy(id = "login-password-input")
    WebElement loginPasswordInput;

    @FindBy(css = "form > .q-button.q-button-medium.q-fluid.q-primary.submit")
    WebElement submitButton;

    @FindBy(className = "login-container")
    WebElement afterLoginUserIconTextArea;

    String natificationPopUp = "modal-close";

    String afterLoginUserIconText = "Hesabım";


    public void openLoginPanel() {
        wait.until(ExpectedConditions.visibilityOf(navigationIconUser)).isDisplayed();
        hover(navigationIconUser);
        wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
    }

    public void successLogin(String userEmailAdress, String userPassword) throws InterruptedException {
        openLoginPanel();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        wait.until(ExpectedConditions.visibilityOf(loginEmailInput)).isDisplayed();
        loginEmailInput.sendKeys(userEmailAdress);
        loginPasswordInput.sendKeys(userPassword);
        click(submitButton);
        Thread.sleep(2000);
        assertEquals(afterLoginUserIconText, afterLoginUserIconTextArea.getText());
        Thread.sleep(1000);

        if (exists(By.className(natificationPopUp))) {
            click(By.className(natificationPopUp));
        }
    }
}
