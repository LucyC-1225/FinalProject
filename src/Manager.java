import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Manager {
    private ArrayList<User> allUsers;

    public Manager(){
        allUsers = new ArrayList<User>();
    }
    public void addUser(User user){
        allUsers.add(user);
    }
    public User findUser(String username){
        for (int i = 0; i < allUsers.size(); i++){
            if (allUsers.get(i).getUserName().equals(username)){
                return allUsers.get(i);
            }
        }
        return null;
    }
    public boolean userNameExists(String username){
        for (int i = 0; i < allUsers.size(); i++){
            if (allUsers.get(i).getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }
    public boolean correctPassword(String username, String password){
        User u = findUser(username);
        if (u.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public boolean createUserAccount(String username, String password){
        boolean userAlreadyExists = false;
        for (int i = 0; i < allUsers.size(); i++){
            if (allUsers.get(i).getUserName().equals(username)){
                userAlreadyExists = true;
            }
        }
        if (userAlreadyExists){
            return false;
        }
        User u = new User(username, password);
        allUsers.add(u);
        try {
            File f = new File("src/" + u.getUserName() + ".txt");
            f.createNewFile();
        } catch (IOException e){
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
        return true;
    }

    public void createRecipe(User user, String recipeName, String recipeDescription, ArrayList<String> ingredients, ArrayList<String> instructions){
        Recipe r = new Recipe(user.getUserName(), recipeName);
        r.setRecipeDescription(recipeDescription);
        r.setIngredients(ingredients);
        r.setInstructions(instructions);
        user.addToUserRecipes(r);
       // r.setCosts(costs);
    }

    public void save(User user){
        try {
            FileWriter fw = new FileWriter("src/" + user.getUserName() + ".txt");
            fw.write(user.getUserName() + "\n");
            fw.write(user.getPassword() + "\n");
            ArrayList<Recipe> recipes = user.getThisUserRecipes();
            for (int i = 0; i < recipes.size(); i++){
                fw.write(recipes.get(i).getRecipeName() + "\n");
                //writing all the ingredients
                for (int j = 0; j < recipes.get(i).getIngredients().size() - 1; j++){
                    fw.write(recipes.get(i).getIngredients().get(j) + "|");
                }
                fw.write(recipes.get(i).getIngredients().get(recipes.get(i).getIngredients().size() - 1) + "\n");
                //writing all the instructions
                for (int j = 0; j < recipes.get(i).getInstructions().size() - 1; j++){
                    fw.write(recipes.get(i).getInstructions().get(j) + "|");
                }
                //fw.write(recipes.get(i).getInstructions().get(recipes.get(i).getInstructions().size() - 1) + "\n");
                //writing all the costs
                /*for (int j = 0; j < recipes.get(i).getCosts().size() - 1; j++){
                    fw.write(recipes.get(i).getCosts().get(j) + "|");
                }
                fw.write(recipes.get(i).getCosts().get(recipes.get(i).getCosts().size() - 1) + "\n");*/

            }
        } catch (IOException e){
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
    }
}
