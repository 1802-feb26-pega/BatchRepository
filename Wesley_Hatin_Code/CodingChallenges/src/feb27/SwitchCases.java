package feb27;

import java.util.*;

public class SwitchCases {

	public static void main(String[] args) {
		System.out.println("Enter a number 1-3: ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();

		switch(n) {
			case 1:
				//find the square root of a number x, read from console
				System.out.println("Enter a number to find the square root of: ");
				
				double x = scanner.nextFloat();
				scanner.nextLine();
				System.out.print("The square root of " + x);
				x = Math.sqrt(x);
				System.out.println(" is " + x + ".");
				break;
			case 2:
				//Display today's date
				Calendar time = Calendar.getInstance();
				Date today = time.getTime();
				System.out.println("Today's date is " + today);
				
				break;
			case 3:
				//split the following string and store in an array
				String specialString = "the following string";
				String[] strArray = specialString.split(" ");
				for(int i=0; i < strArray.length; i++) {
					System.out.println(strArray[i]);
				}
				break;
		}
		scanner.close();

	}

}
