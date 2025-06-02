import model.User;
import model.service.UserService;

import java.io.*;
import java.util.*;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static Map<String, Integer> userInvalidLoginAttempt = new HashMap<>();
    private static UserService userService = new UserService(users, userInvalidLoginAttempt);
    public static void main(String[] args) {
        if(displayCompanyLogo()){
            showMenuOptions();
        }else {
            System.out.println("Failed to load company logo. Exiting...");
        }
    }
    private static boolean displayCompanyLogo() {
        try {
            File file = new File("Logo.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter("Logo.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("WELCOME TO ABC TRAVELS");
            bw.close();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            System.out.println(br.readLine());
            return true;
        } catch (IOException e) {
            System.err.println("Error reading company logo file: " + e.getMessage());
            return false;
        }
    }
    private static void showMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean running = true;

        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("1. New Admin User Registration");
            System.out.println("2. Login");
            System.out.println("3. Plan journey");
            System.out.println("4. Reschedule booking date");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    userService.regiterNewAdmin();
                    break;
                case 2:
                    userService.login();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a correct option.");
                    break;
            }
        }


        scanner.close();  // Close the scanner when we're done with it
    }
}