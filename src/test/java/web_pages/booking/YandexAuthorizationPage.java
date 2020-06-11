package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class YandexAuthorizationPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(YandexAuthorizationPage.class);
    private int pageOrder = 1;

    @FindBy(id = "passp-field-login")
    private WebElement emailInput;

    @FindBy(xpath = "//div[contains(@class,'passp-sign-in-button')]")
    private WebElement logInButton;

    @FindBy(id="passp-field-passwd")
    private WebElement passwordInput;

    public YandexAuthorizationPage (WebDriver driver) {
        super(driver);
    }

    public int getPageOrder() {
        return pageOrder;
    }

    public void logInToYandexMail () {
        LOGGER.debug(">>> Log in to Yandex email");
        emailInput.sendKeys(PropertiesParser.getYandexProperties().getProperty("EMAIL"));
        logInButton.click();
        passwordInput.sendKeys(PropertiesParser.getYandexProperties().getProperty("PWD"));
        logInButton.click();
    }
}
