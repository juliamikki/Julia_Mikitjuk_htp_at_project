package web_driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {

    public static WebDriver getDriver(Config config) {
        switch (config) {
            case CHROME:
                return getChromeDriver();
            case FF:
                return getFirefoxDriver();
            default:
                throw null;
        }
    }

    public static WebDriver getChromeDriver() {

        String pathToDriver= "src/test/resources/webdriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        return new ChromeDriver();
    }

    public static WebDriver getFirefoxDriver() {

        String pathToDriver= "src/test/resources/webdriver/firefoxdriver.exe";
        System.setProperty("webdriver.firefox.driver", pathToDriver);
        System.setProperty("webdriver.firefox.silentOutput", "true");
        return new FirefoxDriver();
    }
}
