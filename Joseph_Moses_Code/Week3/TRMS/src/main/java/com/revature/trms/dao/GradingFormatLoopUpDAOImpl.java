package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.pojos.GradingFormat;
import com.revature.trms.util.ConnectionFactory;

public class GradingFormatLoopUpDAOImpl implements GradingFormatLookUpDAO {

	@Override
	public GradingFormat getGradingFormat(int id) {
		
		GradingFormat format = new GradingFormat();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM GradingFormatLookUp WHERE formatId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				format.setGradingFormatId(rs.getInt(1));
				format.setGradingFormat(rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return format;
	}

}
