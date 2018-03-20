package com.reimb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;
import com.reimb.util.ConnectionFactory;

public class DAOImpl implements DAO {

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
				emp.setDOB(info.getDate(8).toLocalDate());
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
				emp.setDOB(info.getDate(8).toLocalDate());
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
			LocalDate DOB, String address, String city, String state) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into employees "
					+ "(first_name, last_name, email, password, reimburse_remain, " + 
					"		reimburse_pend, date_of_birth, job_level, dept, address, " + 
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
			ps.setDate(7, java.sql.Date.valueOf(DOB));
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
	public Reimbursement addReimbursement(int id) {
		// TODO Auto-generated method stub
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
