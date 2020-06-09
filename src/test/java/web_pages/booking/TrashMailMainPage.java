package web_pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.booking.PropertiesParser;
import web_pages.AbstractPage;

import java.util.Properties;

public class TrashMailMainPage extends AbstractPage {

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

    public TrashMailMainPage (WebDriver driver) {
        super(driver);
    }

    public void navigateToTrashMail () {
        String url = PropertiesParser.getTrashMailProperties().getProperty("URL");
        driver.get(url);
    }

    public void createNewUser () {

        Properties prop = PropertiesParser.getTrashMailProperties();
        newUserTab.click();
        userName.clear();
        userName.sendKeys(prop.getProperty("USER"));
        password.sendKeys(prop.getProperty("PWD"));
        confirmPassword.sendKeys(prop.getProperty("PWD"));
        realEmail.sendKeys(prop.getProperty("EMAIL"));
        registerButton.click();
    }


}
