package com.revature.store;

public class IceCreamSandwich {
	
	private String flavor;
	private String expDate;
	private Float price;
	
	public IceCreamSandwich() {
		this.flavor = "vanilla";
		this.expDate = "in a day or two.";
		this.price = (float) 5.00;		
	}
	
	public String getFlavor() {
		return flavor;
	}
	
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	
	public String getExpDate() {
		return expDate;
	}
	
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return flavor + " ice cream sandwich.";
	}

}
