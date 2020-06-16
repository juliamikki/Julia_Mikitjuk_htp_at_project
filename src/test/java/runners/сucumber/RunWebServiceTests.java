package runners.сucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        plugin = {"pretty"},
        glue = {"steps.cucumber.web_service"},
        features = {"src/test/resources/features/WebServiceTest.feature"},
        //tags = {"@qa or @prod"},
        snippets = SnippetType.CAMELCASE,
        strict = false)

public class RunWebServiceTests {
}
