package steps.junit;

import application_items.web_service.User;
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

    private static MyHttpClient client;
    private static List <SearchQuery> queries;

    @BeforeClass
    public static void getTestData() throws IOException, URISyntaxException {

        client = new MyHttpClient();
        queries = WebServiceParser.parseJacksonQuery();
    }

    @Test
    public void searchAll () throws IOException, URISyntaxException {

        SearchQuery query = queries.get(0);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(0);

        assert actualUsers.equals(expectedUsers) : "Response result for All Users search is not as expected";
    }

    @Test
    public void searchFullNameLong () throws IOException, URISyntaxException {

        SearchQuery query = queries.get(1);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(1);

        assert actualUsers.equals(expectedUsers) : "Response result for Full Long UserName search is not as expected";
    }

    @Test
    public void searchFullNameShort () throws IOException, URISyntaxException {

        SearchQuery query = queries.get(2);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(2);

        assert actualUsers.equals(expectedUsers) : "Response result for Full Long UserName search is not as expected";
    }

    @Test
    public void searchPartialNameLong () throws IOException, URISyntaxException {

        SearchQuery query = queries.get(3);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(3);

        assert actualUsers.equals(expectedUsers) : "Response result for Full Long UserName search is not as expected";
    }

    @Test
    public void searchPartialNameShort () throws IOException, URISyntaxException {

        SearchQuery query = queries.get(4);
        String response = client.searchUser(query);
        List <User> actualUsers = WebServiceParser.parseJacksonResponse(response);
        List <User> expectedUsers = WebServiceParser.parseJacksonResultFile(4);

        assert actualUsers.equals(expectedUsers) : "Response result for Full Long UserName search is not as expected";
    }
}
