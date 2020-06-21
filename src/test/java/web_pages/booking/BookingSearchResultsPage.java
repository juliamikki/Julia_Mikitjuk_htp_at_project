package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utilities.WebElementsParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_driver.Driver;
import web_pages.AbstractPage;

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
        actions.click(adultsField).sendKeys(Integer.toString(adults)).sendKeys(Keys.ENTER).build().perform();
        actions.click(roomsField).sendKeys(Integer.toString(rooms)).sendKeys(Keys.ENTER).build().perform();
        LOGGER.debug(">>> Accommodation details are entered using actions successfully!");
    }

    public void chooseStarRating () {
        threeStarsHotels.click();
        fourStarsHotels.click();
        LOGGER.debug(">>> Star rating is chosen successfully!");
    }

    public int getMinFilterPrice() {
        LOGGER.debug(">>> Hotel price per night from the lowest budget hotels is extracted successfully!");
        return WebElementsParser.parseToNumber(minPriceHotelsFilter);
    }

    public int getMaxFilterPrice() {
        LOGGER.debug(">>> Hotel price per night from the highest budget hotels is extracted successfully!");
        return WebElementsParser.parseToNumber(maxPriceHotelsFilter);
    }

    public int getFirstHotelPricePerNight(int durationOfStay) {
        LOGGER.debug(">>> First hotel price per night from the suggested list is extracted successfully!");
        return WebElementsParser.parseToNumber(firstHotel)/durationOfStay;
    }

    public void applyStyleTo10Hotel () {
        Driver.executeJS("arguments[0].scrollIntoView();", tenthHotel);
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-hotelid][10]"))).build().perform();
        Driver.executeJS("arguments[0].setAttribute('style','background-color : palegreen')",tenthHotel);
        Driver.executeJS("arguments[0].setAttribute('style', 'color : red')", tenthHotelTitle);
        LOGGER.debug(">>> Styles to the background color and title color of the tenth hotel are applied successfully!");
    }

    public String getColorOf10HotelTitle () {
        LOGGER.debug(">>> The color of the tenth hotel title is taken successfully!");
        return tenthHotelTitle.getCssValue("color");
    }

    public void addFavorites ()  {
        firstHotelHeartIcon.click();
        lastHotelHeartIcon.click();
        LOGGER.debug(">>> Hotels are added to the favourites successfully!");
    }

}
