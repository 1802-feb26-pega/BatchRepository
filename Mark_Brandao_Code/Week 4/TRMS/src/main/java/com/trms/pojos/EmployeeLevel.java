package com.trms.pojos;

public class EmployeeLevel {
	private int levelId;
	private String levelDescription;
	
	public EmployeeLevel() { }
	
	public EmployeeLevel(int levelId, String levelDescription) {
		super();
		this.levelId = levelId;
		this.levelDescription = levelDescription;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getLevelDescription() {
		return levelDescription;
	}
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}
	@Override
	public String toString() {
		return "EmployeeLevel [levelId=" + levelId + ", levelDescription=" + levelDescription + "]";
	}
}
