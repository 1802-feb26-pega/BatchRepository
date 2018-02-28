package com.revature.store;


//Class is also called Java bean

public class IceCreamSandwhich {

	private String flavor;
	private String expDate;
	private float Price;
	
/*	
 * This would throw a problem in Dessert.java because since I have a constructor of the same name of the class with a argument
 * it would not give me a default constructor 
 * 
	public IceCreamSandwhich(String flavor) {
		this.flavor  = flavor;
		this.expDate = "Today";
		this.Price = 12.6f;
	}
	*/
	public IceCreamSandwhich() {
		this.flavor  = "Vanilla";
		this.expDate = "Today";
		this.Price = 12.6f;
	}
	
	public String getFlavor() {
		return this.flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
		
	}
	
	public String getExpDate() {
		return this.expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
		
	}
	
	public float getPrice() {
		return this.Price;
	}
	public void setPrice(float price) {
		this.Price = price;
		
	}
	
	@Override // Annontations
	public String toString() {
		return this.flavor + " ice cream sandwhich";
	}
}
