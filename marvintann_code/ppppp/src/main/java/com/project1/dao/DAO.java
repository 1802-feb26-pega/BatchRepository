package com.project1.dao;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.project1.pojos.Address;
import com.project1.pojos.Attachment;
import com.project1.pojos.Department;
import com.project1.pojos.Employee;
import com.project1.pojos.Event;
import com.project1.pojos.EventDetails;
import com.project1.pojos.EventLocation;
import com.project1.pojos.GradingFormat;
import com.project1.pojos.Request;
import com.project1.pojos.RequestStatus;
import com.project1.pojos.Requests;
import com.project1.pojos.Title;

public interface DAO {
	
	public Requests addRequests(int employeeId, String requestsStatus, String requestsType, String requestsGradingFormat,
			String requestsDate, String requestsLocation, double requestsCost, String requestsDescription,
			String requestsJustification, int requestsDaysMissed, String requestsAttachments);
	public ArrayList<Requests> getRequests(int employeeId);
	
	
	public Address addAddress(int employeeId, String street, String city, String stateName, int zip);
	public Address getAddressById(int addressId);
	
	public Attachment addAttachment(int requestId, Blob attachment);
	public Attachment getAttachmentById(int attachmentId);
	
	// using a lookup table so no need to add anymore departments
	public Department getDepartmentById(int departmentId);
	
	public Employee addEmployee(int titleId, int departmentId, String firstName, String lastName, 
			String email, String pwd, int reportsTo);
	public Employee getEmployeeById(int employeeId);
	public Employee getEmployeeByEmail(String email);
	
	public Event addEvent(String eventName, int formatId);
	public Event getEventById(int eventId);
	
	public EventDetails addEventDetails(int requestId, int eventId, String eventDate, String eventTime, 
			double eventCost, String eventDescription, String justification, int daysMissed);
	public EventDetails getEventDetailsById(int eventDetailsId);
	
	public EventLocation addEventLocation(int eventDetailsId, String street, String city, String stateName, int zip);
	public EventLocation getEventLocationById(int eventLocationId);
	
	// look up table for grading formats so do not need to add anymore
	public GradingFormat getFormatById(int formatId);
	
	public Request addRequest(int employeeId, Timestamp requestTime);
	public Request getRequestById(int requestId);
	public ArrayList<Request> getAllRequests(int employeeId);
	
	public RequestStatus addRequestStatus(int requestId, String requestStatusType);
	public RequestStatus getRequestStatusById(int requestStatusId);
	
	// using a lookup table for title
	public Title getTitleById(int titleId);
	
	
}
