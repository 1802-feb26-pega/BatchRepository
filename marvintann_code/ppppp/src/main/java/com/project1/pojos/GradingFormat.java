package com.project1.pojos;

public class GradingFormat {
	private int formatId;
	private String formatType;
	
	public GradingFormat() {
		
	}

	public GradingFormat(int formatId, String formatType) {
		super();
		this.formatId = formatId;
		this.formatType = formatType;
	}

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}
	
	
}
