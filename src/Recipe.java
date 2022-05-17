import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String recipeDescription;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private String userName;

    public Recipe(String userName, String recipeName){
        this.userName = userName;
        this.recipeName = recipeName;
    }

    public void setRecipeDescription(String description){
        recipeDescription = description;
    }

    public void addIngredients(String ingredient){
        ingredients.add(ingredient);
    }

    public void addInstructions(String instruction){
        instructions.add(instruction);
    }

    public void setUserName(String name){
        userName = name;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public void setRecipeName(String name){
        recipeName = name;
    }

    //getters

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public String getUserName() {
        return userName;
    }

    public String toString(){
        String str = "";
        str += "Recipe name: " + recipeName + "\n";
        str += "Recipe description: " + recipeDescription + "\n";
        str += "Ingredients: " + "\n";
        for (int i = 0; i < ingredients.size(); i++){
            str += i + 1 + ") " + ingredients.get(i) + "\n";
        }
        str += "Instructions: " + "\n";
        for (int i = 0; i < instructions.size(); i++){
            str += i + 1 + ") " + instructions.get(i) + "\n";
        }
        return str;
    }
}
