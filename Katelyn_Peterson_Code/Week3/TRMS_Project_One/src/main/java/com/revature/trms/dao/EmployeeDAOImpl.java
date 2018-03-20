package com.revature.trms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.pojos.Employee;
import com.revature.trms.util.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	
	@Override
	public Employee getUserByEmail(String email)
	{
		Collection<Employee> oneEmp = new ArrayList<>();
		Employee selected = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM employee WHERE lower(email) = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			oneEmp = populateEmployee(rs);
			
			ArrayList<Employee> transfer = (ArrayList<Employee>) oneEmp;
			selected = transfer.get(0);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(selected.getEmployeeId() == 0) return null;
		
		return selected;
	}

	@Override
	public Employee getEmployeeSupervisor(Integer empId)
	{
		Collection<Employee> oneEmp = new ArrayList<>();
		Employee selected = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "{CALL find_supervisor(?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			
			oneEmp = populateEmployee(rs);
			
			ArrayList<Employee> transfer = (ArrayList<Employee>) oneEmp;
			selected = transfer.get(0);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(selected.getEmployeeId() == 0) return null;
		
		return selected;
	}

	@Override
	public Employee getEmployeeByEmployeeId(Integer empId)
	{
		Collection<Employee> oneEmp = new ArrayList<>();
		Employee selected = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM employee WHERE employeeid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			
			oneEmp = populateEmployee(rs);
			
			ArrayList<Employee> transfer = (ArrayList<Employee>) oneEmp;
			selected = transfer.get(0);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(selected.getEmployeeId() == 0) return null;
		
		return selected;
	}

	@Override
	public Collection<Employee> getDSEmployees(Integer empId)
	{
		Collection<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM employee WHERE reportsto = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			
			employees = populateEmployee(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//if(employees.isEmpty()) return null;
		
		return employees;
	}

	@Override
	public Collection<Employee> getDHEmployees(Integer departID)
	{
		Collection<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM employee WHERE departmentid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, departID);
			rs = pstmt.executeQuery();
			
			employees = populateEmployee(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//if(employees.isEmpty()) return null;
		
		return employees;
	}
	
	private Collection<Employee> populateEmployee(ResultSet rs)
	{
		Collection<Employee> output = new ArrayList<Employee>();
		
		try
		{
			while(rs.next())
			{
				Employee retrieved = new Employee();
				
				retrieved.setEmployeeId(rs.getInt(1));
				retrieved.setFirstName(rs.getString(2));
				retrieved.setLastName(rs.getString(3));
				retrieved.setEmail(rs.getString(4));
				retrieved.setPassword(rs.getString(5));
				
				Long dataTransfer = rs.getLong(6);
				char[] transfer = dataTransfer.toString().toCharArray();
				String temp = "+";
				temp = temp + transfer[0] + " (";
				
				for (int x = 1; x <= 3; x++)
				{
					temp = temp + transfer[x];
				}
				
				temp = temp + ") ";
				
				for (int x = 4; x <= 6; x++)
				{
					temp = temp + transfer[x];
				}
				
				temp = temp + "-";
				
				for (int x = 7; x < transfer.length; x++)
				{
					temp = temp + transfer[x];
				}
				
				retrieved.setPhone(temp);
				// Moving on
				retrieved.setReimburse(rs.getDouble(7));
				retrieved.setReportsto(rs.getInt(8));
				retrieved.setDepartId(rs.getInt(9));
				retrieved.setEmpTitle(rs.getString(10));
				
				output.add(retrieved);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return output;
	}
	
}
