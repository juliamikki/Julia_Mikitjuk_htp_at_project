package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        plugin = {"pretty"},
        glue = {"steps.cucumber.cinema"},
        features = {"src/test/resources/features/CinemaTest.feature"},
        tags = {"@qa"},
        snippets = SnippetType.CAMELCASE,
        strict = false)

public class RunCinemaTests {
}
