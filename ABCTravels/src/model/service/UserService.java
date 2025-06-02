package model.service;

import model.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private List<User> users ;
    private Map<String,Integer> userInvalidLoginAttempt;

    public UserService(List<User> users, Map<String, Integer> userInvalidLoginAttempt) {
        this.users = users;
        this.userInvalidLoginAttempt = userInvalidLoginAttempt;
    }
    public void regiterNewAdmin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nNew Admin User Registration");

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter mobile number: ");
        String mobileNumber = sc.nextLine();

        System.out.print("Enter gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if(isUserExists(email)){
            System.out.println("User Email :"+email+"already exists");
        }
        User newuser = new User( firstName,lastName,mobileNumber,email,gender,password);
        users.add(newuser);
        System.out.println("Registration Successful");

    }
    private boolean isUserExists(String email){
        for(User user:users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public User login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        for(User user:users){
            if(user.getEmail().equals(email)){
                Integer existingCount = userInvalidLoginAttempt.getOrDefault(email,0);
                if(existingCount>=5){
                    System.out.println("User account is locked due to multiple invalid login attempts.");
                    return null;
                }
                if(user.getPassword().equals(password)){
                    System.out.println("\n Login Successful");
                    userInvalidLoginAttempt.put(email, 0); // Reset count after successful login
                    return user;
                }else {
                    userInvalidLoginAttempt.put(email, existingCount + 1);
                    System.out.println("\n Invalid Credentials. Attempt: " + (existingCount + 1) + " for email: " + email);

                }
            }
        }
        System.out.println("No user found with email: " + email);
        return null;
    }
}
