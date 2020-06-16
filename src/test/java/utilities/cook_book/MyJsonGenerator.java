package utilities.cook_book;

import application_items.web_service.SearchQuery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import application_items.cook_book.Ingredient;
import application_items.cook_book.Recipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyJsonGenerator {

    //classwork:
    public static String fromGSON(Object search) {
        Gson gson = new Gson();
        return gson.toJson(search);
    }

    public void fromGSON() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Recipe recipe = new Recipe("Borsch", new Ingredient[]{}, "120 minutes");
        System.out.println(gson.toJson(recipe));
        Files.write(Paths.get("src/test/resources/cook_book/borschRecipe.json"), gson.toJson(recipe).getBytes());
    }
}
