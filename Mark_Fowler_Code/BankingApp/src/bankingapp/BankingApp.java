
package bankingapp;

public class BankingApp {

    public static void main(String[] args) {
        
        Client.displayMenu();
        Persistence.deleteFromDB("mfowler7");
    }
    
}
