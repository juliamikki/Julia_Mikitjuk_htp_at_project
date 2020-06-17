package steps.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web_driver.Driver;
import web_pages.cinema.SilverScreenMainPage;

public class CinemaJUnit4Test {

    private static WebDriver driver;
    private static SilverScreenMainPage silverScreenMainPage;
    private static final Logger LOGGER = LogManager.getLogger(CinemaJUnit4Test.class);

    @BeforeClass
    public static void startBrowser() {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        silverScreenMainPage = new SilverScreenMainPage (driver);

        LOGGER.info(">>> Browser is started");
    }

    @Test
    public void searchMovie () {
        silverScreenMainPage.navigateToSilverScreen();
        silverScreenMainPage.searchWord("ножи");
        Driver.setWaiter().until(ExpectedConditions.visibilityOf(silverScreenMainPage.movieListHeader));
        assert silverScreenMainPage.searchResults.size() > 0 : "The list of items isn't displayed";
        assert silverScreenMainPage.checkItemContent("ножи") : "The item doesn't contain ножи";
    }

    @Test
    public void loginApp () {
        silverScreenMainPage.navigateToSilverScreen();
        silverScreenMainPage.logInToPersonalAccount("julia.mikitjuk@gmail.com", "zaqwerty");
        assert silverScreenMainPage.myLevel.getText().contains("Новичок") : "Incorrect";
    }

    @Test
    public void loginAppBlankField () {
        silverScreenMainPage.navigateToSilverScreen();
        silverScreenMainPage.logInToPersonalAccount("", "zaqwerty");
    }

    @Test
    public void loginWithNoUser () {
        silverScreenMainPage.navigateToSilverScreen();
        silverScreenMainPage.logInToPersonalAccount("vasya.pupkin@gmail.com", "zaqwerty");
    }

    @AfterClass
    public static void stopBrowser() {
        Driver.destroy();
        LOGGER.info(">>> Browser is stopped");
    }

}
