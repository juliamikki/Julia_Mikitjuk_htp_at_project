package utilities.booking;

import application_items.booking.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestDataParser {

    private final static String JSONPath = "src/test/resources/booking/bookingTestData.json";
    private static File JSONFile = new File (JSONPath);

    public static TestData[] parseJackson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestData [] testData =  mapper.readValue(JSONFile, TestData[].class);
        return testData;
    }



    public TestData parseGSON() throws FileNotFoundException {

        JsonReader JSONSource = new JsonReader(new FileReader(JSONPath));

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //or just Gson gson = new Gson();

        //parse Json into a Search object:
        return gson.fromJson(JSONSource, TestData.class);

        //System.out.println(search.getSearchData()[2].getDestination());
    }
}
