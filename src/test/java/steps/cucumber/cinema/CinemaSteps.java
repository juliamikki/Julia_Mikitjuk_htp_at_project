package steps.cucumber.cinema;

import cucumber.api.java.en.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web_driver.Driver;
import web_pages.cinema.SilverScreenMainPage;

import java.util.Properties;

public class CinemaSteps {

    private static final Logger LOGGER = LogManager.getLogger(CinemaSteps.class);
    private static WebDriver driver;
    private static SilverScreenMainPage silverScreenMainPage;
    private static Properties propCinema;

    public CinemaSteps() {
        driver = CinemaBaseSteps.getDriver();
        silverScreenMainPage = CinemaBaseSteps.getSilverScreenMainPage();
        propCinema = CinemaBaseSteps.getPropCinema();
    }

    @Given("I open an app")
    public void iOpenAnApp () {
        silverScreenMainPage.navigateToSilverScreen();
    }

    @When ("I search for {string} word")
    public void iSearchForWord (String word) {
        silverScreenMainPage.navigateToSilverScreen();
        silverScreenMainPage.searchWord(word);
    }

    @When ("I login with {string} and {string}")
    public void iLogIn (String login, String password)  {
        silverScreenMainPage.logInToPersonalAccount(login, password);
    }

    @When ("I left blank {string} field")
    public void iLeftBlankField (String field) {

        String login = propCinema.getProperty("LOGIN");
        String password = propCinema.getProperty("PWD");

        switch (field) {
            case "login" : login = "";
            case "password" : password = "";
            default : LOGGER.info("No such field presented");
        }
        silverScreenMainPage.logInToPersonalAccount(login , password);
    }

    @Then ("I see {string} message")
    public void iSeeMessage (String message) {
        assert silverScreenMainPage.popUpMessageBlankField.getText().contains(message) :
                String.format("Required message '%s' is not displayed", message);
    }

    @Then ("I see no such user message")
    public void iSeeMessageNoUser () {
        assert silverScreenMainPage.popUpMessageNoUser.getText().contains("Пользователь не найден") : "Required message is not displayed";
    }

    @Then ("I can see Red Carpet Club Level {string} in upper right corner")
    public void iCanSeeRedCarpetClubLevel (String level) {
        assert silverScreenMainPage.myLevel.getText().contains(level) :
                String.format("I can't see %s Red Carpet Level in the upper right corner", level);
    }

    @Then ("I see the list of movie items")
    public void iSeeListOfItems () {
        Driver.setWaiter().until(ExpectedConditions.visibilityOf(silverScreenMainPage.movieListHeader));
        assert silverScreenMainPage.searchResults.size() > 0 : "The list of items isn't displayed";
    }

    @And ("each item name or description contains {string}")
    public void checkEachItemContainsSearchWord (String word) {
        assert silverScreenMainPage.checkItemContent(word) : String.format("The item doesn't contain %s", word);
    }

}
