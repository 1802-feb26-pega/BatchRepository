package com.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trms.pojos.BenCo;
import com.trms.pojos.DepartmentHead;
import com.trms.pojos.DirectSupervisor;
import com.trms.pojos.Employee;
import com.trms.pojos.User;
import com.trms.util.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	private static EmployeeDaoImpl me;
	private EmployeeDaoImpl() {
		me = this;
	}

	public static EmployeeDaoImpl getInstance() {
		if (me == null) me = new EmployeeDaoImpl();
		return me;
	}

	@Override
	public User getEmployee(String username) {
		User user = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from employee where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet info = ps.executeQuery();
			
			while (info.next()) {
				int roll = info.getInt(12);
				int empId = info.getInt(1);
				switch(roll) {
					default: {//Employee
						user = new Employee();
						break;
					} case 1: { //Direct Supervisor
						user = new DirectSupervisor();
						sql = "select supervisor_id from direct_supervisor where ? = employee_id";
						int id = getId(sql, conn, empId);
						((DirectSupervisor) user).setDsId(id);
						break;
					} case 2: { //Department Head
						user = new DepartmentHead();
						sql = "select dep_head_id from department_head where ? = employee_id";
						int id = getId(sql, conn, empId);
						((DepartmentHead) user).setMyDepHeadId(id);
						break;
					} case 3: {//Benco
						user = new BenCo();
						sql = "select benco_id from benco where ? = employee_id";
						int id = getId(sql, conn, empId);
						((BenCo) user).setMyBenCoId(id);
						break;
					}
				}
				user.setId(empId);
				user.setFirstName(info.getString(2));
				user.setLastName(info.getString(3));
				user.setPaidReimbursments(info.getDouble(4));
				user.setSupervisorId(info.getInt(5));
				user.setDepHeadId(info.getInt(6));
				user.setBenCoId(info.getInt(7));
				user.setDepartment(info.getString(8));
				user.setJobTitle(info.getString(9));
				user.setUsername(info.getString(10));
				user.setPassword(info.getString(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private int getId(String sql, Connection conn, int empId) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		int id = -1;
		while (rs.next()) {
			id = rs.getInt(1);
		}
		return id;
	}

	@Override
	public User getEmployeeById(int id) {
		// TODO: implement getEmployeeByID
		return null;
	}

	@Override
	public User addBenCo(BenCo bc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addDepHead(DepartmentHead dh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addDirectSupervisor(DirectSupervisor ds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addEmployee(Employee e) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee(first_name,last_name,supervisor_id,dep_head_id,benco_id,department,job_title,username,password) "+
					"VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setInt(3, e.getSupervisorId());
			ps.setInt(4, e.getDepHeadId());
			ps.setInt(5, e.getBenCoId());
			ps.setString(6, e.getDepartment());
			ps.setString(7, e.getJobTitle());
			ps.setString(8, e.getUsername());
			ps.setString(9, e.getPassword());
			int val = ps.executeUpdate();
			if (val > 0) {
				conn.commit();
				sql = "SELECT employee_id FROM employee WHERE username = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, e.getUsername());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					e.setId(rs.getInt(1));
				}
				return e;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	//TODO: Other DB access methods
	
}
