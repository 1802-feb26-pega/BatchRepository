package com.pchase95.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.project1.pojos.Department;
import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.Location;
import com.pchase95.project1.util.ConnectionFactory;

public class EmployeeDAO implements DAO<Employee> {
	

	@Override
	public List<Employee> getAll() {
		List<Employee> results = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM employee";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				results.add(retrieveEmployee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}

	@Override
	public Employee getById(long id) {
		Employee emp = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM employee WHERE emp_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				emp = retrieveEmployee(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}
	
	
	public Employee getByCredentials(String email, String pwd) {
		Employee result = null;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM employee WHERE emp_email = ? AND emp_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = retrieveEmployee(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean add(Employee newEmp) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = 
					"INSERT INTO employee (supervisor_emp_id, dep_head_emp_id, dep_id, loc_id, emp_is_dep_head, emp_email, emp_password, emp_avaliable_rbmt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = { "emp_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			prepareEmployee(newEmp, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newEmp.setId(rs.getLong(1));
				}	
				conn.commit();
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(long id, Employee updatedEmp) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = 
					"UPDATE employee SET supervisor_emp_id = ?, dep_id = ?, dep_head_emp_id, loc_id = ?, emp_is_dep_head = ?, emp_email = ?, emp_password = ?, emp_avaliable_rbmt = ? WHERE emp_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareEmployee(updatedEmp, pstmt);
			pstmt.setLong(9, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	@Override
	public boolean remove(long id) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM employee WHERE emp_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private Employee retrieveEmployee(ResultSet rs) {
		try {
			Employee emp = new Employee();
			emp.setId(rs.getLong(1));			
			
			long superVisorId = rs.getLong(2);
			if (superVisorId != 0) {
				emp.setSuperVisor(getById(superVisorId));
			}
			
			long depHeadId = rs.getLong(3);
			if (depHeadId != 0) {
				emp.setDepartmentHead(getById(depHeadId));
			}
			
			long depId = rs.getLong(4);
			emp.setDepartment(new DepartmentDAO().getById(depId));
			
			long locId = rs.getLong(5);
			emp.setLocation(new LocationDAO().getById(locId));
			
			emp.setIsDepartmentHead(rs.getInt(6) == 1 ? true : false);
			
			emp.setEmail(rs.getString(7));
			emp.setPassword(rs.getString(8));
			emp.setAvaliableReimbursment(rs.getDouble(9));
			
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void prepareEmployee(Employee emp, PreparedStatement pstmt) {
		try {
			Employee superVisor = emp.getSuperVisor();
			if (superVisor != null) {
				pstmt.setLong(1,  superVisor.getId());
			} else {
				pstmt.setNull(1, Types.INTEGER);
			}
			
			Employee depHead = emp.getDepartmentHead();
			if (depHead != null) {
				pstmt.setLong(2, depHead.getId());
			} else {
				pstmt.setNull(2, Types.INTEGER);
			}
			
			Department dep = emp.getDepartment();
			if (dep != null) {
				pstmt.setLong(3, dep.getId());				
			} else {
				pstmt.setNull(3, Types.INTEGER);
			}
			
			Location loc = emp.getLocation();
			if (loc != null) {
				pstmt.setLong(4, loc.getId());
			} else {
				pstmt.setNull(4, Types.INTEGER);
			}
			
			pstmt.setInt(5, emp.isDepartmentHead() ? 1 : 0);
			pstmt.setString(6, emp.getEmail());
			pstmt.setString(7, emp.getPassword());
			pstmt.setDouble(8, emp.getAvaliableReimbursment());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
