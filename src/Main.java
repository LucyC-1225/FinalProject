import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Manager m = new Manager();
        boolean exit = false;
        while (!exit){
            System.out.println("Welcome to recipe manager!");
            System.out.println("1. Log in");
            System.out.println("2. Create an account");
            System.out.println("3. Exit program");
            System.out.print("Input: ");
            int input = sc.nextInt();
            //user logs in
            if (input == 1){
                //verifying username and password
                sc.nextLine();
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                if (m.userNameExists(username)){
                    if (m.correctPassword(username, password)){ //sucessful log in
                        User user = m.findUser(username); //this is the user currently making changes
                        boolean loggedOut = false;
                        while (!loggedOut){
                            //display options
                            System.out.println("------------------------------------------------");
                            System.out.println("What do you want to do? Note that you must log out or else your changes will not save");
                            System.out.println("1. Create a recipe");
                            System.out.println("2. Delete a recipe");
                            System.out.println("3. View recipe");
                            System.out.println("4. Log out");
                            System.out.println("5. Delete account");
                            System.out.print("Input: ");
                            int choice = sc.nextInt();
                            if (choice == 1){
                                sc.nextLine();
                                System.out.print("Enter the recipe name: ");
                                String recipeName = sc.nextLine();
                                System.out.print("Enter the recipe description: ");
                                String description = sc.nextLine();
                                System.out.println("Enter each ingredient and press enter. When you have entered all the ingredients, type -1");
                                //the user will keep entering the ingredients until they are done
                                boolean done = false;
                                ArrayList<String> ingredients = new ArrayList<String>();
                                while (!done){
                                    String ingredient = sc.nextLine();
                                    if (ingredient.equals("-1")){
                                        done = true;
                                    } else if (ingredient != null){
                                        ingredients.add(ingredient);
                                    }
                                }
                                //ingredients.remove(ingredients.size() - 1);
                                System.out.println("Enter each step of the procedure and press enter. When you have entered all the steps, type -1");
                                done = false;
                                ArrayList<String> steps = new ArrayList<String>();
                                while (!done){
                                    String step = sc.nextLine();
                                    if (step.equals("-1")){
                                        done = true;
                                    } else if (step != null){
                                        steps.add(step);
                                    }
                                }
                                //steps.remove(steps.size() - 1);
                                m.createRecipe(user, recipeName, description, ingredients, steps);
                            } else if (choice == 2) {
                                sc.nextLine();
                                System.out.print("Enter the name of the recipe you want to delete: ");
                                String recipeName = sc.nextLine();
                                Recipe r = m.findRecipe(user, recipeName);
                                boolean successful = m.deleteRecipe(user, recipeName);
                                if (successful){
                                    System.out.println("recipe deleted");
                                } else {
                                    System.out.println("recipe deleted unsuccessfully");
                                }
                            } else if (choice == 3) {
                                sc.nextLine();
                                System.out.print("Enter the name of the recipe you want to find: ");
                                String recipeName = sc.nextLine();
                                Recipe r = m.findRecipe(user, recipeName);
                                if (r == null){
                                    System.out.println("This recipe does not exist");
                                } else {
                                    System.out.println();
                                    System.out.println(r);
                                    System.out.println();
                                    System.out.println("press enter when you're done viewing");
                                    if (sc.nextLine() != null){

                                    }
                                }
                            } else if (choice == 4){
                                loggedOut = true;
                                m.save(user);
                            }
                        }
                    } else {
                        System.out.println("Password incorrect");
                    }
                } else {
                    System.out.println("Username does not exist");
                }
                System.out.println("------------------------------------------------");
            } else if (input == 2){ //user creates a new account
                sc.nextLine();
                System.out.print("Create an username: ");
                String userName = sc.nextLine();
                System.out.print("Create a password: ");
                String password = sc.nextLine();
                if (m.createUserAccount(userName, password)){
                    System.out.println("Account creation sucessful. Please now log in.");
                    m.save();
                } else {
                    System.out.println("Username already exists");
                }
                System.out.println("------------------------------------------------");
            } else if (input == 3){
                exit = true;
            }
        }
    }
}
