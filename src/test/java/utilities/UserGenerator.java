package utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserGenerator {

    private static final Logger LOGGER = LogManager.getLogger(UserGenerator.class);
    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateUniqueUserName () {
        int count = 10;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        LOGGER.debug(">>> Unique username is generated successfully!");
        return builder.toString();
    }

}
