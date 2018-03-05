
package bankingapp;

import java.text.DecimalFormat;


public class Service {
    
    static DecimalFormat df = new DecimalFormat("#,###0.00");
    
    // CREATE AND SAVE NEW ACCOUNT
    // Write user's account info to account.txt
    public static Account createAndSaveNewAccount(String[] acctInfo) {
        
        Account tempAccount = new Account(acctInfo[0], acctInfo[1],
                acctInfo[2], acctInfo[3]);
        
        Persistence.writeAccount(tempAccount);
        
        System.out.println("\n  Account created. Please log in.\n");
        return tempAccount;
    }
    
    public static Account createLogInInstance(String un) {
        
        Account loggedInInstance = new Account();
        String[] info = findUserInfo(un);
        loggedInInstance.setFirstName(info[0]);
        loggedInInstance.setLastName(info[1]);
        loggedInInstance.setUsername(info[2]);
        loggedInInstance.setPassword(info[3]);
        loggedInInstance.setBalance(Double.parseDouble(info[4]));
        
        return loggedInInstance;
        
    }
    
    public static String[] findUserInfo(String un) {
        
        String[] result = Persistence.retrieveUserInfo(un);
       
        return result;
    }
    
    public static void formatBalance() {
        //double bal = Double.parseDouble(Persistence.findBalance("mfowler7"));
        double bal = LogInValidation.loggedInAcct.getBalance();
        System.out.println("  Your balance is  $ " + df.format(bal));
    }
    
    public static void makeWithdrawal(double amt) {
        
        double newBalance = LogInValidation.loggedInAcct.getBalance() - amt;
        LogInValidation.loggedInAcct.setBalance(newBalance);
        
    }
    
    public static void makeDeposit(double amt) {
        
        double newBalance = LogInValidation.loggedInAcct.getBalance() + amt;
        LogInValidation.loggedInAcct.setBalance(newBalance);
        
    }
    
}
