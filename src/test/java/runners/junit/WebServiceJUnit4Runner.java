package runners.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import steps.junit.WebServiceJUnit4Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({WebServiceJUnit4Test.class})
public class WebServiceJUnit4Runner {

}
