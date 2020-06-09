package utilities.booking;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {


    public static Properties getBookingProperties() {

        InputStream input;
        Properties prop = new Properties();

        try {
            input = new FileInputStream("src/test/resources/booking/booking.properties");
            prop.load(input);
        } catch (IOException ex) {
            System.err.println("ERROR: No booking.properties file!");
        }
        return prop;
    }

    public static Properties getTrashMailProperties() {

        InputStream input;
        Properties prop = new Properties();

        try {
            input = new FileInputStream("src/test/resources/booking/trashMail.properties");
            prop.load(input);
        } catch (IOException ex) {
            System.err.println("ERROR: No trashMail.properties file!");
        }
        return prop;
    }

}