package classwork;

import application_items.cook_book.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyJsonParser {

    private final static String JSON = "src/test/resources/cook_book/icecreamRecipe.json";
    private File file = new File(JSON);

    public void  parseJSON() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj = new JSONObject(input);
        System.out.println(obj.getString("recipeName"));
        System.out.println(obj.getJSONArray("ingredientList").getJSONObject(0).getInt("quantity"));

    }

    public void parseGSON() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(new JsonReader((new FileReader(JSON))), Recipe.class);
        System.out.println(recipe.getRecipeName());
        System.out.println(recipe.getIngredientList()[0].getItemDescription());
        System.out.println(recipe.getIngredientList()[0]);
    }

    public void  parseJackson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipe = mapper.readValue(file, Recipe.class);
        System.out.println(recipe.getPreparationTime());
    }


}
