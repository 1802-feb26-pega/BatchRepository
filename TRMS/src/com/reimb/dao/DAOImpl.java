package com.reimb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;
import com.reimb.util.ConnectionFactory;

public class DAOImpl implements DAO {
	public static Double getEventMod(int id){
		return null;
	}

	@Override
	public Employee getEmployee(String email) {
		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from employees where email =  ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				emp.setEmpId(info.getInt(1));
				emp.setFirstName(info.getString(2));
				emp.setLastName(info.getString(3));
				emp.setEmail(info.getString(4));
				emp.setPassword(info.getString(5));
				emp.setReimburseRemain(info.getDouble(6));
				emp.setReimbursePending(info.getDouble(7));
				emp.setDOB(info.getDate(8));
				emp.setJobLevel(info.getInt(9));
				emp.setDepartment(info.getInt(10));
				emp.setAddress(info.getString(11));
				emp.setCity(info.getString(12));
				emp.setState(info.getString(13));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from employees where u_id =  ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				emp.setEmpId(info.getInt(1));
				emp.setFirstName(info.getString(2));
				emp.setLastName(info.getString(3));
				emp.setEmail(info.getString(4));
				emp.setPassword(info.getString(5));
				emp.setReimburseRemain(info.getDouble(6));
				emp.setReimbursePending(info.getDouble(7));
				emp.setDOB(info.getDate(8));
				emp.setJobLevel(info.getInt(9));
				emp.setDepartment(info.getInt(10));
				emp.setAddress(info.getString(11));
				emp.setCity(info.getString(12));
				emp.setState(info.getString(13));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}

	@Override
	public Employee addEmployee(String fn, String ln, String email, String pass, int dept,
			Date DOB, String address, String city, String state) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into employees "
					+ "(first_name, last_name, email, password, reimburse_remain, " + 
					"		reimburse_pending, dob, job_level, department, address, " + 
					"		city, state) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String [] key = new String[1];
			key[0] = "employee_id";
			
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setString(1, fn);
			ps.setString(2, ln);
			ps.setString(3, email);
			ps.setString(4, pass);
			ps.setDouble(5, 1000);
			ps.setDouble(6, 0);
			ps.setDate(7, DOB);
			ps.setInt(8, 0);
			ps.setInt(9, dept);
			ps.setString(10, address);
			ps.setString(11, city);
			ps.setString(12, state);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()){
				id = pk.getInt(1);
			}

			conn.commit();
			Employee emp = new Employee(0, fn, ln, email, pass, 1000, 0, DOB, 0, dept, address, city, state);
			emp.setEmpId(id);
			return emp;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Reimbursement addReimbursement(int emp_id, Date event_date, String city, 
			String state, Double cost, String description, int event_type, 
			int hours_missed, int approve_id, int format_id, int attach_id) {
		
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);
			
			double projected_reimb = cost * getEventMod(event_type);

			String sql = "insert into reimbursements "
					+ "(employee_id, event_date, city, state, cost, " 
					+ "projected_reimb, description, event_type, hours_missed, "
					+ "approve_id, format_id, attach_id) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String [] key = new String[1];
			key[0] = "reimb_id";
			
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, emp_id);
			ps.setDate(2, event_date);
			ps.setString(3, city);
			ps.setString(4, state);
			ps.setDouble(5, cost);
			ps.setDouble(6, projected_reimb);
			ps.setString(7, description);
			ps.setInt(8, event_type);
			ps.setInt(9, hours_missed);
			ps.setInt(10, approve_id);
			ps.setInt(11, format_id);
			ps.setInt(12, attach_id);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()){
				id = pk.getInt(1);
			}

			conn.commit();
			Reimbursement reimb = new Reimbursement(0, emp_id, event_date, city, 
			state, cost, projected_reimb, description, event_type,  
			hours_missed, approve_id, format_id, attach_id);
			reimb.setReimbId(id);
			
			return reimb;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsById(int id) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from reimbursements where employee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				Reimbursement temp = new Reimbursement();
				temp.setReimbId(info.getInt(1));
				System.out.println("Reimbursement: " + temp.getReimbId());
				temp.setEmpId(id);
				
				reimbursements.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public void updateReimbursement(int id, double amt) {
		// TODO Auto-generated method stub
		
	}
	
	

}
