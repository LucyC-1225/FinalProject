import java.io.*;
import java.util.ArrayList;

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
            /*File f = new File("src/" + u.getUserName() + ".txt");
            f.createNewFile();*/
            FileOutputStream fos = new FileOutputStream("src/" + u.getUserName() + ".txt");
            fos.flush();
            fos.close();
        } catch (IOException e){
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
        save(u);
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
    public Recipe findRecipe(User user, String recipeName){
        ArrayList<Recipe> thisUserRecipes = user.getThisUserRecipes();
        Recipe r;
        for (int i = 0; i < thisUserRecipes.size(); i++){
            if (thisUserRecipes.get(i).getRecipeName().equals(recipeName)){
                r = thisUserRecipes.get(i);
                return r;
            }
        }
        return null;
    }
    public boolean deleteRecipe(User user, String recipeName){
        Recipe r = findRecipe(user, recipeName);
        if (r == null){
            return false;
        } else {
            int index = -1;
            for (int i = 0; i < user.getThisUserRecipes().size(); i++){
                if (user.getThisUserRecipes().get(i).getRecipeName().equals(recipeName)){
                    r = user.getThisUserRecipes().get(i);
                    index = i;
                }
            }
            user.getThisUserRecipes().remove(index);
        }
        return true;
    }
    public void save(User user){
        try {
            FileOutputStream fos = new FileOutputStream("src/" + user.getUserName() + ".txt");
            String username = user.getUserName() + "\n";
            fos.write(username.getBytes());
            String password = user.getPassword() + "\n";
            fos.write(password.getBytes());
            ArrayList<Recipe> recipes = user.getThisUserRecipes();
            for (int i = 0; i < recipes.size(); i++) {
                //writing recipe name
                String data = recipes.get(i).getRecipeName() + "\n";
                fos.write(data.getBytes());
                //writing recipe description
                String description = recipes.get(i).getRecipeDescription() + "\n";
                fos.write(description.getBytes());
                //writing all the ingredients
                for (int j = 0; j < recipes.get(i).getIngredients().size() - 1; j++) {
                    String data2 = recipes.get(i).getIngredients().get(j) + "|";
                    fos.write(data2.getBytes());
                }
                String data3 = recipes.get(i).getIngredients().get(recipes.get(i).getIngredients().size() - 1) + "\n";
                fos.write(data3.getBytes());
                //writing all the instructions
                for (int j = 0; j < recipes.get(i).getInstructions().size() - 1; j++) {
                    String data4 = recipes.get(i).getInstructions().get(j) + "|";
                    fos.write(data4.getBytes());
                }
                String data5 = recipes.get(i).getInstructions().get(recipes.get(i).getInstructions().size() - 1) + "\n";
                fos.write(data5.getBytes());
            /*
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
                }*/

                //fw.write(recipes.get(i).getInstructions().get(recipes.get(i).getInstructions().size() - 1) + "\n");
                //writing all the costs
                /*for (int j = 0; j < recipes.get(i).getCosts().size() - 1; j++){
                    fw.write(recipes.get(i).getCosts().get(j) + "|");
                }
                fw.write(recipes.get(i).getCosts().get(recipes.get(i).getCosts().size() - 1) + "\n");*/
            }
            } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void save(){
        FileOutputStream f = null;
        try {
            f = new FileOutputStream("src/allUsers.text");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allUsers.size(); i++){
            String name = allUsers.get(i).getUserName() + "\n";
            try {
                f.write(name.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieve(){
        // read the allUsers.text file and for each line, find the txt file that is associated with that username
        // create the User object by reading the first two lines of the username.txt file
        // for every 4 lines of the username.txt file, there is one recipe. Create and set the recipe information. Repeat this until the end of the username.txt file
    }
}
