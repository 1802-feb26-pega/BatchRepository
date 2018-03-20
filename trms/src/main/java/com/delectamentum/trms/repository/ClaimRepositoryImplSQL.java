package com.delectamentum.trms.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.delectamentum.trms.document.Claim;
import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.util.ConnectionFactory;

public class ClaimRepositoryImplSQL implements ClaimRepository{

	@Override
	public List<Claim> getAll() {
		List<Claim> claims = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM claim";
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery(query);
			
			while(res.next()) {
				Claim cur = new Claim();
				
				cur.setId(res.getInt(1));
                cur.setEmployeeId(res.getInt(2));
                cur.setStatus(res.getInt(3));
                cur.setSubmitted(res.getDate(4));
                cur.setAmount(res.getDouble(5));
                cur.setEventType(res.getInt(6));
                cur.setLocation(res.getString(7));
                cur.setEventDate(res.getDate(8));
                cur.setComments(res.getString(9));
                cur.setDescription(res.getString(10));
                cur.setJustification(res.getString(11));
                cur.setMinPassingGrade(res.getDouble(12));
				
				claims.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? claims : null;
	}
	
	@Override
	public Claim getById(int id) {
		Claim target = new Claim();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM claim " +
                           "WHERE claim_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet res = ps.executeQuery();
            while(res.next()) {
            	target.setId(res.getInt(1));
            	target.setEmployeeId(res.getInt(2));
            	target.setStatus(res.getInt(3));
            	target.setSubmitted(res.getDate(4));
            	target.setAmount(res.getDouble(5));
            	target.setEventType(res.getInt(6));
            	target.setLocation(res.getString(7));
            	target.setEventDate(res.getDate(8));
            	target.setComments(res.getString(9));
            	target.setDescription(res.getString(10));
            	target.setJustification(res.getString(11));
            	target.setMinPassingGrade(res.getDouble(12));
            	
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? target : null;
	}

	@Override
	public Claim save(Claim t) {
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			System.out.println(t);
            String query = "INSERT INTO claim (employee_id, projectedamt, etid, location, timeeventstart,comments,description,justification,minpassinggrade)" +
                           "VALUES (?,?,?,?,?,?,?,?,?)";
            String[] key = {"claim_id"};
            PreparedStatement ps = c.prepareStatement(query, key);
            ps.setInt(1, t.getEmployeeId());
            ps.setDouble(2, t.getAmount());
            ps.setInt(3, t.getEventType());
            ps.setString(4,t.getLocation());
            ps.setDate(5, t.getEventDate());
            ps.setString(6, t.getComments());
            ps.setString(7, t.getDescription());
            ps.setString(8, t.getJustification());
            ps.setDouble(9, t.getMinPassingGrade());
            int updateValue = ps.executeUpdate();
            System.out.println(updateValue + " updated in CLAIM.");
            
            ResultSet keys = ps.getGeneratedKeys();
            while(keys.next()) {
            	System.out.println(keys.getInt(1));
            	t.setId(keys.getInt(1));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
		
		return t;
	}

	@Override
	public Claim update(Claim t) {
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "{CALL update_claim(?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement ps = c.prepareCall(query);
            ps.setInt(1, t.getEmployeeId());
            ps.setInt(2, t.getStatus());
            ps.setDate(3, t.getSubmitted());
            ps.setDouble(4,t.getAmount());
            ps.setInt(5, t.getEventType());
            ps.setString(6, t.getLocation());
            ps.setDate(7, t.getEventDate());
            ps.setString(8, t.getComments());
            ps.setString(9, t.getDescription());
            ps.setString(10, t.getJustification());
            ps.setDouble(11, t.getMinPassingGrade());
            ps.setInt(12, t.getId());
            int updateValue = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
		
		return t;
	}

	@Override
	public Claim delete(Claim t) {
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "DELETE FROM claim"
            		     + "WHERE claim_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, t.getId());
            int updateValue = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
		
		return t;
	}

	@Override
	public List<Claim> getClaimsByEmployee(int employeeId) {
		List<Claim> target = new ArrayList<>();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM claim " +
                           "WHERE employee_id = ?";
            
            PreparedStatement ps = c.prepareStatement(query);
            
            ps.setInt(1,employeeId);

            ResultSet res = ps.executeQuery();
            while(res.next()) {
                Claim cur = new Claim();
                
                cur.setId(res.getInt(1));
                cur.setEmployeeId(employeeId);
                cur.setStatus(res.getInt(3));
                cur.setSubmitted(res.getDate(4));
                cur.setAmount(res.getDouble(5));
                cur.setEventType(res.getInt(6));
                cur.setLocation(res.getString(7));
                cur.setEventDate(res.getDate(8));
                cur.setComments(res.getString(9));
                cur.setDescription(res.getString(10));
                cur.setJustification(res.getString(11));
                cur.setMinPassingGrade(res.getDouble(12));
            	
            	target.add(cur);
                updated = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return updated ? target : null;
	}

	@Override
	public List<Claim> getApprovableClaims(Employee e) {
		List<Claim> target = new ArrayList<>();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "";
            
            switch(e.getType()) {
            case 2:
            	query = "SELECT * FROM claim " +
                        "WHERE approval_status = ?" +
                        "AND NOT employee_id = ?" +
                        "AND employee_id IN (SELECT employee_id FROM employee WHERE reportsto = ?)";
            	break;
            case 3:
            	query = "SELECT * FROM claim " +
            			"WHERE approval_status = ?" +
            			"AND NOT employee_id = ?" +	
            			"AND employee_id IN (SELECT employee_id FROM employee WHERE departmentid = ?)";	
            	break;
            case 4:
            	query = "SELECT * FROM claim " +
                        "WHERE approval_status = ?" +
                        "AND NOT employee_id = ?";
            	break;
            default:
            	query = "SELECT * FROM claim " +
                        "WHERE approval_status = ?";
            	break;
            }
            
            PreparedStatement ps = c.prepareStatement(query);
            
            switch(e.getType()) {
            case 2:
            	ps.setInt(1,e.getType() - 1);
                ps.setInt(2, e.getId());
                ps.setInt(3, e.getId());
            	break;
            case 3:
            	ps.setInt(1,e.getType() - 1);
                ps.setInt(2, e.getId());
                ps.setInt(3, e.getDepartment());
            	break;
            case 4:
            	ps.setInt(1,e.getType() - 1);
                ps.setInt(2, e.getId());
            	break;
            default:
            	break;
            }
      
            ResultSet res = ps.executeQuery();
            while(res.next()) {
                Claim cur = new Claim();
                
                cur.setId(res.getInt(1));
                cur.setEmployeeId(e.getId());
                cur.setStatus(res.getInt(3));
                cur.setSubmitted(res.getDate(4));
                cur.setAmount(res.getDouble(5));
                cur.setEventType(res.getInt(6));
                cur.setLocation(res.getString(7));
                cur.setEventDate(res.getDate(8));
                cur.setComments(res.getString(9));
                cur.setDescription(res.getString(10));
                cur.setJustification(res.getString(11));
                cur.setMinPassingGrade(res.getDouble(12));
            	
            	target.add(cur);
                updated = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return updated ? target : null;
	}

}
