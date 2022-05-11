import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String recipeDescription;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private ArrayList<Double> costs;
    private String groupName;
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

    public void setGroupName(String name){
        groupName = name;
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

    public void setCosts(ArrayList<Double> costs) {
        this.costs = costs;
    }

    public double calcCosts(){
        double cost = 0;
        for (int i = 0; i < costs.size(); i++){
            cost += costs.get(i);
        }
        return cost;
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

    public ArrayList<Double> getCosts() {
        return costs;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUserName() {
        return userName;
    }
}
