package com.project1.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.project1.util.ConnectionFactory;


public class DAOImplement implements DAO{
	
	@Override
	public Requests addRequests(int employeeId, String requestsStatus, String requestsType, String requestsGradingFormat,
			String requestsDate, String requestsLocation, double requestsCost, String requestsDescription,
			String requestsJustification, int requestsDaysMissed, String requestsAttachments) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			DAO dao = new DAOImplement();

			// just using a sequence called in the sql statement itself instead of a trigger to auto increment
			String sql = "INSERT INTO requests VALUES (requests_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			String[] key = new String[1];
			key[0] = "address_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, employeeId);
			ps.setString(2, requestsStatus);
			ps.setString(3, requestsType);
			ps.setString(4, requestsGradingFormat);
			ps.setString(5, requestsDate);
			ps.setString(6, requestsLocation);
			ps.setDouble(7, requestsCost);
			ps.setString(8, requestsDescription);
			ps.setString(9, requestsJustification);
			ps.setInt(10, requestsDaysMissed);
			ps.setString(11, requestsAttachments);

			ps.executeUpdate();

			conn.commit();
			Requests a = new Requests(0, employeeId, requestsStatus, requestsType, requestsGradingFormat, requestsDate,
					requestsLocation, requestsCost, requestsDescription, requestsJustification, requestsDaysMissed,
					requestsAttachments);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public ArrayList<Requests> getRequests(int employeeId) {
		
		ArrayList<Requests> arr = new ArrayList<Requests>();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM requests WHERE employee_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Requests a = new Requests();
				a.setRequestsId(rs.getInt(1));
				a.setEmployeeId(rs.getInt(2));
				a.setRequestsStatus(rs.getString(3));
				a.setRequestsType(rs.getString(4));
				a.setRequestsGradingFormat(rs.getString(5));
				a.setRequestsDate(rs.getString(6));
				a.setRequestsLocation(rs.getString(7));
				a.setRequestsCost(rs.getDouble(8));
				a.setRequestsDescription(rs.getString(9));
				a.setRequestsJustification(rs.getString(10));
				a.setRequestsDaysMissed(rs.getInt(11));
				a.setRequestsAttachments((String)rs.getObject(12));
				arr.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arr;
	}
	
	@Override
	public Address addAddress(int employeeId, String street, String city, String stateName, int zip) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			DAO dao = new DAOImplement();
			
			// just using a sequence called in the sql statement itself instead of a trigger to auto increment
			String sql = "INSERT INTO address VALUES (?, ?, ?, ?, ?)";
			
