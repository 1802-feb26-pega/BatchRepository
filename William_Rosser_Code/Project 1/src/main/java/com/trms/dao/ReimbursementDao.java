package com.trms.dao;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import com.trms.pojos.BenCo;
import com.trms.pojos.DepartmentHead;
import com.trms.pojos.DirectSupervisor;
import com.trms.pojos.Reimbursement;
import com.trms.pojos.TRForm;
import com.trms.pojos.User;
import com.trms.util.ConnectionFactory;

public class ReimbursementDao {
	private static ReimbursementDao me;
	private ReimbursementDao() {
		me = this;
	}

	public static ReimbursementDao getInstance() {
		if (me == null) me = new ReimbursementDao();
		return me;
	}

	public TRForm addRequest(TRForm trf, User u) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String requestSql = "INSERT INTO reimbursement(employee_id,ammount_paid,status,request_date) "
					+"VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(requestSql);
			ps.setInt(1, u.getId());
			ps.setDouble(2, 0);
			ps.setString(3, "New Request");
			Date d = Date.valueOf(LocalDate.now());
			ps.setDate(4, d, Calendar.getInstance());
			int val = ps.executeUpdate();
			if (val <= 0) {
				return null;
			}
			ps = conn.prepareStatement("SELECT re_id FROM reimbursement WHERE ? = employee_id AND ? = request_date");
			ps.setInt(1, u.getId());
			ps.setDate(2, d, Calendar.getInstance());
			ResultSet rs = ps.executeQuery();
			int re_id = 0;
			if (rs.next()) {
				re_id = rs.getInt(1);
			} else {
				return null;
			}
			String trFormSql = "INSERT INTO tr_form(employee_id,event_type,date_time,reason,money_request,missed_work_time,reimbursement_id,loc) "+
					"VALUES (?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(trFormSql);
			ps.setInt(1, u.getId());
			ps.setInt(2, trf.getEventType());
			d = Date.valueOf(trf.getDate());
			ps.setDate(3, d, Calendar.getInstance());
			Clob c = conn.createClob();
			c.setString(1,  trf.getReason());
			ps.setClob(4, c);
			ps.setDouble(5, trf.getCost());
			ps.setDouble(6,trf.getMissedWorkTime());
			ps.setInt(7, re_id);
			c = conn.createClob();
			c.setString(1, trf.getLocation());
			ps.setClob(8, c);
			val = ps.executeUpdate();
			if (val <= 0) {
				return null;
			}
			trFormSql = "SELECT form_id FROM tr_form WHERE reimbursement_id = ?";
			ps = conn.prepareStatement(trFormSql);
			ps.setInt(1, re_id);
			rs = ps.executeQuery();
			int f_id = 0;
			if (rs.next()) {
				f_id = rs.getInt(1);
			} else {
				return null;
			}
			ps = conn.prepareStatement("UPDATE reimbursement SET form_id = ? where re_id = ?");
			ps.setInt(1, f_id);
			ps.setInt(2, re_id);
			val = ps.executeUpdate();
			if (val > 1) {
				conn.commit();
				trf.setFormId(f_id);
				return trf;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<TRForm> getTRFFromUser(User u) {
		return getTRF(u, -1);
	}

	public ArrayList<TRForm> getTRFForApprover(User u, int roll) {
		return getTRF(u, roll);
	}

	private ArrayList<TRForm> getTRF(User u, int roll) {
		System.out.println("get TRF, roll: " + roll);
		ArrayList<TRForm> trfarr = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM tr_form ";
			//TODO: Filter by status
			//TODO: trigger auto-passing callable statement
			switch(roll) {
			case 1: sql += "WHERE employee_id IN (SELECT employee_id FROM EMPLOYEE WHERE SUPERVISOR_ID = ?)"; break;
			case 2: sql += "WHERE employee_id IN (SELECT employee_id FROM EMPLOYEE WHERE DEP_HEAD_ID = ?)"; break;
			case 3: sql += "WHERE employee_id IN (SELECT employee_id FROM EMPLOYEE WHERE BENCO_ID = ?)"; break;
			default: sql += "WHERE employee_id = ?";
			}
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);

			switch (roll) {
			case 1: ps.setInt(1, ((DirectSupervisor) u).getDsId()); break;
			case 2: ps.setInt(1, ((DepartmentHead) u).getMyDepHeadId()); break;
			case 3: ps.setInt(1, ((BenCo) u).getMyBenCoId()); break;
			default: ps.setInt(1, u.getId());
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TRForm temp = new TRForm();
				temp.setFormId(rs.getInt(1));
				int empID = rs.getInt(2);
				temp.setEmployeeId(empID);
				temp.setEventType(rs.getInt(3));
				temp.setDate(rs.getDate(4).toString());
				Clob c=rs.getClob(5);
				String reason = c.getSubString(1, (int) c.length());
				temp.setReason(reason);
				c = rs.getClob(10);
				String loc = c.getSubString(1, (int) c.length());
				temp.setLocation(loc);
				temp.setCost(rs.getDouble(6));
				temp.setMissedWorkTime(rs.getDouble(8));
				sql = "SELECT * FROM reimbursement WHERE re_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rs.getInt(9));
				ResultSet rs2 = ps.executeQuery();
				if (rs2.next()) {
					Reimbursement r = new Reimbursement();
					r.setReId(rs.getInt(9));
					r.setFormId(rs.getInt(1));
					r.setEmployeeId(u.getId());
					r.setAmmount_paid(rs2.getDouble(4));
					r.setStatus(rs2.getString(5));
					r.setRequestDate(rs2.getDate(6).toString());
					temp.setReimbursement(r);
					if (roll != -1) {
						sql = "SELECT first_name, last_name FROM employee WHERE employee_id = "+empID;
						Statement s = conn.createStatement();
						ResultSet rs3 = s.executeQuery(sql);
						if (rs3.next()) {
							String name = rs3.getString(1) + " " + rs3.getString(2);
							temp.setName(name);
						}
					}
					trfarr.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trfarr;
	}

}
