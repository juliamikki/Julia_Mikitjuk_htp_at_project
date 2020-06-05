package application_items.cook_book;

public class Recipe {

    private String recipeName;
    private Ingredient[] ingredientList;
    private String preparationTime;

    public Recipe() { }

    public Recipe(String recipeName, Ingredient[] ingredientList, String preparationTime) {
        this.recipeName = recipeName;
        this.ingredientList = ingredientList;
        this.preparationTime = preparationTime;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Ingredient[] getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(Ingredient[] ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }
}
