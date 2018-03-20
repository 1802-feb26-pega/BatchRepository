package com.revature.trms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.pojos.Form;
import com.revature.trms.util.ConnectionFactory;

public class FormDAOImpl implements FormDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;

	@Override
	public boolean hasForms()
	{
		Collection<Form> forms = getAll();
		
		if (forms.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public Collection<Form> getAll()
	{
		Collection<Form> allForms = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM forms";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			allForms = populateForm(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return allForms;
	}

	@Override
	public Collection<Form> getAllByEmployeeId(Integer empId)
	{
		Collection<Form> empForms = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM forms WHERE employeeid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			
			
			empForms = populateForm(rs);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return empForms;
	}

	@Override
	public Form getByFormId(Integer formId)
	{
		Collection<Form> oneForm = new ArrayList<>();
		Form selected = new Form();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM forms WHERE formid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, formId);
			rs = pstmt.executeQuery();
			
			
			oneForm = populateForm(rs);
			
			ArrayList<Form> transfer = (ArrayList<Form>) oneForm;
			selected = transfer.get(0);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return selected;
	}
	
	@Override
	public Form addForm(Integer empId, Form newForm)
	{
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			conn.setAutoCommit(false);
			
			sql = "{CALL add_form(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, empId);
			cstmt.setString(2, newForm.getStartDate());
			cstmt.setString(3, newForm.getAddress());
			cstmt.setString(4, newForm.getCity());
			cstmt.setString(5, newForm.getState());
			cstmt.setInt(6, newForm.getPostal());
			cstmt.setDouble(7, newForm.getCost());
			cstmt.setString(8, newForm.getEvent());
			cstmt.setString(9, newForm.getGradeFormat());
			
			int changeRows = cstmt.executeUpdate();
			
			sql = "SELECT MAX(formid) FROM forms WHERE employeeid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			
			if(changeRows > 0)
			{
				while(rs.next())
				{
					newForm.setFormId(rs.getInt(1));
				}
				conn.commit();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return newForm;
	}

	@Override
	public int updateForm(Integer formID, Form updatedForm)
	{
		int changeRows = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			conn.setAutoCommit(false);
			
			sql = "UPDATE forms SET e_date = ?, e_address = ?, e_city = ?, e_state = ?, "
					+ "e_postal = ?, e_cost = ?, event_type = ?, grade_type = ?, ds_approval = ?, "
					+ "dh_approval = ?, benco_approval = ?, grade = ?, awarded_reimburse = ?"
					+ " WHERE formid = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			// Setting up the Update
			pstmt.setString(1, updatedForm.getStartDate());
			pstmt.setString(2, updatedForm.getAddress());
			pstmt.setString(3, updatedForm.getCity());
			pstmt.setString(4, updatedForm.getState());
			pstmt.setInt(5, updatedForm.getPostal());
			pstmt.setDouble(6, updatedForm.getCost());
			pstmt.setString(7, updatedForm.getEvent());
			pstmt.setString(8, updatedForm.getGradeFormat());
			
			// Approvals - Converting back
			// DS
			int dsA;
			if (updatedForm.getDsApproval())
			{
				dsA = 1;
			}
			else
			{
				dsA = 0;
			}
			pstmt.setInt(9, dsA);
			
			// DH
			int dhA;
			if (updatedForm.getDhApproval())
			{
				dhA = 1;
			}
			else
			{
				dhA = 0;
			}
			pstmt.setInt(10, dhA);
			
			// BenCo
			int benCoA;
			if (updatedForm.getBenCoApproval())
			{
				benCoA = 1;
			}
			else
			{
				benCoA = 0;
			}
			pstmt.setInt(11, benCoA);
			
			// Moving On
			pstmt.setString(12, updatedForm.getGrade());
			pstmt.setDouble(13, updatedForm.getAward());
			
			changeRows = pstmt.executeUpdate();
			
			if(changeRows > 0)
			{
				
				conn.commit();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return changeRows;
	}
	
	private Collection<Form> populateForm(ResultSet input)
	{
		Collection<Form> output = new ArrayList<>();
		
		try
		{
			while(rs.next())
			{
				Form temp = new Form();
				
				temp.setFormId(rs.getInt(1));
				temp.setEmployId(rs.getInt(2));
				temp.setStartDate(rs.getString(3));
				temp.setAddress(rs.getString(4));
				temp.setCity(rs.getString(5));
				temp.setState(rs.getString(6));
				temp.setPostal(rs.getInt(7));
				temp.setCost(rs.getDouble(8));
				temp.setEvent(rs.getString(9));
				temp.setGradeFormat(rs.getString(10));
				
				// Get Submit Time
				Timestamp timestamp = rs.getTimestamp(11);
				String[] transferTime = timestamp.toString().split(" ");
				temp.setSubmitDate(transferTime[0]);
				
				// Approvals
				// DS
				int dsA = rs.getInt(12);
				if(dsA == 1)
				{
					temp.setDsApproval(true);
				}
				else
				{
					temp.setDsApproval(false);
				}
				
				// DH
				int dhA = rs.getInt(13);
				if(dhA == 1)
				{
					temp.setDhApproval(true);
				}
				else
				{
					temp.setDhApproval(false);
				}
				
				// BenCo
				int benCoA = rs.getInt(14);
				if(benCoA == 1)
				{
					temp.setBenCoApproval(true);
				}
				else
				{
					temp.setBenCoApproval(false);
				}
				
				// Moving On
				temp.setGrade(rs.getString(15));
				temp.setAward(rs.getDouble(16));
				
				output.add(temp);
				
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
}
