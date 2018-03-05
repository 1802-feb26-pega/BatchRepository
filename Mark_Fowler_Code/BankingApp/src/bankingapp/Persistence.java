
package bankingapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Persistence {
    
    static final String filename = "src/bankingapp/accounts.txt"; 
    
    // SAVE AN ACCOUNT
    public static void writeAccount(Account acct) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(acct.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    // DISPLAY EVERY ACCOUNT ON FILE
    public static List<String[]> readAccounts() {
        
        List<String[]> accounts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] acctData = line.split(",");
                Account tempAcct = new Account();
                tempAcct.setFirstName(acctData[0]);
                tempAcct.setLastName(acctData[1]);
                tempAcct.setUsername(acctData[2]);
                //tempAcct.setBalance(String.valueOf(acctData[3]));
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return accounts;
    }
    
    // CHECK IF USERNAME EXISTS
    public static boolean usernameAlreadyExistsInDB(String testUN) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            
            while ((line = br.readLine()) != null) {
                String[] acctData = new String[4];
                acctData = line.split(", ");
                
                if (acctData[2].equals(testUN)) {
                    return true;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return false;
    } // End of usernameAlreadyExists();
    
    // VERIFY USERNAME AND PASSWORD AT LOG IN
    public static boolean checkUserAndPass(String u, String p) {
      
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] acctData = line.split(", ");
                if (u.equals(acctData[2])) {
                    if (p.equals(acctData[3])) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }
    
    // CHECK BALANCE
    public static String findBalance(String user) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] acctData = line.split(", ");
                //System.out.println(acctData[2].toString());
                if (user.equals(acctData[2])) {
                    return acctData[4];
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return "  Balance not found.";
    }
    
    // CHANGE IN BALANCE METHODS
    public static String withdrawFromBalance(String user, double amt) {
        
       try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] acctData = line.split(", ");

                double bal;

                if (user.equals(acctData[2])) {
                    bal = Double.parseDouble(acctData[4]) - amt;
                    
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return "  Balance not found."; 
        
    }
    
    public static void changeBalance() {
        
        
        
    }
    
    public static void deleteFromDB(String un) {
    
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            
            while ((line = br.readLine()) != null) {
                String[] acctData = new String[5];
                acctData = line.split(", ");
                
                if (acctData[2].trim().equals(un)) {
                   try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
                        bw.write(line);
                   } catch (IOException ioe) {
                   ioe.printStackTrace();
                   } 
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
   
    public static String[] retrieveUserInfo(String un) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            String line = null;
            
            while ((line = br.readLine()) != null) {
                String[] acctData = new String[5];
                acctData = line.split(", ");
                
                if (acctData[2].equals(un)) {
                    String[] acctInformation = new String[5];
                    acctInformation[0] = acctData[0];
                    acctInformation[1] = acctData[1];
                    acctInformation[2] = acctData[2];
                    acctInformation[3] = acctData[3];
                    acctInformation[4] = acctData[4];
                    return acctInformation;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String[] errorArr = {"Error"}; 
        return errorArr;   
    }
    
} // End Persistence class
