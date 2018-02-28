package com.revature.store;

public class IceCreamSandwich
{
	// 1. Variables
	private String flavor;
	private String expDate;
	private float price;
	
	// Constructor
	public IceCreamSandwich()
	{
		//this.flavor = "Vanilla";
		setFlavor(flavor);
		this.expDate = "Yesterday";
		this.price = 12.6f;
	}
	
	public IceCreamSandwich(String flavor)
	{
		this.flavor = flavor;
		this.expDate = "Yesterday";
		this.price = 12.6f;
	}
	
	public IceCreamSandwich(String flavor, String expDate)
	{
		this.expDate = expDate;
	}
	
	public IceCreamSandwich(float price, String flavor)
	{
		this.price = 12f;
	}
	
	public IceCreamSandwich(String flavor, float price)
	{
		this.price = 10f;
	}
	
	public IceCreamSandwich(int price, String flavor)
	{
		this.price = 11;
	}
	
	// Getters
	public String getFlavor()
	{
		return flavor;
		// or
		// return this.flavor;
	}
	
	public String getExpDate()
	{
		return expDate;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	// Setters
	/*public void setFlavor(String f)
	{
		this.flavor = f;
		// or
		// flavor = f;
	}*/
	
	public void setFlavor(String flavor)
	{
		this.flavor = flavor;
		// or
		// flavor = f;
	}
	
	public void setExpDate(String expDate)
	{
		this.expDate = expDate;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	// Overrides
	@Override
	public String toString()
	{
		return flavor + " ice cream sandwich";
	}
	
}