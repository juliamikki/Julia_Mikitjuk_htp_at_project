package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class YandexMainPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(YandexMainPage.class);
    private int pageOrder = 0;

    @FindBy(xpath = "//a[contains(@class,'button desk-notif-card')]")
    private WebElement logInToMail;

    public YandexMainPage(WebDriver driver) {
        super(driver);
    }

    public int getPageOrder() {
        return pageOrder;
    }

    public void navigateToYandex() {
        LOGGER.debug(">>> Navigate to yandex.by");
        String url = PropertiesParser.getYandexProperties().getProperty("URL");
        driver.get(url);
    }

    public void navigateToAuthorizationPage () {
        LOGGER.debug(">>> Navigate to Yandex authorization page");
        logInToMail.click();
    }

}