			String[] key = new String[1];
			key[0] = "address_id";
			
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, dao.getEmployeeById(employeeId).getAddressId());
			ps.setString(2, street);
			ps.setString(3, city);
			ps.setString(4, stateName);
			ps.setInt(5, zip);
			
			ps.executeUpdate();
			
			conn.commit();
			Address a = new Address(dao.getEmployeeById(employeeId).getAddressId(), employeeId, street, city, 
					stateName, zip);
			return a;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return null;
	}

	@Override
	public Address getAddressById(int addressId) {

		Address address = new Address();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM address WHERE address_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, addressId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				address.setAddressId(rs.getInt(1));
				address.setStreet(rs.getString(2));
				address.setCity(rs.getString(3));
				address.setStateName(rs.getString(4));
				address.setZip(rs.getInt(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return address;
	}

	@Override
	public Attachment addAttachment(int requestId, Blob attachment) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO attachment VALUES (attachment_seq.NEXTVAL, ?, ?)";

			String[] key = new String[1];
			key[0] = "attachment_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, requestId);
			ps.setBlob(2, attachment);

			ps.executeUpdate();

			conn.commit();
			Attachment a = new Attachment(0, requestId, attachment);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Attachment getAttachmentById(int attachmentId) {
		Attachment a = new Attachment();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM attachment WHERE attachment_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, attachmentId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setAttachmentId(rs.getInt(1));
				a.setRequestId(rs.getInt(2));
				a.setAttachment(rs.getBlob(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public Department getDepartmentById(int departmentId) {
		
		Department a = new Department();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM department WHERE department_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, departmentId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setDepartmentId(rs.getInt(1));
				a.setDepartmentName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public Employee addEmployee(int titleId, int departmentId, String firstName, String lastName,  
			String email, String pwd, int reportsTo) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO employee VALUES (employee_seq.NEXTVAL, ?, ?, ?, ?, address_seq.NEXTVAL, ?, ?, ?)";

			String[] key = new String[1];
			key[0] = "employee_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, titleId);
			ps.setInt(2, departmentId);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			//ps.setInt(5, addressId);
			ps.setString(5, email);
			ps.setString(6, pwd);
			ps.setInt(7, reportsTo);

			ps.executeUpdate();

			conn.commit();
			Employee a = new Employee(0, titleId, departmentId, firstName, lastName, 0, email, pwd, reportsTo);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		
		Employee a = new Employee();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM employee WHERE employee_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setEmployeeId(rs.getInt(1));
				a.setTitleId(rs.getInt(2));
				a.setDepartmentId(rs.getInt(3));
				a.setFirstName(rs.getString(4));
				a.setLastName(rs.getString(5));
				a.setAddressId(rs.getInt(6));
				a.setEmail(rs.getString(7));
				a.setPwd(rs.getString(8));
				a.setReportsTo(rs.getInt(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}
	
	@Override
	public Employee getEmployeeByEmail(String email) {
		
		Employee a = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM employee WHERE email = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				a.setEmployeeId(rs.getInt(1));
				a.setTitleId(rs.getInt(2));
				a.setDepartmentId(rs.getInt(3));
				a.setFirstName(rs.getString(4));
				a.setLastName(rs.getString(5));
				a.setAddressId(rs.getInt(6));
				a.setEmail(rs.getString(7));
				a.setPwd(rs.getString(8));
				a.setReportsTo(rs.getInt(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (a.getFirstName() == null) {
			return null;
		} else {
			return a;
		}
	}
	
	@Override
	public Event addEvent(String eventName, int formatId) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO event VALUES (event_seq.NEXTVAL, ?, ?)";

			String[] key = new String[1];
			key[0] = "event_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setString(1, eventName);
			ps.setInt(2, formatId);

			ps.executeUpdate();

			conn.commit();
			Event a = new Event(0, eventName, formatId);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Event getEventById(int eventId) {
		
		Event a = new Event();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM event WHERE event_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eventId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setEventId(rs.getInt(1));
				a.setEventName(rs.getString(2));
				a.setFormatId(rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public EventDetails addEventDetails(int requestId, int eventId, String eventDate,
			String eventTime, double eventCost, String eventDescription, String justification,
			int daysMissed) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO event_details VALUES (event_details_seq.NEXTVAL, ?, ?, ?, ?, location_seq, ?, ?, ?, ?)";

			String[] key = new String[1];
			key[0] = "event_details_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, requestId);
			ps.setInt(2, eventId);
			ps.setString(3, eventDate);
			ps.setString(4, eventTime);
			//ps.setInt(5, locationId);
			ps.setDouble(5, eventCost);
			ps.setString(6, eventDescription);
			ps.setString(7, justification);
			ps.setInt(8, daysMissed);

			ps.executeUpdate();

			conn.commit();
			EventDetails a = new EventDetails(0, requestId, eventId, eventDate, eventTime, 0, eventCost, 
					eventDescription, justification, daysMissed);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public EventDetails getEventDetailsById(int eventDetailsId) {
		
		EventDetails a = new EventDetails();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM event_details WHERE event_details_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eventDetailsId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setEventDetailsId(rs.getInt(1));
				a.setRequestId(rs.getInt(2));
				a.setEventId(rs.getInt(3));
				a.setEventDate(rs.getString(4));
				a.setEventTime(rs.getString(5));
				a.setLocationId(rs.getInt(6));
				a.setEventCost(rs.getDouble(7));
				a.setJustification(rs.getString(8));
				a.setDaysMissed(rs.getInt(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public EventLocation addEventLocation(int eventDetailsId, String street, String city, String stateName, int zip) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO event_location VALUES (?, ?, ?, ?, ?)";

			String[] key = new String[1];
			key[0] = "event_location_id";
			
			DAO dao = new DAOImplement();
			
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, dao.getEventDetailsById(eventDetailsId).getLocationId());
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, stateName);
			ps.setInt(4, zip);

			ps.executeUpdate();

			conn.commit();
			EventLocation a = new EventLocation(dao.getEventDetailsById(eventDetailsId).getLocationId(), 
					eventDetailsId, street, city, stateName, zip);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public EventLocation getEventLocationById(int eventLocationId) {
		
		EventLocation a = new EventLocation();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM event_location WHERE event_location_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eventLocationId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setLocationId(rs.getInt(1));
				a.setStreet(rs.getString(2));
				a.setCity(rs.getString(3));
				a.setStateName(rs.getString(4));
				a.setZip(rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public GradingFormat getFormatById(int formatId) {
		
		GradingFormat a = new GradingFormat();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM grading_format WHERE format_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, formatId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setFormatId(rs.getInt(1));
				a.setFormatType(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public Request addRequest(int employeeId, Timestamp requestTime) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO request VALUES (request_seq.NEXTVAL, ?, ?)";

			String[] key = new String[1];
			key[0] = "request_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, employeeId);
			ps.setTimestamp(2, requestTime);

			ps.executeUpdate();

			conn.commit();
			Request a = new Request(0, employeeId, requestTime);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Request getRequestById(int requestId) {
		
		Request a = new Request();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM request WHERE request_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, requestId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setRequestId(rs.getInt(1));
				a.setEmployeeId(rs.getInt(2));
				a.setRequestTime(rs.getTimestamp(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}
	
	@Override
	public ArrayList<Request> getAllRequests(int employeeId) {
		
		ArrayList<Request> arr = new ArrayList<Request>();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM request WHERE employee_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Request a = new Request();
				a.setEmployeeId(employeeId);
				a.setRequestId(rs.getInt(2));
				a.setRequestTime(rs.getTimestamp(3));
				arr.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arr;
	}
	
	@Override
	public RequestStatus addRequestStatus(int requestId, String requestStatusType) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO request_status VALUES (request_status_seq.NEXTVAL, ?, ?)";

			String[] key = new String[1];
			key[0] = "request_status_id";

			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, requestId);
			ps.setString(2, requestStatusType);

			ps.executeUpdate();

			conn.commit();
			RequestStatus a = new RequestStatus(0, requestId, requestStatusType);
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public RequestStatus getRequestStatusById(int requestStatusId) {
		
		RequestStatus a = new RequestStatus();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM request_status WHERE resuest_status_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, requestStatusId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setRequestStatusId(rs.getInt(1));
				a.setRequestId(rs.getInt(2));
				a.setRequestStatusType(rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public Title getTitleById(int titleId) {
		
		Title a = new Title();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM title WHERE title_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				a.setTitleId(rs.getInt(1));
				a.setTitleName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}
	
	/*
	public static void main(String[] args) {
		DAO dao = new DAOImplement();
		ArrayList<Requests> r = dao.getRequests(1);
		System.out.println(r.get(0).getRequestsGradingFormat());
	}
	*/
	public static void main(String[] args) {
		DAO dao = new DAOImplement();
		dao.addRequests(1,"pending", "Class", "default", "2018-03-15", "123 street, city, state, 123456", 100.00, "Event Description", "Justification", 1, "none");
	}
	
}
