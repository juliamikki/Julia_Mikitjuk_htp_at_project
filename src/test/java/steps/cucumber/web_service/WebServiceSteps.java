package steps.cucumber.web_service;

import application_items.web_service.SearchQuery;
import application_items.web_service.User;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import utilities.web_service.MyHttpClient;
import utilities.web_service.WebServiceParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceSteps {

    private static final String MSG = "Response result for %s search is not as expected";

    private static MyHttpClient client;
    private static List<SearchQuery> queries;
    private static SearchQuery query;
    private static String response;
    private static List <User> actualUsers;
    private static List <User> expectedUsers;

    @Before
    public static void before() throws IOException, URISyntaxException {
        client = new MyHttpClient();
        queries = WebServiceParser.parseJacksonQuery();
    }

    @Given ("I start execution with {int} test data order")
    public void iStartExecutionWithTestData (int queryOrder) {
         query = queries.get(queryOrder);
    }

    @When("I parse response from web service")
    public void iParseResponseFromWS () throws IOException, URISyntaxException {
        response = client.searchUser(query);
        actualUsers = WebServiceParser.parseJacksonResponse(response);
    }

    @And("I parse file with expected result and get {int} response order")
    public void iParseExpectedResultFile (int responseOrder) throws IOException {
        expectedUsers = WebServiceParser.parseJacksonResultFile(responseOrder);
    }

    @Then ("I verify expected result equals actual result for {string} test")
    public void iVerifyResultsAreEqual (String testType) {
        assert actualUsers.equals(expectedUsers) : String.format(MSG,testType);
    }


}
