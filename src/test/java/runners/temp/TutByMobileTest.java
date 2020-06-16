package runners.temp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TutByMobileTest {

    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName","My");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("browserName", "chrome");

        //appium всегда на этом урле слушает запрос http://0.0.0.0:4723/wd/hub
        WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        driver.get("https://tut.by");
        System.out.println("Job is done");
        //Thread.sleep(5000);
        driver.quit();

    }
}
