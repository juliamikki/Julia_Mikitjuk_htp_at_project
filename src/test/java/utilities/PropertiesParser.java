package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {

    public static Properties getProperties(String path) {

        InputStream input;
        Properties prop = new Properties();

        try {
            input = new FileInputStream(path);
            prop.load(input);
        } catch (IOException ex) {
            System.err.println("ERROR: No property file!");
        }

        return prop;
    }
}
