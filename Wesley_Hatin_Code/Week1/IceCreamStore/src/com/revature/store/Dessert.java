package com.revature.store;

public class Dessert {
	static int staticCounter = 0;
	public int instanceCounter = 0;
	
	public static void function() {
		Dessert d = new Dessert();
		int functionCounter = 0;
		
		{
			int blockCounter = 0;
		}
		System.out.println(d.instanceCounter++ + "asdf");
		System.out.println(d.instanceCounter++);
	}

	public static void main(String[] args) {
		IceCreamSandwich ics = new IceCreamSandwich();
		System.out.println("Here's a " + ics);
		System.out.println("It expires " + ics.getExpDate());
		System.out.println("That'll be $" + String.format("%1.2f",ics.getPrice()));
		
		
		
		System.out.println(staticCounter++);
		
		//variable scopes are important.
		if (1==2) {
			//asdf
		}
		else if (1>2){
			//asdf
		}
		else {
			//idk
		}
		
		int s=8;
		
		do {
			s--;
			System.out.println(s);
		} while (s>1);
		
		for(int i=0; i<9; i++) {
			if((i==0)||(i==8)) {
				System.out.print(" ");
				for(int j=0; j<13; j++) {
					System.out.print("_");
				}
			}
			else {
				System.out.print("|");
				for(int k=0; k<13; k++) {
					if(k<(7-i)) {
						System.out.print(" ");
					}
					else if(k>(5+i)) {
						System.out.print(" ");
					}
					else {System.out.print("a");}
					}
				System.out.print("|");
				}
			System.out.println("");
			}
	}

}
