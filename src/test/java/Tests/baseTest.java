package Tests;

import Pages.page;
import Utility.PropReader;
import Utility.TLDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.sql.Driver;

public class baseTest {

    protected page page;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browser) throws MalformedURLException {
        TLDriver.setDriver(browser);
        page = new page(TLDriver.getDriver());
        setBasePage(TLDriver.getDriver());
        TLDriver.getDriver().manage().window().maximize();
        page.click(By.className("fancybox-close"));
    }

    @AfterMethod
    public void afterMethod() {
        try {
            TLDriver.getDriver().quit();
        } catch (Exception e) {
            System.out.println("***********unable to quit**************");
        }
    }

    private void setBasePage(WebDriver driver) {
        String jenkinsParallelMethod = System.getenv("ParallelMethod");
        String jenkinsEnvironment = System.getenv("Environment");
        if (jenkinsParallelMethod == null) {
            if (PropReader.getEnvironment().equals("production")) {
                driver.get("https://trendyol.com");
            } else {
                driver.get("https://trendyol.com");
            }
        }
    }
}
