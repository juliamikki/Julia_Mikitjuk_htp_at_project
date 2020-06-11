package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utilities.booking.WebElementsParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_driver.Driver;
import web_pages.AbstractPage;

import java.util.List;

public class BookingSearchResultsPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(BookingSearchResultsPage.class);
    private Actions actions = new Actions(driver);

    @FindBy(how=How.CSS, using="#group_adults")
    private WebElement adultsField;

    @FindBy(how=How.CSS, using="#group_children")
    private WebElement childrenField;

    @FindBy(how=How.CSS, using="#no_rooms")
    private WebElement roomsField;

    @FindBy(how=How.CSS, using=".sb-searchbox__button")
    public WebElement searchButton;

    @FindBy (how=How.XPATH, using="//*[@id='filter_price']//a[1]")
    public WebElement minPriceHotelsFilter;

    @FindBy (how=How.XPATH, using="//*[@id='filter_price']//a[1]//input")
    public WebElement minPriceHotelsFilterSelected;

    @FindBy (how=How.XPATH, using="//*[@id='filter_price']//a[5]")
    public WebElement maxPriceHotelsFilter;

    @FindBy (how=How.XPATH, using="//*[@id='filter_price']//a[5]//input")
    public WebElement maxPriceHotelsFilterSelected;

    @FindBy (how=How.CSS, using="#filter_class a[data-id='class-3']")
    private WebElement threeStarsHotels;

    @FindBy (how=How.CSS, using="#filter_class a[data-id='class-4']")
    private WebElement fourStarsHotels;

    @FindBy(how=How.XPATH, using="//*[@class=' sort_category   sort_price ']")
    public WebElement lowestPriceFirst;

    @FindBy(how=How.XPATH, using="//*[@class=' sort_category  selected  sort_price ']")
    public WebElement lowestPriceFirstSelected;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][1]//div[contains(@class,'bui-price-display__value')]")
    private WebElement firstHotel;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][10]")
    private WebElement tenthHotel;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]")
    private WebElement tenthHotelTitle;

    //private List<WebElement> heartIcon = driver.findElements(By.className("bk-icon -iconset-heart sr-wl-entry-heart-svg"));

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][1]//*[@class='bk-icon -iconset-heart sr-wl-entry-heart-svg']")
    public WebElement firstHotelHeartIcon;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][25]//*[@class='bk-icon -iconset-heart sr-wl-entry-heart-svg']")
    public WebElement lastHotelHeartIcon;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][1]//*[contains(@class,'sr-hotel__name')]")
    public WebElement firstHotelTitle;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][25]//*[contains(@class,'sr-hotel__name')]")
    public WebElement lastHotelTitle;

    public BookingSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void enterAccommodationDetailsUsingActions (int adults, int children, int rooms) {
        LOGGER.debug(">>> Enter accommodation details (adults, children, rooms) using actions");
        actions.click(adultsField).sendKeys(Integer.toString(adults)).sendKeys(Keys.ENTER).build().perform();
        actions.click(roomsField).sendKeys(Integer.toString(rooms)).sendKeys(Keys.ENTER).build().perform();
    }

    public void chooseStarRating () {
        LOGGER.debug(">>> Choose star rating");
        threeStarsHotels.click();
        fourStarsHotels.click();
    }

    public int getMinFilterPrice() {
        LOGGER.debug(">>> Extract hotel price per night from the lowest budget hotels");
        return WebElementsParser.parseToNumber(minPriceHotelsFilter);
    }

    public int getMaxFilterPrice() {
        LOGGER.debug(">>> Extract hotel price per night from the highest budget hotels");
        return WebElementsParser.parseToNumber(maxPriceHotelsFilter);
    }

    public int getFirstHotelPricePerNight() {
        LOGGER.debug(">>> Extract first hotel price per night from the suggested list");
        return WebElementsParser.parseToNumber(firstHotel)/7;
    }

    public void applyStyleTo10Hotel () {
        LOGGER.debug(">>> Apply styles to the background color and title color of the tenth hotel");

        Driver.executeJS("arguments[0].scrollIntoView();", tenthHotel);
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-hotelid][10]"))).build().perform();
        Driver.executeJS("arguments[0].setAttribute('style','background-color : palegreen')",tenthHotel);
        Driver.executeJS("arguments[0].setAttribute('style', 'color : red')", tenthHotelTitle);
    }

    public String getColorOf10HotelTitle () {
        LOGGER.debug(">>> Get title color of the tenth hotel");
        return tenthHotelTitle.getCssValue("color");
    }

    public void addFavorites () throws InterruptedException {
        firstHotelHeartIcon.click();
        lastHotelHeartIcon.click();
    }

}
