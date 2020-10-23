package Utility;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class OptionsManager {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");

        //options.setCapability(CapabilityType.PROXY, BrowserMob.getSeleniumProxy());
        // options.addArguments("--start-maximized");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }
}
