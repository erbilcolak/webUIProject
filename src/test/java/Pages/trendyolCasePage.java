package Pages;

import Utility.TLDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.xml.ws.WebEndpoint;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;


public class trendyolCasePage extends basePage {
    public trendyolCasePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(className = "image-container")
    WebElement productDetails;

    @FindBy(css = "#boutique-detail-app > div > div.boutique-meta-container > div.boutique-meta > ul > li")
    WebElement breadcrumb;

    @FindBy(className = "p-card-img ")
    WebElement productCards;

    @FindBy(css = ".add-to-bs-tx")
    WebElement addBasketButton;

    @FindBy(className = "basket-item-count")
    WebElement basketItemCount;

    @FindBy(id = "myBasketListItem")
    WebElement myBasketButton;

    @FindBy(className = "i-trash")
    WebElement trashIcon;

    @FindBy(css = ".btn-item.btn-remove")
    WebElement basketModalDeleteButton;

    @FindBy(css = ".btn.shoppingStart")
    WebElement shoppingStartButton;

    String shoppingStartText = "Alışverişe Başla";

    String categoryTabs = "#navigation-wrapper > nav > ul > li";

    public void goCategoryTab(String categoryName) throws InterruptedException {
        int categoryOrder = 0;
        boolean matchCategoryTabName = false;

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(categoryTabs))).isDisplayed();
        List<WebElement> categories = TLDriver.getDriver().findElements(By.cssSelector(categoryTabs));
        for (WebElement element : categories) {
            categoryOrder++;
            if (element.getText().equals(categoryName)) {
                matchCategoryTabName = true;
                element.click();
                Thread.sleep(1500);
                assertTrue(driver.findElement(By.cssSelector("#navigation-wrapper > nav > ul > li:nth-child(" + categoryOrder + ")")).getAttribute("class").contains("active"));
                break;
            } else continue;
        }
        if (matchCategoryTabName == false) {
            fail("*****Aranılan Kategori Tab'i bulunamadı.*****");
        }
    }

    public void checkAllImagesLoaded() throws IOException {
        List<WebElement> elements = driver.findElements(By.cssSelector(".image-container > img"));

        for (WebElement element : elements) {
            checkImageStatus(element);
        }
    }

    public void goProductDetail(String checkCategoryName) {
        wait.until(ExpectedConditions.elementToBeClickable(productDetails)).click();
        assertEquals(true, driver.getCurrentUrl().contains(checkCategoryName));
        wait.until(ExpectedConditions.visibilityOf(productDetails)).isDisplayed();

        wait.until(ExpectedConditions.elementToBeClickable(productDetails)).click();
        wait.until(ExpectedConditions.visibilityOf(addBasketButton)).isDisplayed();
    }

    public void addBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(addBasketButton)).click();
        wait.until(ExpectedConditions.visibilityOf(basketItemCount)).isDisplayed();
        System.out.println(basketItemCount.getText());
        assertEquals("1", basketItemCount.getText());
    }

    public void assertAddedBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(myBasketButton)).click();
        assertEquals(true, driver.getCurrentUrl().contains("basket"));
        wait.until(ExpectedConditions.visibilityOf(trashIcon)).isDisplayed();
    }

    public void basketDeleteProduct() {
        wait.until(ExpectedConditions.visibilityOf(trashIcon)).isDisplayed();
        click(trashIcon);
        wait.until(ExpectedConditions.elementToBeClickable(basketModalDeleteButton)).click();
        wait.until(ExpectedConditions.visibilityOf(shoppingStartButton)).isDisplayed();
        assertEquals(shoppingStartText, shoppingStartButton.getText());
    }
}
