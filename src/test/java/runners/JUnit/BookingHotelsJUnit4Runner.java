package runners.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import steps.junit.BookingHotelsJUnit4Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingHotelsJUnit4Test.class})

public class BookingHotelsJUnit4Runner {
}


