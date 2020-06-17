package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web_pages.AbstractPage;

public class YandexInboxPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(YandexInboxPage.class);
    private int pageOrder = 1;

    @FindBy (xpath = "//*[@title='robot@trashmail.com']")
    public WebElement emailFromTrashMailBot;

    @FindBy (xpath = "//p/a[contains(@href,'trashmail.com')]")
    private WebElement confirmTrashMailLink;

    @FindBy (xpath = " //*[@title='noreply@booking.com']")
    private WebElement emailFromBooking;

    @FindBy (xpath = "//a[contains(text(), 'Confirm')]")
    private WebElement confirmBookingButton;

    @FindBy (xpath = "//a[@href='#spam']")
    private WebElement spam;

   /* @FindBy (xpath = "//a[@data-title='Спам']")
    private WebElement spam;

    @FindBy (xpath = "(//*[contains(@class,'mail-NestedList')]/a)[4]")
    private WebElement spam;

    @FindBy (xpath = "//*[@class='mail-NestedList-Item-Name' and contains (text(), 'Спам')]")
    private WebElement spam;*/

    @FindBy (xpath = "//*[@class=' nb-button _nb-small-pseudo-button js-show-content']")
    private WebElement showContent;

    @FindBy (id = "nb-4")
    private WebElement lastMailCheckbox;

    @FindBy (xpath = "//div[contains(@title, 'Удалить')]")
    private WebElement recycleBin;

    @FindBy (xpath = "//*[@class='mail-ComposeButton-Refresh js-main-action-refresh ns-action']")
    public WebElement refreshEmailBox;

    public YandexInboxPage (WebDriver driver) {
        super(driver);
    }

    public int getPageOrder() {
        return pageOrder;
    }

    public void confirmTrashMail () {
        emailFromTrashMailBot.click();
        confirmTrashMailLink.click();
        LOGGER.debug(">>> TrashMail.com registration is confirmed successfully!");
    }

    public void deleteLastEmail () {
        lastMailCheckbox.click();
        recycleBin.click();
        LOGGER.debug(">>> Last inbox email is deleted successfully!");
    }

    public void confirmBookingRegistration () {
        spam.click();
        emailFromBooking.click();
        showContent.click();
        confirmBookingButton.click();
        LOGGER.debug(">>> Booking.com registration is confirmed successfully!");
    }

}
