import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

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
    public void removeUser(User user) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (user.getUserName().equals(allUsers.get(i).getUserName())){
                allUsers.remove(i);
                i--;
            }
        }
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
    public String getAllRecipeNames(User user) {
        ArrayList<String> allRecipenames = new ArrayList<String>();
        String str = "";
        for (int i = 0; i < user.getThisUserRecipes().size(); i++) {
            str += i + 1 + ". " + user.getThisUserRecipes().get(i).getRecipeName() + "\n";
        }
        return str;
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

    public void editRecipeName(User user, String currentRecipeName, String newName){
        Recipe r = findRecipe(user, currentRecipeName);
        r.setRecipeName(newName);
    }

    public void editRecipeDescription(User user, String recipeName, String newName){
        Recipe r = findRecipe(user, recipeName);
        r.setRecipeDescription(newName);
    }
    public String getCurrentInstructions(User user, String recipeName) {
        Recipe r = findRecipe(user, recipeName);
        ArrayList<String> recipeInstructions = r.getInstructions();
        String str = "";
        for (int i = 0; i < recipeInstructions.size(); i++) {
            str += i + 1 + ". " + recipeInstructions.get(i) + "\n";
        }
        return str;
    }
    public void addInstruction(User user, String recipeName, String newInstruction) {
        Recipe r = findRecipe(user, recipeName);
        r.addInstructions(newInstruction);
    }
    public void deleteInstruction(User user, String recipeName, int index) {
        Recipe r = findRecipe(user, recipeName);
        r.getInstructions().remove(index);
    }
    public void editInstruction(User user, String recipeName, int index, String modifiedInstruction) {
        Recipe r = findRecipe(user, recipeName);
        r.getInstructions().set(index, modifiedInstruction);
    }
    public String getCurrentIngredients(User user, String recipeName) {
        Recipe r = findRecipe(user, recipeName);
        ArrayList<String> recipeIngredients = r.getIngredients();
        String str = "";
        for (int i = 0; i < recipeIngredients.size(); i++) {
            str += i + 1 + ". " + recipeIngredients.get(i) + "\n";
        }
        return str;
    }
    public void addIngredient(User user, String recipeName, String newIngredient) {
        Recipe r = findRecipe(user, recipeName);
        r.addIngredients(newIngredient);
    }
    public void deleteIngredient(User user, String recipeName, int index) {
        Recipe r = findRecipe(user, recipeName);
        r.getIngredients().remove(index);
    }
    public void editIngredient(User user, String recipeName, int index, String modifiedIngredient) {
        Recipe r = findRecipe(user, recipeName);
        r.getIngredients().set(index, modifiedIngredient);
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
                String name = recipes.get(i).getRecipeName() + "\n";
                fos.write(name.getBytes());
                //writing recipe description
                String description = recipes.get(i).getRecipeDescription() + "\n";
                fos.write(description.getBytes());
                //writing all the ingredients
                for (int j = 0; j < recipes.get(i).getIngredients().size() - 1; j++) {
                    String ingredient = recipes.get(i).getIngredients().get(j) + "|";
                    fos.write(ingredient.getBytes());
                }
                String lastIngredient = recipes.get(i).getIngredients().get(recipes.get(i).getIngredients().size() - 1) + "\n";
                fos.write(lastIngredient.getBytes());
                //writing all the instructions
                for (int j = 0; j < recipes.get(i).getInstructions().size() - 1; j++) {
                    String instruction = recipes.get(i).getInstructions().get(j) + "|";
                    fos.write(instruction.getBytes());
                }
                String lastInstruction = recipes.get(i).getInstructions().get(recipes.get(i).getInstructions().size() - 1) + "\n";
                fos.write(lastInstruction.getBytes());
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
        File file = new File("src/allUsers.text");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //retrieves all usernames ever created
        ArrayList<String> usernames = new ArrayList<String>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            usernames.add(str);
        }
        //for every username, there is an account with recipes
        for (int i = 0; i < usernames.size(); i++){
            String username = usernames.get(i);
            File f = new File("src/" + username + ".txt");
            Scanner sc2 = null;
            try {
                sc2 = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sc2.nextLine();
            String password = sc2.nextLine();
            //creating user
            User u = new User(username, password);
            allUsers.add(u);
            //every 4 lines is a recipe
            while (sc2.hasNextLine()){
                String recipeName = sc2.nextLine();
                String recipeDescription = sc2.nextLine();
                String ingredients = sc2.nextLine();
                String instructions = sc2.nextLine();
                //turning ingredients into an arrayList
                ArrayList<String> arrIngredients = convertToArrayList(ingredients);
                ArrayList<String> arrInstructions = convertToArrayList(instructions);
                Recipe r = new Recipe(username, recipeName);
                r.setRecipeDescription(recipeDescription);
                r.setIngredients(arrIngredients);
                r.setInstructions(arrInstructions);
                u.addToUserRecipes(r);
            }
            sc2.close();
        }
        sc.close();
    }
    private ArrayList<String> convertToArrayList(String str){
        ArrayList<String> arr = new ArrayList<String>();
        int index = str.indexOf("|");
        while (index != -1){
            arr.add(str.substring(0, index));
            str = str.substring(index + 1);
            index = str.indexOf("|");
        }
        arr.add(str);
        return arr;
    }
    public void deleteAccount(User user) {
        File file = new File("src/" + user.getUserName() + ".txt");
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
