package feb27;

import java.util.Scanner;

public class ZuellerCongruenceAlg {
	static int year = -1;
	static int month = -1;
	static int day = -1;

	static int century;
	static int centuryYear;	
	static int dayOfWeek;
	
	private static void dateCheck() {
		Scanner yScanner, mScanner, dScanner;
		
		//Checking for an integer year.
				System.out.println("Enter year: ");
				do {
					yScanner = new Scanner(System.in);
					if (yScanner.hasNextInt()) {
						year = yScanner.nextInt();
					} 
					else {System.out.println("Sorry, that's not an integer. Please enter year:");}
				} while (year == -1);
				
				//Checking for an integer month. 3 is Mar, 4 is Apr,... 13 is Jan, 14 is Feb.
				System.out.println("Enter month:" );
				do {
					mScanner = new Scanner(System.in);
					if (mScanner.hasNextInt()) {
						month = mScanner.nextInt();
						if((month==1)||(month==2)) {
							month = month + 12;
						}
						else if((month<1)||(month>12)) {
							System.out.println("Sorry, that's not a number between 1 and 12. Please enter month:");
							month = -1;
						}
					} 
					else {System.out.println("Sorry, that's not an integer. Please enter month:");}
				} while (month == -1);
				
				//Checking for an integer day. Different possible days for different months.
				System.out.println("Enter day: " );
				do {
					dScanner = new Scanner(System.in);
					if (dScanner.hasNextInt()) {
						day = dScanner.nextInt();
						switch(month){
						case 4:
						case 6:
						case 9:
						case 11:
							if((day<1)||(day>30)) {
								System.out.println("Sorry, that's not a number between 1 and 30. Please enter day:");
								day = -1;
							}
							break;
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
						case 13:
							if((day<1)||(day>31)) {
								System.out.println("Sorry, that's not a number between 1 and 31. Please enter day:");
								day = -1;
							}
							break;
						case 14:
							if((day<1)||(day>28)) {
								System.out.println("Sorry, that's not a number between 1 and 28. Please enter day:");
								day = -1;
							}
							break;
						}
					} 
					else {System.out.println("Sorry, that's not an integer. Please enter day:");}
				} while (day == -1);

				yScanner.close();
				mScanner.close();
				dScanner.close();
	}

	public static void main(String[] args) {
		dateCheck();
		
		century = year/100;
		centuryYear = year%100;
		
		dayOfWeek = (day + (26*(month+1))/10 + centuryYear + centuryYear/4 + 5 - century)%7;
		
		String answer = "unknown";
		switch(dayOfWeek) {
			case 0:
				answer = "Saturday";
				break;
			case 1:
				answer = "Sunday";
				break;
			case 2:
				answer = "Monday";
				break;
			case 3:
				answer = "Tueday";
				break;
			case 4:
				answer = "Wednesday";
				break;
			case 5:
				answer = "Thursday";
				break;
			case 6:
				answer = "Friday";
				break;
				
		}
		System.out.println("Day of the week is " + answer);
	}

}
