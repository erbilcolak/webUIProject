package Tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class trendyolCaseTest extends baseTest {

    @Test
    public void trendyolCaseTest() throws InterruptedException, IOException {
        page.loginPage().successLogin("erbilcase@gmail.com", "qwert123");
        page.trendyolCasePage().goCategoryTab("KADIN");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("ERKEK");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("ÇOCUK");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("EV & YAŞAM");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("SÜPERMARKET");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("KOZMETİK");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("AYAKKABI & ÇANTA");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("SAAT & AKSESUAR");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("ELEKTRONİK");
        page.trendyolCasePage().checkAllImagesLoaded();

        page.trendyolCasePage().goCategoryTab("SÜPERMARKET");
        page.trendyolCasePage().goProductDetail("supermarket");
        page.trendyolCasePage().addBasket();
        page.trendyolCasePage().assertAddedBasket();
        page.trendyolCasePage().basketDeleteProduct();
    }
}
