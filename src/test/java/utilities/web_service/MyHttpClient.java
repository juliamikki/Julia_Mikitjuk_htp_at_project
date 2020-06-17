package utilities.web_service;

import application_items.web_service.SearchQuery;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import classwork.MyJsonGenerator;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyHttpClient {

    private static final Logger LOGGER = LogManager.getLogger(MyHttpClient.class);

    public String searchUser(SearchQuery query) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("http://178.124.206.46:8001/app/ws/");
        HttpPost request = new HttpPost(builder.build());
        request.setEntity(new StringEntity(MyJsonGenerator.fromGSON(query)));
        HttpResponse response = client.execute(request);
        LOGGER.debug(">>> Response from web service is received successfully!");
        return EntityUtils.toString(response.getEntity());
    }

    //classwork:
     public void searchRates() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://www.nbrb.by/api/exrates/currencies/1");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}

