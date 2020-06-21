package utilities;

import org.openqa.selenium.WebElement;

public class WebElementsParser {

    public static int parseToNumber (WebElement element) {
        return Integer.parseInt(element.getText().replaceAll("[^0-9]+",""));
    }

}
