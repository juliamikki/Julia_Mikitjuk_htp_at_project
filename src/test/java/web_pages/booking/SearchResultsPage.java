package web_pages.booking;

import utilities.booking.WebElementsParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import web_driver.Driver;
import web_pages.AbstractPage;

public class SearchResultsPage extends AbstractPage {

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

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][1]//div[contains(@class,'bui-price-display__value')]")
    private WebElement firstHotel;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][10]")
    private WebElement tenthHotel;

    @FindBy(how=How.XPATH, using="//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]")
    private WebElement tenthHotelTitle;

    @FindBy(how=How.XPATH, using="//*[@class=' sort_category   sort_price ']")
    public WebElement lowestPriceFirst;

    @FindBy(how=How.XPATH, using="//*[@class=' sort_category  selected  sort_price ']")
    public WebElement lowestPriceFirstSelected;

    @FindBy(how=How.CLASS_NAME, using="sr-usp-overlay__loading")
    public WebElement overlayCheckMark;

    public SearchResultsPage (WebDriver driver) {
        super(driver);
    }

    public void enterAccommodationDetailsUsingActions (int adults, int children, int rooms) {
        actions.click(adultsField).sendKeys(Integer.toString(adults)).sendKeys(Keys.ENTER).build().perform();
        actions.click(roomsField).sendKeys(Integer.toString(rooms)).sendKeys(Keys.ENTER).build().perform();
    }

    public void chooseStarRating () {
        threeStarsHotels.click();
        fourStarsHotels.click();
    }

    public int getMinFilterPrice() {
        return WebElementsParser.parseToNumber(minPriceHotelsFilter);
    }

    public int getMaxFilterPrice() {
        return WebElementsParser.parseToNumber(maxPriceHotelsFilter);
    }

    public int getFirstHotelPricePerNight() {
        return WebElementsParser.parseToNumber(firstHotel)/7;
    }

    public void applyStyleTo10Hotel () {
        Driver.executeJS("arguments[0].scrollIntoView();", tenthHotel);
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-hotelid][10]"))).build().perform();
        Driver.executeJS("arguments[0].setAttribute('style','background-color : palegreen')",tenthHotel);
        Driver.executeJS("arguments[0].setAttribute('style', 'color : red')", tenthHotelTitle);
    }

    public String getColorOf10HotelTitle () {
        return tenthHotelTitle.getCssValue("color");
    }

}
