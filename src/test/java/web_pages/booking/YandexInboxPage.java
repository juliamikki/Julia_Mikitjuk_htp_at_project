package web_pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web_pages.AbstractPage;

public class YandexInboxPage extends AbstractPage {

    @FindBy (xpath = "//*[@class='mail-MessageSnippet-FromText']")
    public WebElement lastMail;

    public YandexInboxPage (WebDriver driver) {
        super(driver);
    }

}
