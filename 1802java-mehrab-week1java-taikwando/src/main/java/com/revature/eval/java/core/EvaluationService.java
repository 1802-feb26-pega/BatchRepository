package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		// TODO Write an implementation for this method declaration
		String reversed = "";
		for (int i = string.length() - 1; i > -1; i--) {
			reversed += string.charAt(i);
		}
		return reversed;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
		String acronym = "";
		for (int i = 0; i < phrase.length(); i++) {
			if (i == 0) {
				acronym += phrase.charAt(i);
			}
			else if (phrase.charAt(i) == ' ') {
				acronym += phrase.charAt(i + 1);
			}
			else {
				
			}
		}
		acronym.toUpperCase();
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// TODO Write an implementation for this method declaration
			if (getSideOne() == getSideTwo() && getSideOne() == getSideThree()) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			// TODO Write an implementation for this method declaration
			if (getSideOne() == getSideTwo()) {
				if (getSideOne() != getSideThree() && getSideTwo() != getSideThree()) {
					return true;
				}
				
				else if (getSideOne() == getSideThree() || getSideTwo() == getSideThree()) {
					return false;
				}
			}
			
			else if (getSideOne() == getSideThree()) {
				if (getSideOne() != getSideTwo() && getSideTwo() != getSideThree()) {
					return true;
				}
				
				else if (getSideOne() == getSideTwo() || getSideTwo() == getSideThree()) {
					return false;
				}
			}
			return false;
		}

		public boolean isScalene() {
			// TODO Write an implementation for this method declaration
			if (getSideOne() != getSideTwo() && getSideOne() != getSideThree() && getSideTwo() != getSideThree()) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		HashMap<Character, Integer> values = new HashMap<Character, Integer>();
		char[] ones = {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'};
		char[] twos = {'D', 'G'};
		char[] threes = {'B', 'C', 'M', 'P'};
		char[] fours = {'F', 'H', 'V', 'W', 'Y'};
		char[] fives = {'K'};
		char[] eights = {'J', 'X'};
		char[] tens = {'Q', 'Z'};
		for (int i = 0; i < ones.length; i++) {
			values.put(ones[i], 1);
		}
		for (int i = 0; i < twos.length; i++) {
			values.put(twos[i], 2);
		}
		for (int i = 0; i < threes.length; i++) {
			values.put(threes[i], 3);
		}
		for (int i = 0; i < fours.length; i++) {
			values.put(fours[i], 4);
		}
		for (int i = 0; i < fives.length; i++) {
			values.put(fives[i], 5);
		}
		for (int i = 0; i < eights.length; i++) {
			values.put(eights[i], 8);
		}
		for (int i = 0; i < tens.length; i++) {
			values.put(tens[i], 10);
		}
		
		int score = 0;
		string = string.toUpperCase();
		for (int i = 0; i < string.length(); i++) {
			char letter = string.charAt(i);
			score += values.get(letter);
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		
		
		//first remove the characters that are not needed - char.isDigit() or RegEx[x]
		string = string.replaceAll("[^0-9]", "");
		String finalNum = "";
		int idx = 0;
		int count = 0;
		if (string.charAt(idx) == '1') {
			idx++;
			count++;
		}
		
		while (idx < string.length()) {
			finalNum += string.charAt(idx);
			count++;
			idx++;
		}
		
		if (count > 11 || (count >10 && string.charAt(0) != '1') || (count == 10 && string.charAt(0) == '1')) {
			throw new IllegalArgumentException("Illegal Input");
		}
		
		return finalNum;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
		
		//Parse by String?
		String[] wordsList = string.split(" ");
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		for (int i = 0; i < wordsList.length; i++) {
			if (count.containsKey(wordsList[i])) {
				int counter = count.get(wordsList[i]);
				count.put(wordsList[i], counter + 1);
			}
			else {
				count.put(wordsList[i], 1);
			}
		}
		
		return count;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// TODO Write an implementation for this method declaration
			/**
			 * for binary search:
			 * 	start at the middle of the list (length/2) or (length/2 + 1)
			 * 	check if we are at our value...
			 * 		if we are, return the index
			 * 		if we are not, compare what we are looking for with our current value
			 * 			if we are less than, take the right half
			 * 			if we are greater than, take the left half
			 * 			repeat...
			 */
			
			
			
			return 0;
		}
		
		public int getIndex() {
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// TODO Write an implementation for this method declaration
		
		//create a new string array that will house the word(s)
		String[] word = string.split(" ");
		String result = "";
		ArrayList<Character> vowels = new ArrayList<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
		for (int i = 0; i < word.length; i++) {
			if (i >= 1) {
				result += " ";
			}
			if (vowels.contains(word[i].charAt(0))) {
				result += word[i] + "ay";
			}
			else if (!vowels.contains(word[i].charAt(0))) {
				int consonants = 1;
				if (word[i].charAt(0) == 'q' && word[i].charAt(1) == 'u') {
					result += word[i].substring(2, word[i].length()) + word[i].substring(0, 2) + "ay";
				}
				else {
					while (!vowels.contains(word[i].charAt(consonants))) {
						consonants++;
					}
					result += word[i].substring(consonants, word[i].length()) + word[i].substring(0, consonants) + "ay";
				}
			}
		}
		return result;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// TODO Write an implementation for this method declaration
		String strInput = Integer.toString(input);
		int result = 0;
		for (int i = 0; i < strInput.length(); i++) {
			int tmp = Character.getNumericValue(strInput.charAt(i));
			result += Math.pow(tmp, strInput.length());
		}
		if (result == input) {
			return true;
		}
		else {
			return false;			
		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// TODO Write an implementation for this method declaration
		
		/**
		 * How to calculate a the prime factors:
		 * 
		 * divide by 2 until you get an odd number
		 * now that the number is odd, we can skip one element and start at 3 (1 doesn't count and we already have 2)
		 * 
		 * 
		 */
		
		List<Long> result = new ArrayList<Long>();
		
		//print all of the twos first since that is the easiest to find
		while (l % 2 == 0) {
			result.add(2L);
			l /= 2;
		}
		
		//now it should be odd at this point
		//can start at 3 because we have 1 and 2 already
		for (int i = 3; i < Math.sqrt(l); i+=2) {
			
			//check to see if divisible
			if (l % i == 0) {
				result.add((long) i);
				
				l /= i;
			}
		}
		
		if (l > 2) {

		}
		
		return result;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// Rotate each char in the string to the right "key" number of time
			char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			
			String rotated = "";
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == ' ') {
					rotated += string.charAt(i);
				}
				else if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
					String temp = string.toLowerCase();
					for (int e = 0; e < alphabet.length; e++) {
						if (alphabet[e] == temp.charAt(i)) {
							int newLetterIndex = (e + key) % 26;
							String newLetter = "";
							newLetter += alphabet[newLetterIndex];
							newLetter = newLetter.toUpperCase();
							rotated += newLetter;
						}
					}
				}
				else if (string.charAt(i) >= 'a' && string.charAt(i) <= 'z') {
					for (int e = 0; e < alphabet.length; e++) {
						if (alphabet[e] == string.charAt(i)) {
							int newLetterIndex = (e + key) % 26;
							rotated += alphabet[newLetterIndex];
						}
					}
				}
				else {
					rotated += string.charAt(i);
				}
			}
			return rotated;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// TODO Write an implementation for this method declaration
		int counter = 0;
		int nthPrime;
		int y;
		
		if (i == 0) {
			throw new IllegalArgumentException("Can not find the 0th prime");
		}
		
		int number = 2;
		while (counter < i) {
			boolean primeCheck = isPrime(number);
			if (primeCheck == true) {
				counter++;
				number++;
			}
			else {
				number++;
			}
		}
		nthPrime = number - 1;
		
		return nthPrime;
	}
	
	public boolean isPrime(int num) {
		for(int i = 2; i < num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			string = string.toLowerCase();
			String encoded = "";
			int spacer = 0;
			
			for (int i = 0; i < string.length(); i++) {
				for (int e = 0; e < alphabet.length; e++) {
					if (string.charAt(i) == alphabet[e]) {
						if (spacer == 5) {
							spacer = 0;
							encoded += " " + alphabet[(alphabet.length - 1) - e];
							spacer++;
						}
						else {
							encoded += alphabet[(alphabet.length - 1) - e];
							spacer++;
						}
					}
				}
				if (string.charAt(i) >= '1' && string.charAt(i) <= '9') {
					if (spacer == 5) {
						spacer = 0;
						encoded += " " + string.charAt(i);
						spacer++;
					}
					else {
						encoded += string.charAt(i);
						spacer++;
					}
				}
			}
			return encoded;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			string = string.toLowerCase();
			String decoded = "";
			for (int i = 0; i < string.length(); i++) {
				for (int e = 0; e < alphabet.length; e++) {
					if (string.charAt(i) == alphabet[e]) {
						decoded += alphabet[(alphabet.length - 1) - e];
					}
				}
				if (string.charAt(i) >= '1' && string.charAt(i) <= '9') {
					decoded += string.charAt(i);
				}
			}
			return decoded;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		// get rid of the '-'
		char[] charArrOfString = string.toCharArray();
		string = "";
		for (int i = 0; i < charArrOfString.length; i++) {
			if (charArrOfString[i] == '-') {
				
			}
			else if (charArrOfString[i] >= '0' && charArrOfString[i] <= '9') {
				string += charArrOfString[i];
			}
			else if (charArrOfString[i] == 'x' || charArrOfString[i] == 'X') {
				string += charArrOfString[i];
			}
			else {
				return false;
			}
		}
		
		int result = 0;
		if (string.length() == 10) {
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == 'x' || string.charAt(i) == 'X') {
					result += (10 * (10-i));
				}
				result += ((int)(string.charAt(i)) * (10-i));
			}
		}
		else {
			return false;
		}
		
		if (result % 11 == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// TODO Write an implementation for this method declaration
		string = string.toLowerCase();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Map<Character, Integer> lettercount = new HashMap<>();
		for (int i = 0; i < alphabet.length; i++) {
			lettercount.put(alphabet[i], 0);
		}
		
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == ' ') {
				
			}
			else {
				lettercount.put(string.charAt(i), lettercount.get(string.charAt(i)) + 1);	
			}
		}
		
		for (int i = 0; i < alphabet.length; i++) {
			char temp = alphabet[i];
			if (lettercount.get(temp) < 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		int result = 0;
		List<Integer> used = new ArrayList<>();
		for (int e = 0; e < set.length; e++) {
			int interval = set[e];
			int num = set[e];
			while (num < i) {
				System.out.print(num + " ");
				if (used.contains(num)) {
					num += interval;
				}
				else {
					result += num;
					used.add(num);
					num += interval;
				}
			}
			System.out.println("Made it out");
		}
		System.out.println(result + "\n");
		return result;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
