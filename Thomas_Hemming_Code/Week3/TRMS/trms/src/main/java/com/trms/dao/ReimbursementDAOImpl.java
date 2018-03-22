package com.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trms.pojos.Reimbursement;
import com.trms.util.ConnectionFactory;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public Reimbursement addReimbursement(int employeeId, int eventId, String justification, int superApp,
			int depHeadApp, int benCoApp, int requestedAmount) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into reimbursement "
					+ "(employee_id, event_id, justification, super_app, dephead_appr, benco_appr, requested_amount) "
					+ "values(?, ?, ?, ?, ?, ?, ? )";
			String [] key = new String[1];
			key[0] = "re_id";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, employeeId);
			ps.setInt(2, eventId);
			ps.setString(3, justification);
			ps.setInt(4, superApp);
			ps.setInt(5, depHeadApp);
			ps.setInt(6, benCoApp);
			ps.setInt(7, requestedAmount);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			
			while(pk.next()) {
				id = pk.getInt(1);
			}

			conn.commit();
			Reimbursement r = new Reimbursement(employeeId, eventId, justification, superApp, depHeadApp, benCoApp, requestedAmount);
			r.setReId(id);
			return r;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
