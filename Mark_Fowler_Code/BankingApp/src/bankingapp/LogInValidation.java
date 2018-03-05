
package bankingapp;
import java.util.Scanner;
import java.util.InputMismatchException;

public class LogInValidation {
   
    public static Account loggedInAcct;
    static Scanner sc = new Scanner(System.in);
    
    public static void logIn() {
        
        System.out.println("\n  Log In selected.");
        
        boolean loggedIn = true;
        
        System.out.print("  Username ->  ");
        String un = sc.next();
        System.out.print("  Password ->  ");
        String pw = sc.next();
        
        validateUserPass(un, pw);

    }
    
    public static boolean validateUserPass(String user, String pass) {
        
        if (Persistence.checkUserAndPass(user, pass)) {
            loggedInAcct = Service.createLogInInstance(user);
            displayLoggedInMenu();
        }
        else {
            System.out.println("\n  Sorry, invalid username and"
                    + " password combination.");
            logIn();
        }
        return false;
    }
    
    
    public static void displayLoggedInMenu() {
        
        boolean loggedIn = true;
      
        while(loggedIn) {
                    
        System.out.println("\n  Options Menu");
        System.out.println("\n  (1)  Check account balance.");
        System.out.println("  (2)  Withdraw funds.");
        System.out.println("  (3)  Make a deposit.");
        System.out.println("  (4)  Log Out");
        //System.out.println("  (5)  Close the application.");
        System.out.print("\n  Please select from the menu to perform an action ->  ");
        
        int input = sc.nextInt();
        
        switch (input) {
            case 1:
                System.out.println("\n  Check account balance selected.");
                Service.formatBalance();
                break;
            case 2:
                System.out.println("\n  Withdraw funds selected.");
                System.out.print("\n  Enter the amount you would like to withdraw ->  ");
                
                double amountRequested = sc.nextDouble();
                Service.makeWithdrawal(amountRequested);
                Service.formatBalance();
                               
                break;
            case 3:
                System.out.println("\n  Make a deposit selected.");
                System.out.print("\n  Enter the amount you would like to deposit ->  ");
                double amountToDeposit = sc.nextDouble();
                Service.makeDeposit(amountToDeposit);
                Service.formatBalance();
                break;
            case 4: 
                System.out.println("\n  Logging out... \n");
                Client.displayMenu();
                break;
            //case 5:
            //    System.out.println("  Bye!");
            //    loggedInAcct = null;
            //    loggedIn = false;
            //    break;
            default:
                System.out.println("\n  Invalid Entry. Please try again.");
        }
            
        }
                
    }
    
}
