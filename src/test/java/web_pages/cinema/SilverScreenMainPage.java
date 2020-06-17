package web_pages.cinema;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.PathList;
import utilities.PropertiesParser;
import web_pages.AbstractPage;
import web_pages.booking.BookingMainPage;

import java.util.List;

public class SilverScreenMainPage  extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(SilverScreenMainPage.class);
    private Actions actions = new Actions(driver);

    @FindBy (xpath = "(//*[@id='svg-icon-search'])[1]")
    private WebElement searchIcon;

    @FindBy (xpath = "//*[contains(@placeholder,'Поиск')]")
    private WebElement searchField;

    @FindBy (xpath = "//h2[contains(., 'Фильмы')]")
    public WebElement movieListHeader;

    @FindBy (xpath = "//*[@class='sc-iKIEpe bLYdTB']//a/span")
    public List <WebElement> searchResults;

    @FindBy (xpath = "//*[@class='sc-fyjhYU eVJmYW']")
    private WebElement logIn;

    @FindBy (xpath = "//button[contains(text(), 'Войти')]")
    private WebElement logInButton;

    @FindBy (xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy (xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy (xpath = "(//span[contains(text(),'Мой уровень')])[1]")
    public WebElement myLevel;

    @FindBy (xpath = " //*[contains(text(),'Необходимо заполнить поле')]")
    public WebElement popUpMessageBlankField;

    @FindBy (xpath = " //*[contains(text(),'Пользователь не найден')]")
    public WebElement popUpMessageNoUser;

    public SilverScreenMainPage (WebDriver driver) {
        super(driver);
    }

    public void navigateToSilverScreen() {
        String url = PropertiesParser.getProperties(PathList.getSilverScreenPropertyPath()).getProperty("URL");
        driver.get(url);
    }

    public void searchWord (String word) {
        actions.moveToElement(searchIcon).build().perform();
        searchField.clear();
        searchField.sendKeys(word);
        searchField.sendKeys(Keys.ENTER);
    }

    public void logInToPersonalAccount (String login, String pwd) {
        actions.moveToElement(logIn).build().perform();
        email.click();
        email.sendKeys(login);
        password.clear();
        password.sendKeys(pwd);
        logInButton.click();
    }

    public boolean checkItemContent (String word) {

        for (int i=0; i<searchResults.size(); i++) {
            if (!searchResults.get(i).getText().toLowerCase().contains(word.toLowerCase())){
                return false;
            }
        }
        return true;
    }
}
