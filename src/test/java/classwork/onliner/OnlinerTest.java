package classwork.onliner;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static web_driver.Driver.getDriver;
import static web_driver.Driver.maximize;
import static web_driver.Driver.setTimeouts;

public class OnlinerTest {

    private static WebDriver driver;

    @BeforeClass
    public static void startBrowser() {
        driver = getDriver();
        setTimeouts();
        maximize();
    }

    @Test
    public void applePriceOnlinerTest() throws InterruptedException {

        String priceToCompare = "100 BYN";
        String addressSite = "https://www.onliner.by/";
        String CATALOG_CSS = "a[href='https://catalog.onliner.by/']";
        String ELECTRONIC_XPATH = "//li[@data-id='1']";

        //String PHONES_MENU_XPATH = "//div[@class='catalog-navigation-list__aside-title']";
        String PHONES_MENU_XPATH = "//div[contains(text(), 'Мобильные')]";
        String MOBILE = "//div[contains(text(), 'Мобильные')]/following-sibling::div//a[@href='https://catalog.onliner.by/mobile']";


        //String CHOSE_APPLE_XPATH = "//div[@class='schema-filter__facet']/ul//span[text()= 'Apple']/parent::*//input[@value = 'apple']";
        String CHOSE_APPLE_XPATH = "//div[@class='schema-filter__facet']/ul//span[text()= 'Apple']/parent::*//span[@class='i-checkbox']";
        String GOODS_FOUND = "//span[contains(text(),'Найдено')]";

        //String PRICE_FIELD_APPLE_XPATH = "//div[@class='schema-product__group'][1]//div[@class='schema-product__price'][1]/a/span";
        String PRICE_FIELD_APPLE_XPATH = "//div[@class='schema-product__group'][1]//div[@class='schema-product__price']//span";


        driver.get(addressSite);

        WebElement buttonCatalog = driver.findElement(By.cssSelector(CATALOG_CSS));
        buttonCatalog.click();
        WebElement buttonElectronic = driver.findElement(By.xpath(ELECTRONIC_XPATH));
        buttonElectronic.click();

        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement menuPhones = driver.findElement(By.xpath(PHONES_MENU_XPATH));
        WebElement buttonMobile = driver.findElement(By.xpath(MOBILE));
        menuPhones.click();
        buttonMobile.click();

        WebElement checkboxApple = driver.findElement(By.xpath(CHOSE_APPLE_XPATH));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", checkboxApple);
        checkboxApple.click();

        WebElement goodsFound = driver.findElement(By.xpath(GOODS_FOUND));
        wait.until(ExpectedConditions.visibilityOf(goodsFound));

        String priceFirstMobile = driver.findElements(By.xpath(PRICE_FIELD_APPLE_XPATH)).get(0).getText();
        System.out.println(priceFirstMobile);


    }
}
