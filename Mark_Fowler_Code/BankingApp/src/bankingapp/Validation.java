
package bankingapp;

import java.util.Scanner;

public class Validation {
    
    static Scanner sc = new Scanner(System.in);
    
    public static String[] createNewAccountPrompt() {
        
        System.out.println("\n  Create New Account selected.");
 
        // New account display heading
        System.out.println("\n  Please enter the following:  ");
        
        // First and last name input and validation
        String firstNamePrompt = "  First Name ->  ";
        String firstName = enterAndValidateName(firstNamePrompt);
        String lastNamePrompt = "  Last Name  ->  ";
        String lastName = enterAndValidateName(lastNamePrompt);
        
        // Username input and validation
        String userName = enterAndValidateUsername();
        
        // Password input and validation
        String password = enterAndValidatePassword();
        
        String[] acctFields = {firstName, lastName, userName, password};
        return acctFields;
        
    } // End createNewAccountPrompt();
    
     public static String enterAndValidateName(String fieldName) {
        // Get name from user
        System.out.print("  " + fieldName + "  ");
        String name = sc.next();
        sc.nextLine();

        for (int i = 0; i < name.length(); i++) {
            if (name.substring(i, i + 1).matches("[^A-Za-z]")) {
            System.out.println("\n  Error ! Please only enter letters.");
            enterAndValidateName(fieldName);
            }
        }
        return name;
    } // End enterAndValidateName
    
     public static String enterAndValidateUsername() {
        
         boolean valid = false;
         String userN = "";
         
         while(!valid) {
         
            System.out.println("\n    Your Username should be between 8-20 characters "
               + "long, using only numbers and letters.");
            System.out.print("    Enter a Username ->  ");
            String un = sc.next();
            sc.nextLine();
        
            // Check username length
            if (un.length() < 8 || un.length() > 20) {
                System.out.println("\n    Error ! Invalid Username Length or "
                        + "Invalid Character entered.");

                un = "";
                continue;
                //enterAndValidateUsername();
            }
        
            // Check for disallowed characters
            for (int i = 0; i < un.length(); i++) {
                if (un.substring(i, i + 1).matches("[^0-9^A-Z^a-z]")) {
                    System.out.println("\n    Error ! Invalid Username Character");

                    un = "";
                    continue;
                    //enterAndValidateUsername();
                }
            }

            // Check if username already exists
            if (Persistence.usernameAlreadyExistsInDB(un)) {
                System.out.println("\n    Username is taken. Please try again.");
                continue;
                //enterAndValidateUsername();
            }
            userN = un;
            valid = true; 
         }
         
        return userN;
    } 
     
    public static String enterAndValidatePassword() {
        
        boolean validPw = false;
        String passW = "";
         
        while (!validPw) {
            System.out.println("\n    Your Password should be between 8-20 characters "
                    + "long, using numbers, letters, and symbols.");
            System.out.print("    Enter a Password ->  ");
            String pw = sc.next();
            sc.nextLine();

            // Check password length
            if (pw.length() < 8 || pw.length() > 20) {
                System.out.println("\n    Error ! Invalid Password Length");

                pw = "";
                continue;
                //enterAndValidatePassword();
            }
            passW = pw;
            validPw = true;
        }
       
        return passW;
    }
     
}
