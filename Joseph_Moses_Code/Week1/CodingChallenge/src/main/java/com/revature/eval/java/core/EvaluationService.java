package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
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
		
		char[] input = string.toCharArray();
		String output = "";
		
		for(int i = input.length -1 ; i >= 0; i--) {
			output = output.concat(String.valueOf(input[i]));
		}
		
		return output;
		
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
		String[] inputs = phrase.split("([ .,;:_-])");
		StringBuilder output = new StringBuilder("");
		
		for(String element : inputs) {
			
			if(element.length() < 1) { // account for the spaces after a , ; :
				continue;
			}
			
			output.append(element.charAt(0));
		}
		
		return output.toString().toUpperCase();
		
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
			
			if(sideOne == sideTwo && sideOne == sideThree && sideTwo == sideThree) {
				return true;
			} else {
				return false;
			}

		}

		public boolean isIsosceles() {
			
			if(sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree) {
				return true;
			} else {
				return false;
			}
			
		}

		public boolean isScalene() {
			
			if(sideOne == sideTwo && sideOne == sideThree && sideTwo == sideThree) {
				return false;
			} else {
				return true;
			}

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
		
		int score = 0;
		
		char[] letters = string.toUpperCase().toCharArray();
		
		for(char letter : letters) {
			
			String letterString = Character.toString(letter);
			
			if(letterString.matches("([AEIOULNRST])")) {
				score += 1;
			}
			else if(letterString.matches("([DG])")) {
				score += 2;
			}
			else if(letterString.matches("([BCMP])")) {
				score += 3;
			}
			else if(letterString.matches("([FHVWY])")) {
				score += 4;
			}
			else if(letterString.matches("K")) {
				score += 5;
			}
			else if(letterString.matches("([JX])")) {
				score += 8;
			}
			else if(letterString.matches("([QZ])")) {
				score += 10;
			}
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
	
	public String cleanPhoneNumber(String string) throws IllegalArgumentException{
		
		String cleaned = string.replaceAll("[^0-9]", "");
		StringBuilder output = new StringBuilder(cleaned);
		
		if(cleaned.length() < 10 || cleaned.length() > 11) {
			throw new IllegalArgumentException();
		}
		else if(cleaned.length() == 11) {
			
			output.deleteCharAt(0);
		}

		return output.toString();

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
		
		string = string.replaceAll("[\\s]", " ");
		String[] input = string.split("[^A-Za-z0-9]");
		Map<String, Integer> output = new HashMap<String, Integer>();
		
		for(String word : input) {
			
			if(word.length() < 1) {
				//do nothing with it. The word is blank.
			}
			else if(output.containsKey(word)) {
				output.put(word, output.get(word) + 1);
			}
			else {
				output.put(word, 1);
			}
		}
		
		return output;
		
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
	static class BinarySearch<T extends Comparable<T>>{
		private List<T> sortedList;

		public int indexOf(T t) {
			
			int low = 0;
			int high = sortedList.size()-1;
			
			while(low <= high) {
				int mid = (low + high)/2;
				T midVal = sortedList.get(mid);
				int comparison = t.compareTo(midVal);
				
				if(comparison < 0) {
					high = mid - 1;
				}
				else if(comparison > 0) {
					low = mid + 1;
				}
				else {
					return mid;
				}
				
			}
			return -1;
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
		
		string = string.toLowerCase();
		String[] input = string.split(" ");
		StringBuilder output = new StringBuilder("");
		String vowels = "aeiou";
		
		for(String s : input) {
			
			StringBuilder temp = new StringBuilder(s);
			
			int firstVowel = -1;
			
			for(int i = 0; i < s.length(); i++) {
				
				String letter = new Character(s.charAt(i)).toString();
				if(vowels.indexOf(s.charAt(i)) != -1) {
					firstVowel = i;
					break;
				}
			}

			System.out.println(firstVowel);
			
			if(firstVowel == 0) {
				temp.append("ay");
			}
			else {
				if(s.charAt(0) == 'q') {
					temp.append(temp.subSequence(0, firstVowel + 1) + "ay");
					temp.delete(0, firstVowel + 1);
				}
				else {
					temp.append(temp.subSequence(0, firstVowel) + "ay");
					temp.delete(0, firstVowel);
				}
			}
			
			if(!s.equals(input[input.length - 1])) {
				temp.append(" ");
			}
			
			output.append(temp.toString());
			
		}
		
		return output.toString();
		
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
		int originalInput = input;
		int rightMostDigit;
		int total = 0;
		int numberOfDigits = String.valueOf(input).length();
		
		while(input > 0) {
			rightMostDigit = input%10;	//store the right most digit
			input = input/10;			//remove the right most digit
			total = (int) (total + Math.pow(rightMostDigit, numberOfDigits));
		}
		
		if(total == originalInput) {
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
		List<Long> factors = new ArrayList<>();
		
		for(int i = 2; i <= l; i++) {
			while(l % i == 0) {
				factors.add((long)i);
				l /= i;
			}
		}
		
		return factors;
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
		private String letters = "abcdefghijklmnopqrstuvwxyz";
		
		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			char[] input = string.toCharArray();
			char[] cipher = new char[input.length];
			int plainIndex = 0;
			int cipherIndex = 0;
			for(int i = 0; i < input.length; i++) {
				
				Character c = input[i];
				
				if(c.isLetter(c)) {
					
					Character noCase = c.toLowerCase(c);
					plainIndex = letters.indexOf(noCase);
					cipherIndex = plainIndex + key;
					
					if(cipherIndex > 25) {
						cipherIndex -= 26;
					}
					
					if(c.isUpperCase(c)) {
						cipher[i] = Character.toUpperCase(letters.charAt(cipherIndex));
					}
					else {
						cipher[i] = letters.charAt(cipherIndex);
					}	
				}
				else {
					cipher[i] = c;
				}
			}
			return new String(cipher);
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
		
		if(i == 0) {
			throw new IllegalArgumentException();
		}
		
		int numPrimesFound = 0;
		int test = 2;
		
		while(numPrimesFound < i) {
			if(isPrime(test++)) {
				numPrimesFound++;
			}
		}
		
		return --test;
		
		
	}
	
	public static boolean isPrime(int n) {
		for(int i = 2; i < n; i++) {
			if(n % i == 0) {
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

		private static String plain = "abcdefghijklmnopqrstuvwxyz";
		private static String cipher = "zyxwvutsrqponmlkjihgfedcba";
		
		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			string = string.toLowerCase().replaceAll("[^a-z0-9]", "");
			
			char[] input = string.toCharArray();
			StringBuilder output = new StringBuilder();
			for(int i = 0; i < input.length; i++) {
				
				if(i != 0 && i % 5 == 0) {
					output.append(" ");
				}
				
				Character c = input[i];
				
				if(c.isLetter(c)) {
					int index = plain.indexOf(c);
					output.append(cipher.charAt(index));
				}
				else {
					output.append(c);
				}
			}
			
			return output.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			/*The easy way with less code is
			 * return encode(string.replaceAll(" ", "");
			 */
			
			string = string.toLowerCase().replaceAll(" ", "");
			
			char[] input = string.toCharArray();
			StringBuilder output = new StringBuilder();
			for(int i = 0; i < input.length; i++) {
				
				Character c = input[i];
				
				if(c.isLetter(c)) {
					int index = cipher.indexOf(c);
					output.append(plain.charAt(index));
				}
				else {
					output.append(c);
				}
			}
			
			return output.toString();
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
		
		if(!string.matches("([X0-9-]+)")) {
			return false;
		}
		else {
			char[] input = string.replaceAll("-", "").toCharArray();
			int sum = 0;
			for(int i = 0; i < input.length; i ++) {
				
				int value;
				if(input[i] == 'X') {
					value = 10;
				}
				else {
					value = Integer.parseInt(String.valueOf(input[i]));
				}
				sum += (value * (10 - i));	
			}
			if(sum%11 == 0) {
				return true;
			}
			else {
				return false;
			}
		}
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
		string = string.replaceAll(" ", "").toLowerCase();
		ArrayList<Character> letters = new ArrayList<>();
		char[] input = string.toCharArray();
		
		for(Character c : input) {
			if(letters.contains(c)) {
				continue;
			}
			else {
				letters.add(c);
			}
		}
		
		if(letters.size() < 26) {
			return false;
		}
		else {
			return true;
		}
		
		
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
		LocalDateTime input = null;
		
		if(given.getClass().getName().equals("java.time.LocalDate")) {
			input = ((LocalDate)given).atStartOfDay();
		}
		else {
			input = (LocalDateTime) given;
		}
		
		return input.plus(1000000000L, ChronoUnit.SECONDS);
		
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
		
		int sum = 0;
		int multiple;
		ArrayList<Integer> naturals = new ArrayList<>();
		for(int x : set) {
			
			for(int j = 1; j < i; j++) {
				
				multiple = x * j;

				if(multiple < i && !naturals.contains(multiple)) {
					sum += multiple;
					naturals.add(multiple);
				}
				else if(multiple < i && naturals.contains(multiple)){
					continue;
				}
				else {
					break;
				}
			}
		}
		return sum;
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
		
		string = string.replaceAll(" ", "");
		
		if(string.length() < 1) {
			return false;
		}
		else if (!string.matches("\\d+")) {

			return false;
		}
		else {
			int sum = 0;
			char[] numbers = string.toCharArray();
			
			for(int i = 0; i < numbers.length; i++) {
				if(i%2 == 0) {
					sum += Integer.valueOf(numbers[i]) * 2;
				}
				else {
					sum += Integer.valueOf(numbers[i]);
				}
			}
			
			if(sum%10 == 0) {
				return true;
			}
			else {
				return false;
			}
		}
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
		
		int output = 0;
		ArrayList<Integer> numbers = new ArrayList<>();
		String operation = null;
		
		string = string.replaceAll("[^a-zA-Z0-9- ]", "");
		
		Scanner scan = new Scanner(string);
		scan.useDelimiter(" ");
		
		while(scan.hasNext()) {
			
			if(scan.hasNextInt()) {
				numbers.add(scan.nextInt());
			}
			else if(scan.hasNext("plus")) {
				operation = scan.next(); 
			}
			else if(scan.hasNext("minus")) {
				operation = scan.next(); 
			}
			else if(scan.hasNext("multiplied")) {
				operation = scan.next(); 
			}
			else if(scan.hasNext("divided")) {
				operation = scan.next(); 
			}
			else {
				scan.next();
			}	
		}
		scan.close();
		
		if(operation.equals("plus")) {
			output = numbers.get(0) + numbers.get(1);
		}
		else if(operation.equals("minus")) {
			output = numbers.get(0) - numbers.get(1);
		}
		else if(operation.equals("multiplied")) {
			output = numbers.get(0) * numbers.get(1);
		}
		else if(operation.equals("divided")) {
			output = numbers.get(0) / numbers.get(1);
		}
		
		return output;
		
		
	}

}
