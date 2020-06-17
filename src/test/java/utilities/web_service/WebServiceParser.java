package utilities.web_service;

import application_items.web_service.Response;
import application_items.web_service.SearchQuery;
import application_items.web_service.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebServiceParser {

    private static final Logger LOGGER = LogManager.getLogger(WebServiceParser.class);
    private final static String WS_JSON_PATH = "src/test/resources/testData/wsTestData.json";
    private final static String EXP_RESULT_JSON_PATH = "src/test/resources/testData/wsExpectedResult.json";
    private final static File WS_JSON_File = new File (WS_JSON_PATH);
    private final static File EXP_RESULT_JSON_FILE = new File (EXP_RESULT_JSON_PATH);

    public static List<SearchQuery> parseJacksonQuery() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List <SearchQuery> queries = Arrays.asList(mapper.readValue(WS_JSON_File, SearchQuery[].class));
        LOGGER.debug(">>> Search query is parsed successfully!");
        return queries;
    }

    public static List<User> parseJacksonResponse(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Response response = mapper.readValue(json, Response.class);
        LOGGER.debug(">>> Response from web service is parsed successfully!");
        return response.getData();
    }

    public static List<User> parseJacksonResultFile(int responseOrder) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List <Response> response = Arrays.asList(mapper.readValue(EXP_RESULT_JSON_FILE, Response[].class));
        LOGGER.debug(">>> File with expected results is parsed successfully!");
        return response.get(responseOrder).getData();
    }
}
