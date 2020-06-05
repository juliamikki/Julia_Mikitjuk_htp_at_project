package utilities;

import org.apache.http.client.methods.HttpGet;
import utilities.cook_book.MyJsonGenerator;
import application_items.web_service.Search;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyHttpClient {



    public void searchUser(Search search) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("http://178.124.206.46:8001/app/ws/");
        //builder.setParameter()
        HttpPost request = new HttpPost(builder.build());
        request.setEntity(new StringEntity(MyJsonGenerator.fromGSON(search)));
        HttpResponse response = client.execute(request);
       // System.out.println(EntityUtils.toString(response.getEntity()));
        //json - response.getEntity();
        //return


    }

     public void searchRates() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://www.nbrb.by/api/exrates/currencies/1");
        //builder.setParameter()
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));

    }

}

