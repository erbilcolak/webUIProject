package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class TLDriver {

    //AF: Create a ThreadLocal Driver for Thread-safe automation
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


    public synchronized static void setDriver(String browser) throws MalformedURLException {
        Locale.setDefault(new Locale("en", "EN"));

        switch (PropReader.GetInstance().getRunOn().toString()) {
            case "docker":
                tlDriver.set(new RemoteWebDriver(new URL(PropReader.getGridHubAddress()), OptionsManager.getChromeOptions().addArguments("--headless")));
                break;
            case "local":
                switch (PropReader.GetInstance().getBrowser().toString()) {
                    case "chrome":
                        tlDriver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
                        break;
                    case "firefox":
                        tlDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
                        break;
                    case "ie":
                        tlDriver.set(new InternetExplorerDriver());
                        break;
                    default:
                        System.out.println("Unable to identify browser type");
                        break;
                }
                break;
            default:
                System.out.println("Unable to idendity driver type!");
                break;
        }
    }

    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }
}
