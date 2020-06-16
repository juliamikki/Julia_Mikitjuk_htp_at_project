package runners.сucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        plugin = {"pretty"},
        glue = {"steps.cucumber.booking"},
        features = {"src/test/resources/features/BookingTest.feature"},
        //tags = {"@qa or @prod"},
        snippets = SnippetType.CAMELCASE,
        strict = false)

public class RunBookingHotelTests {
}
