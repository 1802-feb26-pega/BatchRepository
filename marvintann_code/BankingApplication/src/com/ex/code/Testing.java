package com.ex.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class Testing {
	
	public static void main(String[] args) {
		
		File file = new File("src/com/ex/data/file.txt");
        if (file.length() == 0)
            System.out.println("File is empty!!!");
        else
            System.out.println("File is not empty!!!");
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			
			for (int i = 0; i < 10; i++) {
				System.out.println(br.readLine());
				if (br.readLine() == null) {
					System.out.print(" Is null?");
				}
			}
			System.out.println("while loop");
			while (br.readLine() != null) {
				System.out.println(br.readLine());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
