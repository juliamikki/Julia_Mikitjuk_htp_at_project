package runners.junit;

import steps.junit.BookingJUnit4Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingJUnit4Test.class})

public class BookingJUnit4Runner {

}


