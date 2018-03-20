package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.bank.pojos.Employee;
import com.bank.pojos.Event;
import com.bank.util.ConnectionFactory;

public class DAOImpl implements DAO{

	@Override
	public HashMap<Integer, String> getEmails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getUserById(int id) {

		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){

			String sql = "select EmployeeId, Firstname, Lastname, Email, Password"
					+ " from Employee where EmployeeId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				emp.setId(info.getInt(1));
				emp.setFirstname(info.getString(2));
				emp.setLastname(info.getString(3));
				emp.setEmail(info.getString(4));
				emp.setPassword(info.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}

	@Override
	public Employee addUser(Employee emp) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){

			conn.setAutoCommit(false);

			String sql = "insert into Employee "
					+ "(Firstname, Lastname, Email, Password, DepartmentId, address, zip, Balance) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
			String [] key = new String[1];
			key[0] = "EmployeeId";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setString(1, emp.getFirstname());
			ps.setString(2, emp.getLastname());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getPassword());
			ps.setInt(5, emp.getDepartmentId());
			ps.setString(6, emp.getAddress());
			ps.setString(7, emp.getZip());
			ps.setInt(8, 1000);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()){
				id = pk.getInt(1);
			}
			conn.commit();
			
			emp.setBalance(1000);
			emp.setId(id);
			return emp;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public double getBalance(int id) {
		double bal = 0.0;
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select balance from employee where EmployeeId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				bal = info.getDouble(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return bal;
	}

	@Override
	public void updateBalance(int id, double amt) {
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			
			String sql = "update employee set balance = ? where EmployeeID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amt );
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public Employee getUser(String email) {

		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){

			email = email.toLowerCase();
			String sql = "select EmployeeId, Firstname, Lastname, Email, Password, Balance, DepartmentId"
					+ " from Employee where Email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				emp.setId(info.getInt(1));
				emp.setFirstname(info.getString(2));
				emp.setLastname(info.getString(3));
				emp.setEmail(info.getString(4));
				emp.setPassword(info.getString(5));
				emp.setBalance(info.getInt(6));
				emp.setDepartmentId(info.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("is emp null? emp name is " + emp.getFirstname());
		if(emp.getId() == 0) 
			return null;

		return emp;
	}

	@Override
	public Event addEvent(Event event) {

		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){

			conn.setAutoCommit(false);

			String sql = "insert into Event "
					+ "(EmployeeId, EventType, Format, Cost, Day, Time, Location, Description) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
			String [] key = new String[1];
			key[0] = "EventId";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, event.getEmployeeId());
			ps.setString(2, event.getType());
			ps.setString(3, event.getFormat());
			ps.setInt(4, event.getCost());
			ps.setString(5, event.getDate());
			ps.setString(6, event.getTime());
			ps.setString(7, event.getLocation());
			ps.setString(8, event.getDescription());

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			
			while(pk.next()) {
				id = pk.getInt(1);
			}
			
			conn.commit();
			event.setId(id);
			return event;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> events = new ArrayList<Event>();

		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			
			String sql = "select * from Event";
			Statement stmt = conn.createStatement();
			ResultSet info = stmt.executeQuery(sql);

			while(info.next()){
				Event temp = new Event();
				temp.setId(info.getInt(1));
				temp.setEmployeeId(info.getInt(2));
				temp.setType(info.getString(3));
				temp.setFormat(info.getString(4));
				temp.setCost(info.getInt(5));
				temp.setDate(info.getString(6));
				temp.setTime(info.getString(7));
				temp.setLocation(info.getString(8));
				temp.setDescription(info.getString(9));
				temp.setGrade(info.getString(10));
				temp.setApproval(info.getString(11));
				events.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	@Override
	public ArrayList<Event> getEventByUser(Employee emp) {
		ArrayList<Event> events = new ArrayList<Event>();

		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			
			String sql = "select * from Event where EmployeeId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ResultSet info = ps.executeQuery();

			while(info.next()){
				Event temp = new Event();
				temp.setId(info.getInt(1));
				temp.setEmployeeId(info.getInt(2));
				temp.setType(info.getString(3));
				temp.setFormat(info.getString(4));
				temp.setCost(info.getInt(5));
				temp.setDate(info.getString(6));
				temp.setTime(info.getString(7));
				temp.setLocation(info.getString(8));
				temp.setDescription(info.getString(9));
				temp.setGrade(info.getString(10));
				temp.setApproval(info.getString(11));
				events.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public void updateGrade(int id, String grade) {
		
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();) {
			
			String sql = "update event set grade = ? where EventID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, grade);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getApproval(int id) {
		
		int approve = 0;
		
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			
			String sql = "select approval from event where eventId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				approve = info.getInt(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return approve;
	}

	public void updateApproval(int id, int approval) {
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();) {
			
			String sql = "update event set approval = ? where EventId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, approval);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
