/**
 * 
 */

/**
 * @author philip
 *
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Beginning {

	
		static int x = 5;
		int y = 10;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Beginning a = new Beginning();
		OtherClass z = new OtherClass();
		
		/*
		System.out.println(x);
		System.out.println(a.y);
		System.out.println("After the method call");
		a.test();
		System.out.println(a.x);
		System.out.println(a.y);
		System.out.println("Other class");
		
		System.out.println(z.f);
		System.out.println(z.g);
		*/
		
		String temp =  x > 10 ? "x is greater than 10" : "x is less than 10";
		String temp2 = x > 10 ? (x > 100 ? "x is greater than 100" : "x is less than 100") : "x is less than 10";
		
		//System.out.println(temp2);
		
		try {
			FileInputStream input = new FileInputStream("test.txt");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void test() {
		y = 20;
		x = 100;
	}

}