package web_pages.booking;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class TrashMailMainPage extends AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(TrashMailMainPage.class);
    private final Properties propTrashM = PropertiesParser.getProperties(PathList.getTrashMailPropertyPath());
    private final Properties propYandex = PropertiesParser.getProperties(PathList.getYandexPropertyPath());
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
    private WebElement newDisposableEmail;

    public TrashMailMainPage (WebDriver driver) {
        super(driver);
    }

    public void navigateToTrashMail () {
        LOGGER.debug(">>> Navigate to Trashmail.com");
        String url = propTrashM.getProperty("URL");
        driver.get(url);
    }

    public void createNewUser () {
        LOGGER.debug(">>> Create new TrashMail user");
        newUserTab.click();
        userName.clear();
        userName.sendKeys(propTrashM.getProperty("USER"));
        password.sendKeys(propTrashM.getProperty("PWD"));
        confirmPassword.sendKeys(propTrashM.getProperty("PWD"));
        realEmail.sendKeys(propYandex.getProperty("EMAIL"));
        registerButton.click();
    }

    public void createDisposableAddress () {
        LOGGER.debug(">>> Create new disposable email address");
        quickTab.click();
        realEmailQuick.clear();
        realEmailQuick.sendKeys(propYandex.getProperty("EMAIL"));
        numberOfForwards.click();
        actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ENTER).build().perform();
        createDisposableEmail.click();
    }

    public void addDisposableEmailToPropertyFile() throws FileNotFoundException {
        LOGGER.debug(">>> Add new disposable email address to property file");
        OutputStream out = new FileOutputStream(PathList.getTrashMailPropertyPath());
        String disposableEmail = newDisposableEmail.getText();
        propTrashM.put("EMAIL", disposableEmail);
        try {
            propTrashM.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
