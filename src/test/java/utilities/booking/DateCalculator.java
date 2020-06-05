package utilities.booking;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

    private static String checkInDate;
    private static String checkOutDate;

    public static String getCheckInDate() {
        return checkInDate;
    }

    public static void setCheckInDate(String checkInDate) {
        DateCalculator.checkInDate = checkInDate;
    }

    public static String getCheckOutDate() {
        return checkOutDate;
    }

    public static void setCheckOutDate(String checkOutDate) {
        DateCalculator.checkOutDate = checkOutDate;
    }

    public static void defineTravelDates (int arrivalInXDays, int durationOfStay) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, arrivalInXDays);
        Date checkIn = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, durationOfStay);
        Date checkOut = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        checkInDate = formatter.format(checkIn);
        checkOutDate = formatter.format(checkOut);
    }

}
