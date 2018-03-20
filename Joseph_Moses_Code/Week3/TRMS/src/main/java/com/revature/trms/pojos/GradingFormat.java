package com.revature.trms.pojos;

public class GradingFormat {

	private int gradingFormatId;
	private String gradingFormat;
	
	public GradingFormat(){}

	public GradingFormat(int gradingFormatId, String gradingFormat) {
		super();
		this.gradingFormatId = gradingFormatId;
		this.gradingFormat = gradingFormat;
	}

	public int getGradingFormatId() {
		return gradingFormatId;
	}

	public void setGradingFormatId(int gradingFormatId) {
		this.gradingFormatId = gradingFormatId;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	@Override
	public String toString() {
		return "GradingFormat [gradingFormatId=" + gradingFormatId + ", gradingFormat=" + gradingFormat + "]";
	}
	
	
}
