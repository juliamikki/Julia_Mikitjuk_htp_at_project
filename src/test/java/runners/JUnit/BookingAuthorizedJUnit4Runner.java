package runners.junit;

import steps.junit.BookingAuthorizedJUnit4Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingAuthorizedJUnit4Test.class})

public class BookingAuthorizedJUnit4Runner {
}
