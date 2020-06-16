package utilities;

public class PathList {

    private static final String BOOKING_PROPERTY_PATH = "src/test/resources/properties/booking.properties";
    private static final String TRASHMAIL_PROPERTY_PATH = "src/test/resources/properties/trashMail.properties";
    private static final String YANDEX_PROPERTY_PATH = "src/test/resources/properties/yandex.properties";
    private static final String SILVERSCREEN_PROPERTY_PATH = "src/test/resources/properties/cinema.properties";

    public static String getBookingPropertyPath() {
        return BOOKING_PROPERTY_PATH;
    }

    public static String getTrashMailPropertyPath() {
        return TRASHMAIL_PROPERTY_PATH;
    }

    public static String getYandexPropertyPath() {
        return YANDEX_PROPERTY_PATH;
    }

    public static String getSilverScreenPropertyPath() {
        return SILVERSCREEN_PROPERTY_PATH;
    }
}
