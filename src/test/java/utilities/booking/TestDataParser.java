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

    private final static String BOOKING_JSON_PATH = "src/test/resources/testData/bookingTestData.json";
    private static File bookingJSONFile = new File (BOOKING_JSON_PATH);

    public static TestData[] parseJackson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TestData [] testData =  mapper.readValue(bookingJSONFile, TestData[].class);
        return testData;
    }

    //classwork:
    public TestData parseGSON() throws FileNotFoundException {
        JsonReader JSONSource = new JsonReader(new FileReader(BOOKING_JSON_PATH));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.fromJson(JSONSource, TestData.class); //parse Json into a Search object
    }
}
