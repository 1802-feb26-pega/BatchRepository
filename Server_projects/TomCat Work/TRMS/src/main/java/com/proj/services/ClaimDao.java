package com.proj.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.proj.pojos.Claim;
import com.proj.pojos.Employee;
import com.proj.util.ConnectionFactory;

public class ClaimDao {
	Date date = Calendar.getInstance().getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Employee employee;
	
	
	public boolean addClaim(Claim claim) {
		// TODO Auto-generated method stub

		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "INSERT INTO CLAIMS VALUES (1,?,?,?,0,?,?,?,?,?,null,?,null,?,?,?,?)";


			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setString(1,"Pending");
			p_statement.setString(2,sdf.format(date));
			p_statement.setString(3,claim.getEventStartdate());
			p_statement.setInt(4,1);//Set employee key here
			p_statement.setString(5,claim.getLoc());
			p_statement.setString(6,claim.getEvent_type());
			p_statement.setDouble(7,claim.getCost());
			p_statement.setString(8,claim.getReason());
			p_statement.setString(9,claim.getEventStarttime());
			p_statement.setInt(10,claim.getPassing());
			p_statement.setInt(11,claim.getGradingFormat());
			p_statement.setString(13,claim.getDescription());
			p_statement.setInt(12,claim.getDaysmissed());



			int rows_affected_claim = p_statement.executeUpdate();

			
			
			if(rows_affected_claim > 0 & rows_affected_note > 0 ) return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}

		return false;
	}		
}
