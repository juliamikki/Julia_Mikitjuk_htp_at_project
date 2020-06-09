package web_pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web_pages.AbstractPage;

public class YandexInboxPage extends AbstractPage {

    @FindBy (xpath = "//*[@class='mail-MessageSnippet-FromText']")
    public WebElement lastMail;

    @FindBy (xpath = "//p/a[contains(@href,'trashmail.com')]")
    private WebElement confirmationLink;

    @FindBy (id = "nb-4")
    private WebElement lastMailCheckbox;

    @FindBy (xpath = "//div[contains(@title, 'Удалить')]")
    private WebElement recycleBin;


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

}
