package com.revature.eval;

// Name: Katelyn Peterson
// Date: Feb. 27th, 2018

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class EvaluationService
{

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string)
	{
		// TODO Write an implementation for this method declaration
		//System.out.println(string);
		
		// 1. Variables & 2. Input
		char[] initial = string.toCharArray();
		char[] reverse = new char[initial.length];
		int count = 0;
		
		/*TESTING
		 * for(int x = 0; x < initial.length; x++)
		{
			System.out.println(initial[x]);
		}
		System.out.println();*/
		
		// 3. Calculation
		for (int x = initial.length - 1; x >= 0; x--)
		{
			reverse[count] = initial[x];
			count = count + 1;
		}
		
		/*TESTING
		 * for(int x = 0; x < reverse.length; x++)
		{
			System.out.println(reverse[x]);
		}
		System.out.println();*/
		
		string = new String(reverse);
		
		// 4. Output
		return string;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase)
	{
		// TODO Write an implementation for this method declaration
		
		// 1. Variables & 2. Input
		String[] initial = phrase.split(" |-");
		char[] transfer = new char[initial.length];
		String acron;
		
		//TESTING
		/*System.out.println(initial.length);
		for (String x : initial)
		{
			System.out.println(x);
		}*/
		
		// 3. Calculation
		for (int x = 0; x < initial.length; x++)
		{
			transfer[x] = Character.toUpperCase(initial[x].charAt(0));
			
		}
		
		/*TESTING
		 * for (char x : transfer)
		{
			System.out.println(x);
		}*/
		
		acron = new String(transfer);
		
		// 4. Output		
		return acron;
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
	static class Triangle
	{
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle()
		{
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree)
		{
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne()
		{
			return sideOne;
		}

		public void setSideOne(double sideOne)
		{
			this.sideOne = sideOne;
		}

		public double getSideTwo()
		{
			return sideTwo;
		}

		public void setSideTwo(double sideTwo)
		{
			this.sideTwo = sideTwo;
		}

		public double getSideThree()
		{
			return sideThree;
		}

		public void setSideThree(double sideThree)
		{
			this.sideThree = sideThree;
		}

		public boolean isEquilateral()
		{
			// TODO Write an implementation for this method declaration
			if (Double.compare(this.sideOne, this.sideTwo) == 0 && Double.compare(this.sideOne, this.sideThree) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		public boolean isIsosceles() 
		{
			// TODO Write an implementation for this method declaration
			//if (Double.compare(this.sideOne, this.sideTwo) == 0 && Double.compare(this.sideOne, this.sideThree) == 0)
			if (this.isEquilateral())
			{
				return false;
			}
			else if (Double.compare(this.sideOne, this.sideTwo) == 0 | Double.compare(this.sideOne, this.sideThree) == 0 
					| Double.compare(this.sideTwo, this.sideThree) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		public boolean isScalene() 
		{
			// TODO Write an implementation for this method declaration
			if (this.isEquilateral())
			{
				return false;
			}
			else if (this.isIsosceles())
			{
				return false;
			}
			else
			{
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
	public int getScrabbleScore(String string)
	{
		// TODO Write an implementation for this method declaration
		
		// 1. Variables & 2. Input
		char[] input = string.toCharArray();
		int score = 0;
		int totalScore = 0;
		int multiplier = 1;
		char currChar = ' ';
		
		// TEST
		/*System.out.println("Before Sort");
		for (char x : input)
		{
			System.out.println(x);
		}*/
		
		// 3. Calculation
		Arrays.sort(input);
		
		// TEST
		/*System.out.println("After Sort");
		for (char x : input)
		{
			System.out.println(x);
		}*/
		
		for (int x = 0; x < input.length; x++)
		{
			// first x into static character to check for multiplier
			if (x == 0)
			{
				currChar = Character.toUpperCase(input[x]);
			}
			// increase multiplier
			else if (currChar == Character.toUpperCase(input[x]))
			{
				multiplier = multiplier + 1;
			}
			// applying multiplier and score to total score; resetting currChar and multiplier
			else if (currChar != Character.toUpperCase(input[x]))
			{
				// Total Score
				totalScore = totalScore + (multiplier * score);
				
				// Reset variables
				multiplier = 1;
				currChar = Character.toUpperCase(input[x]);
			}
			
			if (Character.toUpperCase(input[x]) == 'A' 
					| Character.toUpperCase(input[x]) == 'E' 
					| Character.toUpperCase(input[x]) == 'I'
					| Character.toUpperCase(input[x]) == 'O'
					| Character.toUpperCase(input[x]) == 'U'
					| Character.toUpperCase(input[x]) == 'L'
					| Character.toUpperCase(input[x]) == 'N'
					| Character.toUpperCase(input[x]) == 'R'
					| Character.toUpperCase(input[x]) == 'S'
					| Character.toUpperCase(input[x]) == 'T')
			{
				// Setting score
				score = 1;
			}
			else if(Character.toUpperCase(input[x]) == 'D' 
					| Character.toUpperCase(input[x]) == 'G')
			{
				// Setting score
				score = 2;
			}
			else if(Character.toUpperCase(input[x]) == 'B' 
					| Character.toUpperCase(input[x]) == 'C'
					| Character.toUpperCase(input[x]) == 'M'
					| Character.toUpperCase(input[x]) == 'P')
			{
				// Setting score
				score = 3;
			}
			else if(Character.toUpperCase(input[x]) == 'F' 
					| Character.toUpperCase(input[x]) == 'H'
					| Character.toUpperCase(input[x]) == 'V'
					| Character.toUpperCase(input[x]) == 'W'
					| Character.toUpperCase(input[x]) == 'Y')
			{
				// Setting score
				score = 4;
			}
			else if(Character.toUpperCase(input[x]) == 'K')
			{
				// Setting score
				score = 5;
			}
			else if(Character.toUpperCase(input[x]) == 'J' 
					| Character.toUpperCase(input[x]) == 'X')
			{
				// Setting score
				score = 8;
			}
			else if(Character.toUpperCase(input[x]) == 'Q' 
					| Character.toUpperCase(input[x]) == 'Z')
			{
				// Setting score
				score = 10;
			}
			else
			{
				// Number, not letter
				score = 0;
			}
			
			// TESTING
			/*System.out.println("Before IF");
			System.out.println("X " + x);
			System.out.println("Input " + input[x]);
			System.out.println("currChar " + currChar);
			System.out.println("multiplier " + multiplier);
			System.out.println("score " + score);
			System.out.println("totalScore " + totalScore);*/
			
			if (x == input.length - 1 && currChar == Character.toUpperCase(input[x]))
			{
				// Total Score
				totalScore = totalScore + (multiplier * score);
			}
			
			// TESTING
			/*System.out.println("After IF");
			System.out.println("X " + x);
			System.out.println("Input " + input[x]);
			System.out.println("currChar " + currChar);
			System.out.println("multiplier " + multiplier);
			System.out.println("score " + score);
			System.out.println("totalScore " + totalScore);*/
			
		}
		
		// 4. Output
		return totalScore;
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
		
		// 1. Variables & 2. Input
		String temp = string.trim();
		char[] input = temp.toCharArray();
		ArrayList<Character> cleanData = new ArrayList<>();
		int count = 0;
		String returnData = null;
		
		//char[] numTransfer = new char[10];
		
		// 3. Calculation		
		// Test for any illegal characters
		for (char x : input)
		{
			//System.out.println(x);
			if (Character.isDigit(x) | x == ' ' | x == '.' | x == '(' | x == ')' | x == '-')
			{
				//System.out.println(x);
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		
		for (char x : input)
		{
			if(Character.isDigit((x)))
			{
				cleanData.add(x);
			}
		}
		
		input = new char[cleanData.size()];
				
		Iterator<Character> iter = cleanData.iterator();
		
		while (iter.hasNext())
		{
			input[count] = iter.next();
			count++;
			if (count > 10)
			{
				throw new IllegalArgumentException();
			}
		}
		
		//System.out.println(input);
		returnData = new String(input);
		

		
		// 4. Output
		//System.out.println(returnData);
		
		return returnData;
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
		
		// 1. Variables & 2. Input
		String regex = " |,\\n|,";
		String[] initial = string.split(regex);
		TreeMap<String, Integer> data = new TreeMap<>();
		
		// 3. Calculation
		if (initial.length > 0)
		{
			data.put(initial[0], 1);
			//System.out.println("0 " + initial[0]);
			
			for (int x = 1; x < initial.length; x++)
			{
				//System.out.println(x + " " + initial[x]);
				if (data.containsKey(initial[x]))
				{
					// Trying to add new value to map
					data.replace(initial[x], data.get(initial[x]), data.get(initial[x]) + 1);
					//System.out.println(data.get(initial[x]));
				}
				/*else if (initial[x] == "\\n")
				{
					
				}*/
				else
				{
					data.put(initial[x], 1);
					//System.out.println(data.get(initial[x]));
				}
			}
			//System.out.println(data.toString());
			
			return data;
		}
		else
		{
			// 4. Output
			return null;
		}
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
	static class BinarySearch<T>
	{
		private List<T> sortedList;

		public int indexOf(T t)
		{
			if (!sortedList.isEmpty())
			{
				// 1. Variables & 2. Input
				int high = sortedList.size() - 1;
				int low = 0;
				int mid = 0;
				String transfer = String.valueOf(t);
				int search = Integer.valueOf(transfer);
				
				while (low <= high)
				{
					mid = low + (high - low)/2;
					
					// TESTING
					/*System.out.println("Search " + search);
					System.out.println("Mid " + mid);
					System.out.println("List " + sortedList.get(mid));
					System.out.println(Integer.valueOf(String.valueOf(sortedList.get(mid))));
					System.out.println();*/
					
					// 3. Calculation & Output
					if (Integer.valueOf(String.valueOf(sortedList.get(mid))).equals(search))
					{
						return mid;
						//high = mid - 1;
					}
					else if (search > Integer.valueOf(String.valueOf(sortedList.get(mid))))
					{
						low = mid + 1;
					}
					else
					{
						high = mid - 1;
					}
				}
				return mid;
			}
			else
			{
				return 0;
			}
		}

		public BinarySearch(List<T> sortedList)
		{
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList()
		{
			return sortedList;
		}

		public void setSortedList(List<T> sortedList)
		{
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
		
		// 1. Variables & 2. Input
		String[] initial = string.split(" ");
		//String[] output = new String[initial.length];
		String answer = "";
		
		// 3. Calculation
		for (int x = 0; x < initial.length; x++)
		{
			//System.out.println(x);
			if (initial[x].startsWith("a") | initial[x].startsWith("e") | initial[x].startsWith("i") | 
					initial[x].startsWith("o") | initial[x].startsWith("u"))
			{
				answer = answer + initial[x].concat("ay");
			}
			else if (initial[x].substring(0, 3).matches("sch"))
			{
				String temp = "sch";
				answer = answer + initial[x].substring(3) + temp + "ay";
				//System.out.println(answer);
			}
			else if (initial[x].substring(0, 2).matches("th"))
			{
				String temp = "th";
				answer = answer + initial[x].substring(2) + temp + "ay";
				//System.out.println(answer);
			}
			else if (initial[x].substring(0, 2).matches("qu"))
			{
				String temp = "qu";
				answer = answer + initial[x].substring(2) + temp + "ay";
				//System.out.println(answer);
			}
			else
			{
				char temp = initial[x].charAt(0);
				answer = answer + initial[x].substring(1) + temp + "ay";
			}
			
			if (initial.length > 1 & x < (initial.length - 1))
			{
				answer = answer + " ";
			}
		}
		
		// 4. Output
		//System.out.println(answer);
		return answer;
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
		
		// 1. Variables & 2. Input
		int digits = (int)(Math.log10(input)+1);
		int digitTrans = input;
		ArrayList<Integer> transfer = new ArrayList<>();
		int sum = 0;
		
		// 3. Calculation
		// Extracting the digits from the input via digitTrans
		LinkedList<Integer> stack = new LinkedList<>();
		while (digitTrans > 0)
		{
		    stack.push( digitTrans % 10 );
		    digitTrans = digitTrans / 10;
		}
		
		// Reordering digits
		while (!stack.isEmpty())
		{
		    transfer.add(stack.pop());
		}
		
		for(Integer x : transfer)
		{
			sum = sum + (int)Math.pow(x, digits);
		}
		
		// 4. Output
		if (sum == input)
		{
			return true;
		}
		else
		{
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
		
		// 1. Variables & 2. Input
		long input = l;
		long remainder;
		long currPrime;
		LinkedList<Long> primes = new LinkedList<>();
		ArrayList<Long> nums = new ArrayList<>();
		
		// 3. Calculation
		// Populate the primes
		for (int x = 2; x < (input + 1); x++)
		{
			if (input % x == 0)
			{
				primes.add((long) x);
			}
		}
		
		// TESTING
		//System.out.println("Primes: " + primes);
		
		// Begin with lowest number.  If num X % Prime = 0, then add prime to nums list.  
		// While num != any primes, keep running
		//while(!primes.isEmpty() && !primes.contains(input))
		//{
			
			do
			{
				// Set current Prime to be checked
				currPrime = primes.peekFirst();
				remainder = input % currPrime;
				
				// TESTING
				/*System.out.println("Before If");
				System.out.println("Input: " + input);
				System.out.println("currPrime: " + currPrime);
				System.out.println("Remainder: " + remainder);
				System.out.println();*/
				
				if (remainder == 0)
				{
					// TESTING
					/*System.out.println("In If");
					System.out.println("Input: " + input);
					System.out.println("currPrime: " + currPrime);
					System.out.println("Remainder: " + remainder);
					System.out.println();*/
					
					// Add prime to nums
					nums.add((currPrime));
					
					// reduce input
					input = input / currPrime;
					
					// TESTING
					/*System.out.println("In If, after input update");
					System.out.println("Input: " + input);
					System.out.println("currPrime: " + currPrime);
					System.out.println("Remainder: " + remainder);
					System.out.println();*/
				}
				else
				{
					primes.removeFirst();
					
					// TESTING
					//System.out.println("In Else");
				}
				
			} while(!primes.isEmpty());
		//}
		
		// 4. Output
		// TESTING
		//System.out.println("Nums: " + nums);
		
		if (nums.size() == 1)
		{
			List<Long> oneNum = new ArrayList<>();
			oneNum = Collections.singletonList(nums.get(0));
			
			return oneNum;
		}
		
		return nums;
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
	static class RotationalCipher
	{
		private int key;
		private List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
				'y', 'z');

		public RotationalCipher(int key)
		{
			super();
			this.key = key;
		}

		public String rotate(String string)
		{
			// TODO Write an implementation for this method declaration
			
			// 1. Variables & 2. Input
			char[] input = string.toCharArray();
			char[] output = new char[input.length];
			int inKey;
			int newKey;
			String result;
			
			// TESTING
			/*System.out.println("Before Loop");
			System.out.print("Input: ");
			for (char x : input)
			{
				System.out.print(x);
			}
			System.out.println();
			System.out.println();*/
			
			// 3. Calculation
			for(int x = 0; x < input.length; x++)
			{
				if (Character.isLetter(input[x]))
				{
					// Set keys
					inKey = alphabet.indexOf(Character.toLowerCase(input[x]));
					newKey = inKey + key;
					
					if (newKey >= alphabet.size())
					{
						newKey = newKey - 26;
					}
					
					if (Character.isUpperCase(input[x]))
					{
						output[x] = Character.toUpperCase(alphabet.get(newKey));
					}
					else
					{
						output[x] = alphabet.get(newKey);
					}
				}
				else
				{
					output[x] = input[x];
				}
			}
			
			// 4. Output
			result = new String(output);
			
			// TESTING
			/*System.out.println("After Loop");
			System.out.print("Input: ");
			for (char x : input)
			{
				System.out.print(x);
			}
			System.out.println();
			System.out.print("Output: ");
			for (char x : output)
			{
				System.out.print(x);
			}
			System.out.println();
			System.out.println("Result: " + result);
			System.out.println();*/
			
			return result;
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
	public int calculateNthPrime(int input)
	{
		// TODO Write an implementation for this method declaration
		// So, need to run a loop, calculating primes until we have calculated N primes
		// Also, need to throw an IllegalArgument exception if N is 0
		
		// Throwing exception is N is 0
		if (input == 0)
		{
			throw new IllegalArgumentException();
		}
		
		// 1. Variables & 2. Input
		//int [] primeNums = new int [input];
		// Adjust to deal with ArrayList
		ArrayList<Integer> primeNums = new ArrayList<>();
		int counter = 0;
		int prime = 2;
		//int arrSize = 0;
		
		// 3. Calculation
		do
		{
			if (prime == 2)
			{
				//primeNums[counter] = prime;
				primeNums.add(prime);
				counter = counter + 1;
			}
			//else if (!(prime % 2 == 0))
			else if (prime == 3)
			{
				primeNums.add(prime);
				counter = counter + 1;
			}
			else
			{
				if (prime % 2 != 0 && prime % 3 != 0)
				{
					int i = 5;
					int w = 2;
					boolean isPrime = true;
					
					while(i * i <= prime)
					{
						if(prime % i == 0)
						{
							isPrime = false;
						}
						
						i = i + w;
						w = 6 - w;
					}
					
					if(isPrime)
					{
						primeNums.add(prime);
						//primeNums[x] = prime;
						counter = counter + 1;
					}					
					
				}
			}
			
			// increment prime
			prime = prime + 1;
			
			// TESTING
			/*System.out.println("Counter: " + counter);
			System.out.println("Prime: " + prime);
			//System.out.println("primeNums size: " + primeNums.size());
			System.out.println();*/
			
		} while (counter <= input);
		
		// 4. Output
		// TESTING
		/*System.out.println("primeNums: ");
		for (int x : primeNums)
		{
			System.out.println(x);
		}
		System.out.println();*/
		
		return primeNums.get(input - 1);
		//return primeNums[input - 1];
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
	static class AtbashCipher
	{
		// Variables
		private static List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
				'y', 'z');
		private static List<Character> atbash = Arrays.asList('z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 
				'q', 'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a');

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string)
		{
			// TODO Write an implementation for this method declaration
			
			// 1. Variables & 2. Input
			char[] input = string.toCharArray();
			//char[] output = new char[input.length];
			ArrayList<Character> output = new ArrayList<>();
			int inKey;
			int outSize;
			int counter = 0;
			String result = "";
			
			for(int x = 0; x < input.length; x++)
			{
				if (Character.isLetter(input[x]))
				{
					// Set keys
					inKey = alphabet.indexOf(Character.toLowerCase(input[x]));
					//newKey = inKey + key;
					
					/*if (newKey >= alphabet.size())
					{
						newKey = newKey - 26;
					}*/
					
					output.add(atbash.get(inKey));
				}
				else if (Character.isDigit(input[x]))
				{
					output.add(input[x]);
				}
			}
			
			outSize = output.size();
			
			do
			{
				for (int x = 0; x < 5; x++)
				{
					if (counter < outSize)
					{
						result = result + output.get(counter);
						
						if (x == 4 & output.get(counter) != output.get(outSize - 1))
						{
							result = result + " ";
						}
						
						counter = counter + 1;
					}
				}
				
			}while(counter < outSize);
			
			//System.out.println("Result: " + result);
			
			return result;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string)
		{
			// TODO Write an implementation for this method declaration
			// 1. Variables & 2. Input
			char[] input = string.toCharArray();
			char[] output = new char[input.length];
			//ArrayList<Character> output = new ArrayList<>();
			int inKey;
			//int outSize;
			//int counter = 0;
			String result = "";
			
			for(int x = 0; x < input.length; x++)
			{
				if (Character.isLetter(input[x]))
				{
					// Set keys
					inKey = atbash.indexOf(Character.toLowerCase(input[x]));
					//newKey = inKey + key;
					
					/*if (newKey >= alphabet.size())
					{
						newKey = newKey - 26;
					}*/
					
					//output.add(alphabet.get(inKey));
					output[x] = alphabet.get(inKey);
				}
				else if (Character.isDigit(input[x]))
				{
					//output.add(input[x]);
					output[x] = input[x];
				}
			}
			
			for(int x = 0; x < output.length; x++)
			{
				if (Character.isLetterOrDigit(output[x]))
				{
					result = result + output[x];
				}
			}
			
			return result;
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
	public boolean isValidIsbn(String string)
	{
		// TODO Write an implementation for this method declaration
		
		// 1. Variables & 2. Input
		char[] input = string.toCharArray();
		int isbnMod = 10;
		int result = 0;
		
		// 3. Calculation
		for(int x = 0; x < input.length; x++)
		{
			if (isbnMod < 1)
			{
				return false;
			}
			else if(Character.isDigit(input[x]))
			{
				result = result + (Integer.parseInt(Character.toString(input[x])) * isbnMod);
				isbnMod = isbnMod - 1;
			}
			else if (input[x] == 'X')
			{
				result = result + (10 * isbnMod);
				isbnMod = isbnMod - 1;
			}
			else if (input[x] == '-')
			{
				
			}
			else
			{
				return false;
			}
		}
		
		// 4. Output
		return result % 11 == 0;
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
	public boolean isPangram(String string)
	{
		// TODO Write an implementation for this method declaration
		
		// Check for empty string
		if (string == "")
		{
			return false;
		}
		
		// 1. Variables
		TreeSet<Character> alphabet = new TreeSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
				'y', 'z'));
		TreeSet<Character> tester = new TreeSet<>();
		char[] input = string.toCharArray();
		
		// 2. Input
		for(int x = 0; x < input.length; x++)
		{
			if(Character.isLetter(input[x]))
			{
				tester.add(input[x]);
			}
		}
		
		// 3. Calculation & 4. Output
		if(alphabet.equals(tester))
		{
			return true;
		}
		else
		{
			return false;
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
	public Temporal getGigasecondDate(Temporal given)
	{
		// TODO Write an implementation for this method declaration
		
		// 1. Variables
		/*int year;
		int month;
		int date;
		int hour;
		int min;
		int second;*/
		long gigasecond = (long) Math.pow(10, 9);
		LocalDateTime input;
		LocalDateTime output;
		String[] tester = given.toString().split("-|:");
		
		//LocalDateTime input = LocalDateTime.of(given, time);
		
		// 2. Input
		// Plan
		//System.out.println("Given: " + given.toString());
		
		
		// 3. Calculation & 4. Output
		if(tester.length <= 3)
		{
			LocalTime time = LocalTime.of(0, 0 ,0);
			input = LocalDateTime.of((LocalDate) given, time);
		}
		else
		{
			input = (LocalDateTime) given;
		}
		
		// 4. Output
		output = input.plus(gigasecond, ChronoUnit.SECONDS);
		
		return output;
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
	 * @param num
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int num, int[] set)
	{
		// TODO Write an implementation for this method declaration
		
		// 1. Variables
		TreeSet<Integer> multiples = new TreeSet<>();
		int sum = 0;
		
		// 2. Input
		for (int x = 1; x < num; x++)
		{
			for (int y = 0; y < set.length; y++)
			{
				if (x % set[y] == 0)
				{
					multiples.add(x);
				}
			}
		}
		
		// 3. Calculation
		for(Integer x : multiples)
		{
			sum = sum + x;
		}
		
		// 4. Output
		
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
	public boolean isLuhnValid(String string)
	{
		// TODO Write an implementation for this method declaration
		
		if(string.length() < 2)
		{
			return false;
		}
		
		// 1. Variables
		char[] input = string.toCharArray();
		ArrayList<Integer> transfer = new ArrayList<>();
		int temp = 0;
		int sum = 0;
		
		// 2. Input
		for (char x : input)
		{
			if(!Character.isDigit(x) & x != ' ')
			{
				//System.out.println("Here");
				return false;
			}
			else
			{
				if(Character.isDigit(x))
				{
					/*temp = Character.getNumericValue(x) * 2;
					System.out.println(temp);
					
					if(temp > 9)
					{
						temp = temp - 9;
					}
					
					transfer.add(temp);*/
					transfer.add(Character.getNumericValue(x));
				}
			}
		}
		
		// 3. Calculation
		for (int x = 0; x < transfer.size(); x++)
		{
			if(x % 2 != 0)
			{
				temp = transfer.get(x) * 2;
				
				if(temp > 9)
				{
					temp = temp - 9;
				}
				sum = sum + temp;
			}
			else
			{
				sum = sum + transfer.get(x);
			}
			
			//sum = sum + x;
			//System.out.println("Transfer: " + x);
		}
		
		//System.out.println("Sum: " + sum);
		
		// 4. Output		
		return sum % 10 == 0;
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
	public int solveWordProblem(String string)
	{
		// TODO Write an implementation for this method declaration
		
		// 1. Variables & 2. Input
		String[] input = string.split(" |[?]");
		
		// TESTING
		/*for (String x : input)
		{
			System.out.println(x);
		}
		System.out.println();*/
		
		int firstNum = Integer.parseInt(input[2]);
		//int secondNum = Integer.parseInt(input[4]);
		int secondNum = 0;
		int sum = 0;
		
		// 3. Calculation
		for(String x : input)
		{
			if(x.matches("-?\\d+(\\.\\d+)?"))
			{
				secondNum = Integer.parseInt(x);
			}
		}
		
		if (input[3].matches("plus"))
		{
			sum = firstNum + secondNum;
		}
		else if(input[3].matches("minus"))
		{
			sum = firstNum - secondNum;
		}
		else if(input[3].matches("multiplied"))
		{
			sum = firstNum * secondNum;
		}
		else if(input[3].matches("divided"))
		{
			sum = firstNum / secondNum;
		}
		
		// 4. Output
		return sum;
	}

}