package com.ex.inheritance;

/*
 * Understanding inheritance in Java
 * https://www.geeksforgeeks.org/inheritance-in-java/
 */

public class BikeTest {

	public static void main(String[] args) {
		MountainBike mb = new MountainBike(3, 100, 25);
		System.out.println(mb.toString());
	}
}

//base class
class Bicycle{
	
	public int gear;
	public int speed;
	
	public Bicycle(int gear, int speed) {
		this.gear = gear;
		this.speed = speed;
	}
	
	public void applyBrake(int decrement) {
		speed -= decrement;
	}
	
	public void speedUp(int increment) {
		speed += increment;
	}
	
	public String toString() {
		return "There are " + gear + "s and the speed is " + speed;
	}
	
}

class MountainBike extends Bicycle{
	
	public int seatHeight;
	
	public MountainBike(int gear, int speed, int startHeight) {
		super(gear, speed);
		seatHeight = startHeight;
	}
	
	public void setHeight(int newValue) {
		seatHeight = newValue;
	}
	
	public String toString() {
		return super.toString() + " and height is " + seatHeight;
	}
}