package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.*;
import utilities.UserGenerator;
import web_pages.AbstractPage;

import java.util.Properties;

public class TrashMailMainPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(TrashMailMainPage.class);
    private static final Properties TRASHMAIL_PROP = PropertiesParser.getProperties(PathList.getTrashMailPropertyPath());
    private static final Properties YANDEX_PROP = PropertiesParser.getProperties(PathList.getYandexPropertyPath());
    private final Actions actions = new Actions(driver);

    @FindBy(xpath="//*[@href='#tab-register']")
    private WebElement newUserTab;

    @FindBy(xpath="//*[@id='tab-register']//input[@type='text']")
    private WebElement userName;

    @FindBy(xpath="//*[@id='tab-register']//div[2]/input[@type='password']")
    private WebElement password;

    @FindBy(xpath="//*[@id='tab-register']//div[3]/input[@type='password']")
    private WebElement confirmPassword;

    @FindBy(xpath="//*[@id='tab-register']//input[@type='email']")
    private WebElement realEmail;

    @FindBy(xpath="//*[@id='tab-register']//button")
    private WebElement registerButton;

    @FindBy(xpath="//*[@href='#tab-quick']")
    private WebElement quickTab;

    @FindBy (id = "fe-forward")
    private WebElement realEmailQuick;

    @FindBy (xpath = "//form[@name='tm-login-form']/div[3]//button")
    private WebElement numberOfForwards;

    @FindBy (id = "fe-submit")
    private WebElement createDisposableEmail;

    @FindBy (xpath = "//*[@id='tm-bodyleft-blank-text']/p//b[1]")
    public WebElement newDisposableEmail;

    public TrashMailMainPage (WebDriver driver) {
        super(driver);
    }

    public void createNewUser () {
        newUserTab.click();
        userName.clear();
        String newUser = UserGenerator.generateUniqueUserName();
        userName.sendKeys(newUser);
        PropertiesParser.addProperty(PathList.getTrashMailPropertyPath(), "USER", newUser);
        password.sendKeys(TRASHMAIL_PROP.getProperty("PWD"));
        confirmPassword.sendKeys(TRASHMAIL_PROP.getProperty("PWD"));
        realEmail.sendKeys(YANDEX_PROP.getProperty("EMAIL"));
        registerButton.click();
        LOGGER.debug(">>> New TrashMail user is created successfully!");
    }

    public void createDisposableAddress () {

        quickTab.click();
        realEmailQuick.clear();
        realEmailQuick.sendKeys(YANDEX_PROP.getProperty("EMAIL"));
        numberOfForwards.click();
        actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ENTER).build().perform();
        createDisposableEmail.click();
        PropertiesParser.addProperty(PathList.getTrashMailPropertyPath(), "EMAIL", newDisposableEmail.getText());
        LOGGER.debug(">>> New disposable email address is created successfully!");
    }

}
