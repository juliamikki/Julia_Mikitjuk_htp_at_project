package web_pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

public class BookingSignInPage extends AbstractPage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy (xpath = "//form[@class='nw-signin']//*[text() = 'Next']")
    private WebElement nextButton;

    @FindBy (xpath = "//div[@class='nw-password']//input")
    private WebElement password;

    @FindBy (xpath = " //form[@class='nw-signin']//*[text() = 'Sign in']")
    private WebElement signInButton;

    public BookingSignInPage (WebDriver driver) {
        super(driver);
    }

    public void signIn () {
        email.sendKeys(PropertiesParser.getTrashMailProperties().getProperty("EMAIL"));
        nextButton.click();
        password.sendKeys(PropertiesParser.getBookingProperties().getProperty("PWD"));
        signInButton.click();
    }
}
