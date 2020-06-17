package utilities;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.Properties;

public class PropertiesParser {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesParser.class);

    public static Properties getProperties(String path) {
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream(path);
            prop.load(input);
        } catch (IOException ex) {
            LOGGER.error("ERROR: No property file!");
        }
        return prop;
    }

    public static void addProperty(String path, String property, String value) {

        try {
            PropertiesConfiguration properties = new PropertiesConfiguration(path);
            properties.setProperty(property, value);
            properties.save();
            LOGGER.debug(">>> Property " + property + " at " + path + " is updated successfully!");
        } catch (ConfigurationException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
