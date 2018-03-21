package com.pchase95.project1.pojos;

import java.sql.Date;

public class Reimbursment implements TrmsObject {
	private long id;
	private ReimbursmentType type;
	private GradingFormat gradingFormat;
	private Location eventLocation;
	private Date eventDate;
	private Date courseDate;
	private String description;
	private double cost;
	private double rewardAmount;
	private Date submissionDate;
	
	public Reimbursment() {
		
	}
	
	

	@Override
	public String toString() {
		return "Reimbursment [id=" + id + ", type=" + type + ", gradingFormat=" + gradingFormat + ", eventLocation="
				+ eventLocation + ", eventDate=" + eventDate + ", courseDate=" + courseDate + ", description="
				+ description + ", cost=" + cost + ", rewardAmount=" + rewardAmount + ", submissionDate="
				+ submissionDate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursment other = (Reimbursment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ReimbursmentType getType() {
		return type;
	}

	public void setType(ReimbursmentType type) {
		this.type = type;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public Location getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(Location eventLocation) {
		this.eventLocation = eventLocation;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	
}
