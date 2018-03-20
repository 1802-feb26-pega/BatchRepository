package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.trms.dao.EmployeeDAO;
import com.trms.pojos.Employee;
import com.trms.util.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM employee";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Employee temp = new Employee();
				temp.setEmployeeId(rs.getInt(1));
				temp.setFirstname(rs.getString(2));
				temp.setLastname(rs.getString(3));
				temp.setEmail(rs.getString(4));
				temp.setPass(rs.getString(5));
				temp.setSupervisorId(rs.getInt(6));
				temp.setDepartmentId(rs.getInt(7));
				temp.setLevelId(rs.getInt(8));
				temp.setAvailableReimbursement(rs.getDouble(9));
				temp.setPendingReimbursement(rs.getDouble(10));
				temp.setAwardedReimbursement(rs.getDouble(11));
				employees.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM employee WHERE employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employee.setFirstname(rs.getString(2));
				employee.setLastname(rs.getString(3));
				employee.setEmail(rs.getString(4));
				employee.setPass(rs.getString(5));
				employee.setSupervisorId(rs.getInt(6));
				employee.setDepartmentId(rs.getInt(7));
				employee.setLevelId(rs.getInt(8));
				employee.setAvailableReimbursement(rs.getDouble(9));
				employee.setPendingReimbursement(rs.getDouble(10));
				employee.setAwardedReimbursement(rs.getDouble(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		Employee employee = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM employee WHERE email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employee.setEmployeeId(rs.getInt(1));
				employee.setFirstname(rs.getString(2));
				employee.setLastname(rs.getString(3));
				employee.setEmail(email);
				employee.setPass(rs.getString(5));
				employee.setSupervisorId(rs.getInt(6));
				employee.setDepartmentId(rs.getInt(7));
				employee.setLevelId(rs.getInt(8));
				employee.setAvailableReimbursement(rs.getDouble(9));
				employee.setPendingReimbursement(rs.getDouble(10));
				employee.setAwardedReimbursement(rs.getDouble(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@Override
	public Employee addEmployee(Employee newEmployee) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee (firstname, lastname, email, pass, supervisor_id, " +
						"department_id, level_id, available_reimbursement, pending_reimbursement, " +
						"awarded_reimbursement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] key = new String[1];
			key[0] = "employee_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newEmployee.getFirstname());
			pstmt.setString(2, newEmployee.getLastname());
			pstmt.setString(3, newEmployee.getEmail());
			
			// Hash the password!
			String hashed = BCrypt.hashpw(newEmployee.getPass(), BCrypt.gensalt());
			pstmt.setString(4, hashed);
			
			pstmt.setInt(5, newEmployee.getSupervisorId());
			pstmt.setInt(6, newEmployee.getDepartmentId());
			pstmt.setInt(7, newEmployee.getLevelId());
			pstmt.setDouble(8, newEmployee.getAvailableReimbursement());
			pstmt.setDouble(9, newEmployee.getPendingReimbursement());
			pstmt.setDouble(10, newEmployee.getAwardedReimbursement());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rowsAffected > 0) {
				while(rs.next()) {
					newEmployee.setEmployeeId(rs.getInt(1));
					newEmployee.setPass(hashed);
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newEmployee;
	}

	@Override
	public Employee updateEmployee(Employee updatedEmp) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			

			conn.setAutoCommit(false);

			String sql = "UPDATE employee SET firstname = ?, lastname = ?, email = ?, pass = ?, supervisor_id = ?, "
					+ "department_id = ?, level_id = ?, available_reimbursement = ?,"
					+ " pending_reimbursement = ?, awarded_reimbursement = ? WHERE employee_id = ?"; 

	
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updatedEmp.getFirstname());
			pstmt.setString(2, updatedEmp.getLastname());
			pstmt.setString(3, updatedEmp.getEmail());
			pstmt.setString(4, updatedEmp.getPass());
			pstmt.setInt(5, updatedEmp.getSupervisorId());
			pstmt.setInt(6, updatedEmp.getDepartmentId());
			pstmt.setInt(7, updatedEmp.getLevelId());
			pstmt.setDouble(8, updatedEmp.getAvailableReimbursement());
			pstmt.setDouble(9, updatedEmp.getPendingReimbursement());
			pstmt.setDouble(10, updatedEmp.getAwardedReimbursement());
			pstmt.setInt(11, updatedEmp.getEmployeeId());

			int rowsAffected = pstmt.executeUpdate();
		
			if(rowsAffected > 0) {
				conn.commit();
			}
			else {
				updatedEmp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updatedEmp;
	}

	@Override
	public Employee getEmployeeSupervisor(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean employeeExists(String email) {
		Employee test = getEmployeeByEmail(email);
		if(test.getEmployeeId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidPassword(String email, String password) {
		Employee test = getEmployeeByEmail(email);
		
		try {
			if (BCrypt.checkpw(password, test.getPass())) {
				return true;
			}
		} catch (NullPointerException npe) {
			return false;
		}
		
//		if (BCrypt.checkpw(candidate, hashed))
//			System.out.println("It matches");
//		else
//			System.out.println("It does not match");
		return false;
	}

	@Override
	public Employee getEmployeeByName(String firstname, String lastname) {
		Employee employee = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM employee WHERE firstname = ? AND lastname = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, firstname);
			pstmt.setString(2,  lastname);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employee.setEmployeeId(rs.getInt(1));
				employee.setFirstname(rs.getString(2));
				employee.setLastname(rs.getString(3));
				employee.setEmail(rs.getString(4));
				employee.setPass(rs.getString(5));
				employee.setSupervisorId(rs.getInt(6));
				employee.setDepartmentId(rs.getInt(7));
				employee.setLevelId(rs.getInt(8));
				employee.setAvailableReimbursement(rs.getDouble(9));
				employee.setPendingReimbursement(rs.getDouble(10));
				employee.setAwardedReimbursement(rs.getDouble(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public boolean employeeExists(String firstname, String lastname) {
		Employee test = getEmployeeByName(firstname, lastname);
		if(test.getEmployeeId() > 0) {
			return true;
		}
		return false;
	}

}
