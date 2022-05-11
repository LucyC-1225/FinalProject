import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Manager m = new Manager();
        System.out.println("Welcome to recipe manager!");
        System.out.println("1. Log in");
        System.out.println("2. Create an account");
        System.out.print("Input: ");
        int input = sc.nextInt();
        //user logs in
        if (input == 1){

        } else if (input == 2){ //user creates a new account
            System.out.print("Create an username: ");
            String userName = sc.nextLine();
            System.out.print("Create a password: ");
            String password = sc.nextLine();
            User u = new User(userName, password);
            m.addUser(u);
        }
    }
}
