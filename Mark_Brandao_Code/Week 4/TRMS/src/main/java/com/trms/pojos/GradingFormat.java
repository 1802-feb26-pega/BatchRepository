package com.trms.pojos;

public class GradingFormat {
	private int gradingFormatId;
	private String gradeFormatType;
	private short passingGrade;
	
	public GradingFormat() { }
	
	public GradingFormat(int gradingFormatId, String gradeFormatType, short passingGrade) {
		super();
		this.gradingFormatId = gradingFormatId;
		this.gradeFormatType = gradeFormatType;
		this.passingGrade = passingGrade;
	}
	public int getGradingFormatId() {
		return gradingFormatId;
	}
	public void setGradingFormatId(int gradingFormatId) {
		this.gradingFormatId = gradingFormatId;
	}
	public String getGradeFormatType() {
		return gradeFormatType;
	}
	public void setGradeFormatType(String gradeFormatType) {
		this.gradeFormatType = gradeFormatType;
	}
	public short getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(short passingGrade) {
		this.passingGrade = passingGrade;
	}
	@Override
	public String toString() {
		return "GradingFormat [gradingFormatId=" + gradingFormatId + ", gradeFormatType=" + gradeFormatType
				+ ", passingGrade=" + passingGrade + "]";
	}
}
