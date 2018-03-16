package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.pojos.Employee;
import com.revature.trms.util.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	@Override
	public Employee getUserByEmail(String email)
	{
		
		Employee emp = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM employee WHERE lower(email) = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setPassword(rs.getString(5));
				
				// Phone transfer -- maybe move?
				char[] transfer = String.valueOf(rs.getInt(6)).toCharArray();
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
				
				emp.setPhone(temp);
				// Moving on
				emp.setReimburse(rs.getDouble(7));
				emp.setReportsto(rs.getInt(8));
				emp.setDepartId(rs.getInt(9));
				emp.setEmpTitle(rs.getString(10));
				
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return emp;
	}
	
}
