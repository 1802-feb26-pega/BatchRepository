
package bankingapp;
import java.util.Scanner;

public class Client  {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void displayMenu() {
    
    int input = -1;

    System.out.println("  Welcome to the BankingApp \n");
    
    boolean inputReceived = false;
        
        while (inputReceived == false) {
            // Display options
            System.out.println("  (1) Create an account");
            System.out.println("  (2) Log into account");
            System.out.print("\n  Please select an option ->  ");

            // Get input from the user
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                // Check if input is a number from the menu
                if (input < 0 || input > 2) {
                    System.out.println("\n  Invalid entry. Please select again.");
                    continue;
                }
                // Exit the while loop
                inputReceived = true;
            }
            else {
                // Display error message
                System.out.println("\n  Invalid entry. Please select again. \n");
                // Clear scanner
                sc.nextLine();
                continue;
            }
            // Choose an action based on input - Create acct or log in
            processChoice(input);
        }
    
    } // End of displayMenu();
    
    public static void processChoice(int input) {
        switch (input) {
        case 1:
            String[] acctInfo = Validation.createNewAccountPrompt();
            Service.createAndSaveNewAccount(acctInfo);
            displayMenu();
            break;
        case 2:
            LogInValidation.logIn();
            break;
        default:
            System.out.println("  Invalid entry. Please select again.");
        }   // End switch
        
    } // End processChoice();
       
} // End Client class
