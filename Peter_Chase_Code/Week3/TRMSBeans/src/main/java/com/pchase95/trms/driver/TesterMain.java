package com.pchase95.trms.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesterMain {
    public static void main(String[] args) {
        try {
            System.out.println("HELLO");
            PrintWriter writer = new PrintWriter(new File("meme.txt"));
            writer.println("Hello");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesterMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
