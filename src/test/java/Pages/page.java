package Pages;

import Utility.TLDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.ConcurrentHashMap;

public class page extends basePage {


    private ConcurrentHashMap<WebDriver, ConcurrentHashMap<String, Object>> pageHashMap = new ConcurrentHashMap<WebDriver, ConcurrentHashMap<String, Object>>();

    public page(WebDriver driver) {
        super(driver);
    }

    private synchronized void setPageHashMap(WebDriver driver, String pageName, Object obj) {
        if (!pageHashMap.containsKey(driver)) {
            ConcurrentHashMap<String, Object> pageHashMapPageNamePageObj = new ConcurrentHashMap<String, Object>();
            pageHashMapPageNamePageObj.put(pageName, obj);
            pageHashMap.put(driver, pageHashMapPageNamePageObj);
        } else {
            if (!pageHashMap.get(driver).containsKey(pageName)) {
                pageHashMap.get(driver).put(pageName, obj);
            }
        }
    }

    private synchronized Object getPageHashMap(WebDriver driver, String pageName) {
        if (pageHashMap.containsKey(driver)) {
            return pageHashMap.get(driver).get(pageName);
        } else {
            return null;
        }
    }

    private synchronized <TPage extends basePage> TPage createPage(String pageName, Class<TPage> page) {
        TPage tpage = (TPage) getPageHashMap(TLDriver.getDriver(), pageName);
        if (tpage != null) {
            return tpage;
        } else {
            tpage = PageFactory.initElements(TLDriver.getDriver(), page);
            setPageHashMap(TLDriver.getDriver(), pageName, tpage);
            return (TPage) getPageHashMap(TLDriver.getDriver(), pageName);
        }
    }

    public synchronized loginPage loginPage() {
        return createPage("loginPage", loginPage.class);
    }

    public synchronized trendyolCasePage trendyolCasePage() {
        return createPage("trendyolCasePage", trendyolCasePage.class);
    }
}
