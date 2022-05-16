import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Recipe> thisUserRecipes;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        thisUserRecipes = new ArrayList<Recipe>();
    }

    //getters

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Recipe> getThisUserRecipes() {
        return thisUserRecipes;
    }

    public void addToUserRecipes(Recipe r){
        thisUserRecipes.add(r);
    }
}
