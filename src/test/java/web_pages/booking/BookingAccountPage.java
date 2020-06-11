package web_pages.booking;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_pages.AbstractPage;

public class BookingAccountPage extends AbstractPage {

    @FindBy(how= How.XPATH, using="//*[@class='user_name_block']")
    private WebElement accountButton;

    @FindBy(how= How.XPATH, using="//a[contains(text(), 'My Dashboard')]")
    private WebElement myDashboard;

    @FindBy(how= How.XPATH, using="//a[contains(text(), 'My wish lists')]")
    private WebElement myWishList;

    @FindBy(how= How.XPATH, using="//*[@data-bui-ref='carousel-item'][1]//h1")
    public WebElement firstFavoriteHotel;

    @FindBy(how= How.XPATH, using="//*[@data-bui-ref='carousel-item'][2]//h1")
    public WebElement secondFavoriteHotel;

    @FindBy(how= How.XPATH, using="//*[@class='email-confirm-banner__header']")
    public WebElement emailConfirmBanner;

    public BookingAccountPage (WebDriver driver) {
        super(driver);
    }

    public void goToMyDashboard () {
        accountButton.click();
        myDashboard.click();
    }

    public void goToMyNextTripList () {
        accountButton.click();
        myWishList.click();
    }

    public Boolean isEmailConfirmBannerPresented () {
        Boolean condition = false;
        try {
            condition = emailConfirmBanner.isDisplayed();
        } catch (NoSuchElementException e) {
            return condition;
        }
        return condition;
    }

}
