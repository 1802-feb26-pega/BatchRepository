package com.proj.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.proj.pojos.Claim;
import com.proj.pojos.Employee;
import com.proj.util.ConnectionFactory;

public class ClaimDao {
	Date date = Calendar.getInstance().getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");


	public boolean addClaim(Claim claim,Employee e) {
		// TODO Auto-generated method stub


		try(Connection conn = ConnectionFactory.getInstance().getConnection()){


			conn.setAutoCommit(false);

			String sql = "INSERT INTO CLAIMS "
					+ "(STATUS,CREATEDDATE,EVENTSTARTDATE,EMP_ID,LOCATION,EVENT_TYPE,"
					+ "COST,REASON,EVENTSTARTTIME,GRADE,GRADINGFORMAT,DAYSMISSED,DESCRIPTION,COMMENTS)"
					+ " VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setString(1,"Pending");
			p_statement.setString(2,sdf.format(date));
			p_statement.setString(3,claim.getEventStartdate());
			p_statement.setInt(4,e.getKey());
			p_statement.setString(5,claim.getLoc());
			p_statement.setString(6,claim.getEvent_type());
			p_statement.setDouble(7,claim.getCost());
			p_statement.setString(8,claim.getReason());
			p_statement.setString(9,claim.getEventStarttime());
			p_statement.setInt(10,claim.getPassing());
			p_statement.setInt(11,claim.getGradingFormat());
			p_statement.setInt(12,claim.getDaysmissed());
			p_statement.setString(13,claim.getDescription());
			p_statement.setString(14,claim.getComments());



			int rows_affected_claim = p_statement.executeUpdate();

			//int rows_affected_note = p_statement.executeUpdate();

			if(rows_affected_claim > 0 /*& rows_affected_note > 0 */){
				conn.commit();
				return true;
			}else{
				conn.rollback();				
				return false;
			}

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return false;
	}


	public ArrayList<Claim> getClaims(Employee employee) {
		// TODO Auto-generated method stub
		ArrayList<Claim> list = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){


			conn.setAutoCommit(false);

			String sql = "SELECT * FROM CLAIMS WHERE EMP_ID = ?";


			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1,employee.getKey());




			ResultSet result_of_query = p_statement.executeQuery();
			
			while(result_of_query.next()){
				Claim c = new Claim();
				
					c.setClaim_id(result_of_query.getInt(1));
				    c.setStatus(result_of_query.getString(2));
					c.setCreated(result_of_query.getString(3));
					c.setEventStartdate(result_of_query.getString(4));
					c.setAmount_given(result_of_query.getInt(5));
					c.setEmp_id(result_of_query.getInt(6));
					c.setLoc(result_of_query.getString(7));
					c.setEvent_type(result_of_query.getString(8));
					c.setCost(result_of_query.getDouble(9));
					c.setReason(result_of_query.getString(10));
					c.setAttachment(result_of_query.getBlob(11));
					c.setEventStarttime(result_of_query.getString(12));
					c.setNote_id(result_of_query.getInt(13));
					c.setPassing(result_of_query.getInt(14));
					c.setGradingFormat(result_of_query.getInt(15));
					c.setDaysmissed(result_of_query.getInt(16));
					c.setDescription(result_of_query.getString(17));
					c.setComments(result_of_query.getString(18));
					
				list.add(c);
					
					
			}


		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return list;
	}		
}
