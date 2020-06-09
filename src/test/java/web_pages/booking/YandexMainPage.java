package web_pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class YandexMainPage extends AbstractPage {

    @FindBy(xpath = "//a[contains(@class,'button desk-notif-card')]")
    private WebElement logInToMail;

    public YandexMainPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToYandex() {
        String url = PropertiesParser.getYandexProperties().getProperty("URL");
        driver.get(url);
    }

    public void navigateToAuthorizationPage () {
        logInToMail.click();
    }

}
