package web_pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class YandexAuthorizationPage extends AbstractPage {

    @FindBy(id = "passp-field-login")
    private WebElement emailInput;

    @FindBy(xpath = "//div[contains(@class,'passp-sign-in-button')]")
    private WebElement logInButton;

    @FindBy(id="passp-field-passwd")
    private WebElement passwordInput;

    public YandexAuthorizationPage (WebDriver driver) {
        super(driver);
    }

    public void logInToYandexMail () {
        emailInput.sendKeys(PropertiesParser.getYandexProperties().getProperty("EMAIL"));
        logInButton.click();
        passwordInput.sendKeys(PropertiesParser.getYandexProperties().getProperty("PWD"));
        logInButton.click();
    }
}
