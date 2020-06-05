package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import application_items.web_service.Search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataJSONGenerator {

    public void fromGSON() throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Search search = new Search ("", true);
        System.out.println(gson.toJson(search));
        Files.write(Paths.get("src/test/resources/web_service/wsTestData.json"), gson.toJson(search).getBytes());

    }
}
