package web_pages.booking;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class TrashMailMainPage extends AbstractPage {

    private final Properties propTrashM = PropertiesParser.getTrashMailProperties();
    private final Properties propYandex = PropertiesParser.getYandexProperties();
    private Actions actions = new Actions(driver);

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
        String url = PropertiesParser.getTrashMailProperties().getProperty("URL");
        driver.get(url);
    }

    public void createNewUser () {
        newUserTab.click();
        userName.clear();
        userName.sendKeys(propTrashM.getProperty("USER"));
        password.sendKeys(propTrashM.getProperty("PWD"));
        confirmPassword.sendKeys(propTrashM.getProperty("PWD"));
        realEmail.sendKeys(propYandex.getProperty("EMAIL"));
        registerButton.click();
    }

    public void createDisposableAddress () {
        quickTab.click();
        numberOfForwards.click();
        actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
                .sendKeys(Keys.ENTER).build().perform();
        createDisposableEmail.click();
        newDisposableEmail.getText();
    }

    public void addDisposableEmailToPropertyFile() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("src/test/resources/booking/trashmail.properties");
        String disposableEmail = newDisposableEmail.getText();
        propTrashM.put("EMAIL", disposableEmail);
        try {
            propTrashM.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
