package steps;

import org.junit.BeforeClass;
import org.junit.Test;
import application_items.web_service.Search;
import utilities.MyHttpClient;
import utilities.TestDataJSONGenerator;

import java.io.IOException;
import java.net.URISyntaxException;


public class WebServiceJUnit4Test {

    private static MyHttpClient client = new MyHttpClient();
    private static TestDataJSONGenerator generator;

    @BeforeClass
    public static void getTestData() throws IOException {
        generator = new TestDataJSONGenerator();
        generator.fromGSON();
    }

    @Test
    public void searchAll () throws IOException, URISyntaxException {

    Search search = new Search("", true);
    client.searchUser(search);

    }

    @Test
    public void searchFullNameShort () throws IOException, URISyntaxException {

        Search search = new Search("a", true);
        client.searchUser(search);

    }

    @Test
    public void searchPartialNameShort () throws IOException, URISyntaxException {

        Search search = new Search("a", false);
        client.searchUser(search);

    }

    @Test
    public void searchFullNameLong () throws IOException, URISyntaxException {

        Search search = new Search("berta", true);
        client.searchUser(search);
    }



    @Test
    public void searchPartialNameLong () throws IOException, URISyntaxException {

        Search search = new Search("sim", false);
        client.searchUser(search);

    }
}
