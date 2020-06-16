package runners.сucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        plugin = {"pretty"},                                                    //красиво печатает
        glue = {"steps"},                                                       //последовательность пакетов с тестами
        features = {"src/test/resources/features/UsersApiTest.feature"},        //путь к файлам с фичами
        //tags = {"@qa or @prod"},                                              //
        snippets = SnippetType.CAMELCASE,                                       //
        strict = false)                                                         //

public class RunAPITests {
}
