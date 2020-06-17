package classwork;


import application_items.web_service.SearchQuery;
import utilities.web_service.MyHttpClient;
import utilities.booking.TestDataParser;

import java.io.IOException;
import java.net.URISyntaxException;

public class CookBookParserRunner {
    public static void main(String[] args) throws IOException, URISyntaxException {

        MyJsonParser jsonParser = new MyJsonParser();
        MyJsonGenerator generator = new MyJsonGenerator();

        jsonParser.parseJSON();
        jsonParser.parseGSON();
        jsonParser.parseJackson();
        generator.fromGSON();

        TestDataParser parser = new TestDataParser();
        parser.parseGSON();

        MyHttpClient client = new MyHttpClient();
        client.searchRates();

        SearchQuery search = new SearchQuery("berta", true);
        client.searchUser(search);
    }
}
