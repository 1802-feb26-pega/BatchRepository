package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.util.ConnectionFactory;

public class DepartmentDAOImpl implements DepartmentDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	@Override
	public Integer getDepartmentHead(Integer departID)
	{
		Integer selected = 0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT departmentheadid FROM department WHERE departmentid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, departID);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				selected = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return selected;
	}

}
