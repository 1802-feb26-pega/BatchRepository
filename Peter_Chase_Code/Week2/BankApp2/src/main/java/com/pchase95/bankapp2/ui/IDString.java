package com.pchase95.bankapp2.ui;

class IDString {
	private long id;
	private String text;
	
	public IDString() { }
	
	public IDString(long id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; } 
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }


	@Override
	public String toString() {
		return text;
	}
}
