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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testing {
	
	public static void main(String[] args) {
		
		
		Map<Character, Character> encode = new HashMap<Character, Character>();
		encode.put('a', 'z');
		encode.put('b', 'y');
		encode.put('c', 'x');
		encode.put('d', 'w');
		encode.put('e', 'v');
		encode.put('f', 'u');
		encode.put('g', 't');
		encode.put('h', 's');
		encode.put('i', 'r');
		encode.put('j', 'q');
		encode.put('k', 'p');
		encode.put('l', 'o');
		encode.put('m', 'n');
		encode.put('n', 'm');
		encode.put('o', 'l');
		encode.put('p', 'k');
		encode.put('q', 'j');
		encode.put('r', 'i');
		encode.put('s', 'h');
		encode.put('t', 'g');
		encode.put('u', 'f');
		encode.put('v', 'e');
		encode.put('w', 'd');
		encode.put('x', 'c');
		encode.put('y', 'b');
		encode.put('z', 'a');
		
		String string = "The quick brown fox jumps over the lazy dog.";
		
		String low = string.toLowerCase();
		char c;
		char lowc;
		List<String> str = new ArrayList<String>();
		int block = 0; // keep track of 5 character long blocks
		
		for (int i = 0; i < string.length(); i++) {
			c = string.charAt(i);
			lowc = low.charAt(i);
			if (block == 5) {
				block = 0;
				str.add(" ");
			}
			if (encode.get(lowc) != null) {
				if (c != lowc) { // thought capitalization mattered but it didnt't just left it in for reference
					str.add( ( Character.toString( encode.get(lowc) ) ) );
					block++;
				} else {
					str.add(Character.toString(encode.get(lowc)));
					block++;
				}
			} else if (Character.isDigit(c)) {
				str.add(Character.toString(string.charAt(i)));
				block++;
			}
		}
		
		String s = (String) String.join("", str);
		
		System.out.println(s);
		System.out.println("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt");
		System.out.println(s.equals("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"));
		
	}

}
