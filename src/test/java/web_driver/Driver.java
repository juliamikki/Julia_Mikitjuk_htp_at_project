package web_driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;
    //public static WebDriver TheadLocal <WebDriver> driver = new ThreadLocal<>(); //для создания многопоточности

    private Driver() throws IllegalAccessException {
        throw new IllegalAccessException("Driver is utility class. Impossible to create an instance");
    }

    public static void initDriver(Config config) {
        if (driver == null) {
            driver = DriverManager.getDriver(config);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver(Config.CHROME);
        }
        return driver;
    }

    public static void maximize() {
        driver.manage().window().maximize();
    }

    public static void setTimeouts() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    public static void removeTimeouts() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(0, TimeUnit.SECONDS);
    }

    public static Wait <WebDriver> setWaiter() {
        /*removeTimeouts();
        System.out.println("timeouts are removed");*/
        return new FluentWait <>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

    }

    public static void executeJS (String script, WebElement ell) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script,ell);

    }

    public static void switchToNewTab () {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        allWindowHandles.remove(parentWindow);

        Iterator<String> ite = allWindowHandles.iterator();
        driver.switchTo().window(ite.next());
    }

    public static void destroy() {
        driver.close();
        driver.quit();
    }

}
