package steps.junit;

import application_items.web_service.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import application_items.web_service.SearchQuery;
import org.junit.runners.MethodSorters;
import utilities.web_service.MyHttpClient;
import utilities.web_service.WebServiceParser;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class WebServiceJUnit4Test {

    private static final Logger LOGGER = LogManager.getLogger(WebServiceJUnit4Test.class);
    private static MyHttpClient client;
    private static List <SearchQuery> queries;

    @BeforeClass
    public static void getTestData() throws IOException {
        client = new MyHttpClient();
        queries = WebServiceParser.parseJacksonQuery();
        LOGGER.info(">>> Test data is prepared");
    }

    @Test
    public void searchAll () throws IOException, URISyntaxException {
        LOGGER.info(">>> Search all users test execution is started!");
        SearchQuery query = queries.get(0);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(0);
        assert actualUsers.equals(expectedUsers) : "Response result for All Users search is not as expected";
        LOGGER.info(">>> Search all users test execution is finished!");
    }

    @Test
    public void searchFullNameLong () throws IOException, URISyntaxException {
        LOGGER.info(">>> Search user by full long name test execution is started!");
        SearchQuery query = queries.get(1);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(1);
        assert actualUsers.equals(expectedUsers) : "Response result for Full Long UserName search is not as expected";
        LOGGER.info(">>> Search user by full long name  test execution is finished!");
    }

    @Test
    public void searchFullNameShort () throws IOException, URISyntaxException {
        LOGGER.info(">>> Search user by full short name test execution is started!");
        SearchQuery query = queries.get(2);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(2);
        assert actualUsers.equals(expectedUsers) : "Response result for Full Short UserName search is not as expected";
        LOGGER.info(">>> Search user by full short name  test execution is finished!");
    }

    @Test
    public void searchPartialNameLong () throws IOException, URISyntaxException {
        LOGGER.info(">>> Search user by partial long name test execution is started!");
        SearchQuery query = queries.get(3);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(3);
        assert actualUsers.equals(expectedUsers) : "Response result for Partial Long UserName search is not as expected";
        LOGGER.info(">>> Search user by partial short long  test execution is finished!");
    }

    @Test
    public void searchPartialNameShort () throws IOException, URISyntaxException {
        LOGGER.info(">>> Search user by partial short name test execution is started!");
        SearchQuery query = queries.get(4);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(4);
        assert actualUsers.equals(expectedUsers) : "Response result for Partial Short UserName search is not as expected";
        LOGGER.info(">>> Search user by partial short name  test execution is finished!");
    }
}
