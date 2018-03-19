package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.util.ConnectionFactory;

public class GradeDAOImpl implements GradeDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	
	@Override
	public Collection<String> getAllGradeFormats()
	{
		Collection<String> allGrades = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM gradeformat";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				allGrades.add(rs.getString(1));
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allGrades;
	}

}
