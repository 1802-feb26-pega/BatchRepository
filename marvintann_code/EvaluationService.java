package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		List<String> str = new ArrayList<String>();
		int len = string.length();
		
		for (int i = len - 1; i >= 0; i--) {
			str.add( Character.toString( string.charAt(i) ) );
		}
		
		String fin = String.join("", str);
		
		return fin;
		
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG). (crunchy    crunch   roll)
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
		
		// assuming each word is separated by a space, hopefully that will work
		ArrayList<String> acro = new ArrayList<String>();
		int len = phrase.length();
		
		for  (int i = 0; i < len; i++) {
			char check = phrase.charAt(i); // keeps track of where we are in the string
			if (check == ' ' || check == '-') {
				// do nothing move on until a character is found
			} else if (check != ' ' && i == 0) { // if the first index has a non space char then we take it as the first letter
				acro.add(String.valueOf(check));
			} else if (i > 0 && check != ' ' && (phrase.charAt(i-1) == ' ' || phrase.charAt(i-1) == '-')) { // takes first char of each word
				acro.add(String.valueOf(check));
			}
		}
		String acroString = String.join("", acro); // convert from ArrayString to regular String
		return acroString.toUpperCase(); // capitalize each letter
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
			double s1 = this.getSideOne(); // getting all the sides
			double s2 = this.getSideTwo();
			double s3 = this.getSideThree();
			
			if (s1 == s2 && s1 == s3) { // all have to be equal
				return true;
			} else {return false;}
			//return false;
		}

		public boolean isIsosceles() {
			// TODO Write an implementation for this method declaration
			double s1 = this.getSideOne();
			double s2 = this.getSideTwo();
			double s3 = this.getSideThree();
			
			if ((s1 == s2 && s1 != s3) || (s1 == s3 && s1 != s2)) { // only two sides equal
				return true;
			} else {return false;}
			//return false;
		}

		public boolean isScalene() {
			// TODO Write an implementation for this method declaration
			double s1 = this.getSideOne();
			double s2 = this.getSideTwo();
			double s3 = this.getSideThree();
			
			if (s1 != s2 && s1 != s3 && s2 != s3) { // all have to be unequal
				return true;
			} else {return false;}
			//return false;
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
		int score = 0; // score
		String upper = string.toUpperCase(); // make input string all capital letters
		for (int i = 0; i < upper.length(); i++) {
			char c = upper.charAt(i);
			if (c == 'A'||c == 'E'||c == 'I'||c == 'O'||c == 'U'||c == 'L'||c == 'N'||c == 'R'||c == 'S'||c == 'T') {
				score++;
			} else if (c == 'G') {
				score += 2;
			} else if (c == 'B'||c == 'C'||c == 'M'||c == 'P') {
				score += 3;
			} else if (c == 'F'||c == 'H'||c == 'V'||c == 'W'||c == 'Y') {
				score += 4;
			} else if (c == 'K') {
				score += 5;
			} else if (c == 'J'||c == 'X') {
				score += 8;
			} else if (c == 'Q'||c == 'Z') {
				score += 10;
			} else {
				score += 0;
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
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		
		ArrayList<String> clean = new ArrayList<String>(); // storing values in an arraylist
		
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '0'||c == '1'||c == '2'||c == '3'||c == '4'||c == '5'||c == '6'||c == '7'||c == '8'||c == '9') {
				if (c == '1' && clean.isEmpty()) { 
					// do nothing we do print out the first '1'
				} else {
					clean.add(String.valueOf(c));
				}
			} else {} // do nothing we do not print anything other than '0' through '9' 
		}
		
		String cleanS = String.join("", clean);
		
		if (cleanS.length() != 10) {
			throw new IllegalArgumentException();
		}
		
		return cleanS;
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
		
		ArrayList<String> temp = new ArrayList<String>(); // will build word as we iterate across string and add to map with full word
		// then clear when added and finally move onto next word if available and start over
		String key; // key stored as a string
		int val; // value of key
		
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i); // get character at index
			if (Character.isLetter(c)) { // check if it is a letter
				temp.add(String.valueOf(c)); // add it to temp
				if (i == string.length() - 1) {
					key = String.join("", temp); // convert the ArrayList to a String so we can enter it as a key
					if (map.containsKey(key)) { // increment the value since the key already exists
						val = map.get(key); // get the value at the key
						val = val + 1; // increment value
						System.out.println(key + " value incremented");
						map.put(key, val);
						temp.clear(); // empty temporary arraylist to fill with next word
					} else { // add to map since the key doesn't exist yet
						map.put(key, 1); // add to map and increment to 1
						System.out.println("adding " + key + " for first time");
						temp.clear(); // empty temporary arraylist to fill with next word
					}
				}
			} else {
				if (temp.isEmpty()) {
					// do nothing we continue until we find a letter
				} else { // we have reached the end of the word and will add it to the map or increment the value if it already exists
					key = String.join("", temp); // convert the ArrayList to a String so we can enter it as a key
					if (map.containsKey(key)) { // increment the value since the key already exists
						val = map.get(key); // get the value at the key
						val = val + 1; // increment value
						System.out.println(key + " value incremented");
						map.put(key, val);
						temp.clear(); // empty temporary arraylist to fill with next word
					} else { // add to map since the key doesn't exist yet
						map.put(key, 1); // add to map and increment to 1
						System.out.println("adding " + key + " for first time");
						temp.clear(); // empty temporary arraylist to fill with next word
					}
				}
			}
		}
		
		return map;
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
			
			int num; // we need to find the index of this value in the array
			//List<Integer> curr = (List<Integer>) getSortedList(); // current list
			
			List<T> curr = getSortedList();
			
			int left = 0; // we start at index 0
			int right = curr.size() - 1; // we end at the last index which is size - 1 
			int mid;
			int val;
			int index = 0;
			
			// code to determine type of list
			Object o = curr.get(0);
			String javaType = o.getClass().getName();
			String type = (String) javaType.substring(10); // cuts out java.lang.
			
			if (type.equals("Integer")) {
				num = (int) t;
			} else {
				num = Integer.parseInt((String) t);
			}
			
			while (left <= right) {
				o = curr.get(0);
				javaType = o.getClass().getName();
				type = javaType.substring(10); // cuts out java.lang.
				mid = left + ((right - left) / 2);
				if (type.equals("Integer")) {
					val = (int) curr.get(mid);
				} else {
					val = Integer.parseInt((String) curr.get(mid));
				}
				
				//val = (int) curr.get(mid);
				if (num == val) {
					index = mid;
					//return index;
					break;
				} else if (num < val) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
			return index;
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
		
		String[] words = string.split(" "); // storing words
		int wa = words.length; // number of words in the string
		String fin = ""; // stores each pig latin word
		int len; // length of each word
		char c; // 1st char of word
		char cc; // 2nd
		char ccc; // 3rd
		
		for (int i = 0; i < wa; i++) { // iterate through number of words
			len = words[i].length(); // length of each word
			for (int j = 0; j < len; j++) { // iterate though each character in each word
				c = words[i].charAt(j);
				if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') { // consonant
					if (j == 0) { // check 1st consonant
						cc = words[i].charAt(j + 1); // 2nd char
						ccc = words[i].charAt(j + 2); // 3rd char
						if (cc != 'a' && cc != 'e' && cc != 'i' && cc != 'o' && cc != 'u') { // check if 2nd consonant available
							if (ccc != 'a' && ccc != 'e' && ccc != 'i' && ccc != 'o' && ccc != 'u') { // check if 3rd consonant available
								if (i > 0) { // if not first word
									fin = fin + " "; // we add in a space
								}
								fin = fin + words[i].substring(3) + Character.toString(c) + Character.toString(cc) + Character.toString(ccc) + "ay";
								break; // move onto next word
							}
							if (i > 0) { // if not first word
								fin = fin + " "; // we add in a space
							}
							fin = fin + words[i].substring(2) + Character.toString(c) + Character.toString(cc) + "ay";
							break; // move onto next word
						} else { // case for 1 consonant
							if (ccc == 'a' || ccc == 'e' || ccc == 'i' || ccc == 'o' || ccc == 'u') { // 2 vowels in a rowafter a consonant
								if (i > 0) { // if not first word
									fin = fin + " "; // we add in a space
								}
								fin = fin + words[i].substring(2) + Character.toString(c) + Character.toString(cc) + "ay";
								break;
							} else {
								if (i > 0) { // if not first word
									fin = fin + " "; // we add in a space
								}
								fin = fin + words[i].substring(1) + Character.toString(c) + "ay";; // single consonant followed by single vowel
								break;
							}
						}
					} else {} // do nothing move onto next word
				} else { // vowel case
					if (i > 0) { // if not first word
						fin = fin + " "; // we add in a space
					}
					fin = fin + words[i] + "ay"; // just add ay to end
					break; // move onto next word
				}
			}
		}
		
		return fin;
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
		
		int currentInput = input;
		int len = String.valueOf(input).length(); // geting number of digits in the integer
		int num = 0;
		int curr;
		
		for (int i = 0; i < len; i++) {
			curr = currentInput % 10; // works but it does the numbers backwards (right to left)
			currentInput = currentInput / 10;
			num = (int) (num + Math.pow(curr, len));
		}
		
		if (num == input) {
			return true;
		} else {
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
		
		List<Long> prime = new ArrayList<Long>();

		long len = l;
		long mod;
		
		for (long i = 2L; i <= len; i++) { // start at 2 no point in starting at 1
			mod = len % i;
			if (mod == 0) {
				prime.add(i);
				len = (long) len / i;
				i = 2;
			} else {} // keep going until something divides with no remainder
		}
		
		return prime;
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
			// TODO Write an implementation for this method declaration
			Map<Integer, Character> alpha = new HashMap<Integer, Character>();
			alpha.put(1, 'a');
			alpha.put(2, 'b');
			alpha.put(3, 'c');
			alpha.put(4, 'd');
			alpha.put(5, 'e');
			alpha.put(6, 'f');
			alpha.put(7, 'g');
			alpha.put(8, 'h');
			alpha.put(9, 'i');
			alpha.put(10, 'j');
			alpha.put(11, 'k');
			alpha.put(12, 'l');
			alpha.put(13, 'm');
			alpha.put(14, 'n');
			alpha.put(15, 'o');
			alpha.put(16, 'p');
			alpha.put(17, 'q');
			alpha.put(18, 'r');
			alpha.put(19, 's');
			alpha.put(20, 't');
			alpha.put(21, 'u');
			alpha.put(22, 'v');
			alpha.put(23, 'w');
			alpha.put(24, 'x');
			alpha.put(25, 'y');
			alpha.put(26, 'z');
			
			Map<Character, Integer> numeral = new HashMap<Character, Integer>();
			numeral.put( 'a',1);
			numeral.put( 'b',2);
			numeral.put( 'c',3);
			numeral.put( 'd',4);
			numeral.put( 'e',5);
			numeral.put( 'f',6);
			numeral.put( 'g',7);
			numeral.put( 'h',8);
			numeral.put( 'i',9);
			numeral.put( 'j',10);
			numeral.put( 'k',11);
			numeral.put( 'l',12);
			numeral.put( 'm',13);
			numeral.put( 'n',14);
			numeral.put( 'o',15);
			numeral.put( 'p',16);
			numeral.put( 'q',17);
			numeral.put( 'r',18);
			numeral.put( 's',19);
			numeral.put( 't',20);
			numeral.put( 'u',21);
			numeral.put( 'v',22);
			numeral.put( 'w',23);
			numeral.put( 'x',24);
			numeral.put( 'y',25);
			numeral.put( 'z',26);
			
			int num = 0;
			List<String> arr = new ArrayList<String>();
			
			String low = string.toLowerCase();
			
			for (int i = 0; i < string.length(); i++) {
				if (alpha.containsValue(low.charAt(i))) {
					num = numeral.get(low.charAt(i));
					num += key;
					if (num > 26) {
						num -= 26;
					}
					if (low.charAt(i) != string.charAt(i)) {
						arr.add((Character.toString(alpha.get(num))).toUpperCase());
					} else {
						arr.add(Character.toString(alpha.get(num)));
					}
				} else {
					arr.add(Character.toString(string.charAt(i)));
				}
			}
			
			String str = String.join("", arr);
			
			return str;
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

		int count = 0; // number of current primes
		int num = 1; // actual number being returned
		int j; // iterator
		
		while (count < i) {
			num++; // increment to next int
			for (j = 2; j <= num; j++) {
				if (num % j == 0) {
					if (j == num) {
						count++;
					}
					break;
				}
			}
		}
		
		if (count == 0) {
			throw new IllegalArgumentException();
		}
		
		return num;
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
			
			// a b c d e f g h i j k l m n o p q r s t u v w x y z
			// z y x w v u t s r q p o n m l k j i h g f e d c b a
			
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
			
			String s = String.join("", str);
			
			return s;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			
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
			
			char c;
			List<String> str = new ArrayList<String>();
			
			for (int i = 0; i < string.length(); i++) {
				c = string.charAt(i);
				if (encode.get(c) != null) {
					str.add( Character.toString( encode.get(c) ) );
				} else if (Character.isDigit(c)) {
					str.add(Character.toString(c));
				}
			}
			
			String s = String.join("", str);
			
			return s;
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
		int len = string.length();
		char c;
		boolean valid = false;
		int sum = 0;
		int count = 10;
		
		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (Character.isLetter(c)) {
				if (c != 'X') {
					valid = false;
				} else {
					sum = sum + 10;
				}
			} else if (Character.isDigit(c)) {
				sum += Character.getNumericValue(c) * count;
				count--;
			} else {}
		}
		
		if (sum % 11 == 0) {
			valid = true;
		} else {
			valid = false;
		}
		
		return valid;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γ�?άμμα, pan
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
		List<String> alpha = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
		List<String> check = new ArrayList<String>();
		
		for (int i = 0; i < string.length(); i++) {
			check.add(Character.toString(string.charAt(i)));
		}
		
		return check.containsAll(alpha);
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
		String type = given.getClass().getName();
		String tp = type.substring(10);
		System.out.println(tp);
		LocalDateTime newd;
		if (tp.equals("LocalDate")) {
			LocalDateTime forma = ((LocalDate) given).atStartOfDay();
			newd = forma.plusSeconds(1000000000L);
		} else {
			newd = ((LocalDateTime) given).plusSeconds(1000000000L);
		}
		
		return newd;
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
		
		int len = set.length;
		int sum = 0;
		int mult = 0;
		ArrayList<Integer> store = new ArrayList<Integer>();
		
		
		for (int j = 0; j < len; j++) { // iterate over values in the set
			for (int k = 1; ; k++) { // keep checking multiples
				mult = k * set[j];
				if (mult < i) { // if less than given number we add it to the sum
					if (store.contains(mult) != true) {
						store.add(mult);
						sum += mult;
					}
				} else {
					break; // else we break out and move onto to next value in the set
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
		// TODO Write an implementation for this method declaration
		
		char c;
		int sum = 0;
		int toAdd = 0;
		int place = 1; // every second number
		boolean luhn;
		
		for (int i = string.length() - 1; i >= 0; i--) {
			c = string.charAt(i);
			if (Character.isDigit(c)) {
				if (place%2 == 0) {
					//System.out.println("place "+place);
					toAdd = 2 * Character.getNumericValue(c);
					if (toAdd > 9) {
						toAdd -= 9;
					}
					sum += toAdd;
				} else {
					sum += Character.getNumericValue(c);
				}
				place++;
			} else if (c != ' ') {
				return false;
			}
		}
		
		if (sum % 10 == 0) {
			luhn = true;
		} else {
			luhn = false;
		}
		
		return luhn;
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
	
	// helper function to determine if a string is a number
	public boolean isNumber(String string) {
		boolean bool;
		try {
			int num = Integer.parseInt(string);
		} catch (NumberFormatException nfe) {
			return false;
		}
		
		return true;
	}
	
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		
		// Matching to operations
		String p = "plus";
		String s = "minus";
		String m = "multiplied";
		String d = "divided";
		char op = ' '; // operation
		
		int result = 0;
		
		String noQuestion = string.substring(0, string.length() - 1);
		String[] words = noQuestion.split(" "); // get each individual word
		List<Integer> numbers = new ArrayList<Integer>();
		int len = words.length;
		
		for (int i = 0; i < len; i++) {
			if (words[i].equals(p)) {op = 'p';}
			if (words[i].equals(s)) {op = 's';}
			if (words[i].equals(m)) {op = 'm';}
			if (words[i].equals(d)) {op = 'd';}
			
			if (isNumber(words[i])) {
				numbers.add(Integer.parseInt(words[i]));
			}
		}
		
		switch(op) {
			case 'p':
				result = numbers.get(0) + numbers.get(1);
				break;
			case 's':
				result = numbers.get(0) - numbers.get(1);
				break;
			case 'm':
				result = numbers.get(0) * numbers.get(1);
				break;
			case 'd':
				result = numbers.get(0) / numbers.get(1);
				break;
		}
		
		return result;
	}

}
