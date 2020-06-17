package steps.cucumber.cinema;
import cucumber.api.java.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.PathList;
import utilities.PropertiesParser;
import web_driver.Driver;
import web_pages.cinema.SilverScreenMainPage;

import java.util.Properties;

public class CinemaBaseSteps {

    private static final Logger LOGGER = LogManager.getLogger(CinemaBaseSteps.class);
    private static WebDriver driver;
    private static SilverScreenMainPage silverScreenMainPage;
    private static Properties propCinema;

    public static WebDriver getDriver() {
        return driver;
    }

    public static SilverScreenMainPage getSilverScreenMainPage() {
        return silverScreenMainPage;
    }

    public static Properties getPropCinema() {
        return propCinema;
    }

    @Before
    public static void startBrowser() {
        driver = Driver.getDriver();
        Driver.setTimeouts();
        Driver.maximize();
        silverScreenMainPage = new SilverScreenMainPage (driver);
        propCinema = PropertiesParser.getProperties(PathList.getSilverScreenPropertyPath());
        LOGGER.info(">>> Cucumber: Browser is started successfully!");
    }

    @After
    public static void stopBrowser() {
        Driver.destroy();
        LOGGER.info(">>> Cucumber: Browser is stopped successfully!");
    }

}
