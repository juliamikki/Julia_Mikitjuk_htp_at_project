package web_pages.booking;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web_pages.AbstractPage;


public class YandexInboxPage extends AbstractPage {

    @FindBy (xpath = "//*[@class='mail-MessageSnippet-FromText']")
    public WebElement lastMail;

    @FindBy (id = "nb-4")
    public WebElement lastMailCheckbox;

    @FindBy (xpath = "//*[@id='nb-1']//div[1]/div/div/div/a/div")
    public WebElement lastMailAtSpam;

    @FindBy (xpath = "//*[@class='mail-ComposeButton-Refresh js-main-action-refresh ns-action']")
    public WebElement refreshEmailBox;

    @FindBy (xpath = "//*[@data-title='Спам']")
    public WebElement spam;

    /*@FindBy (xpath = "//*[@title='Не спам!']")
    private WebElement notSpam;*/

    @FindBy (xpath = "//div[contains(@class,'ns-view-toolbar-buttons')]//div[7]")
    private WebElement notSpam;

    @FindBy (xpath = "//p/a[contains(@href,'trashmail.com')]")
    private WebElement confirmationLink;

    @FindBy (xpath = "//div[contains(@title, 'Удалить')]")
    private WebElement recycleBin;

    @FindBy (xpath = "//a[contains(text(), 'Confirm')]")
    private WebElement confirmButton;


    public YandexInboxPage (WebDriver driver) {
        super(driver);
    }

    public void confirmTrashMail () {
        lastMail.click();
        confirmationLink.click();
    }

    public void deleteLastEmail () {
        lastMailCheckbox.click();
        recycleBin.click();
    }


    public void confirmBookingRegistration () throws InterruptedException {
        spam.click();
        lastMailAtSpam.click();
        notSpam.click();
        Thread.sleep(3000);
        confirmButton.click();
    }

}
