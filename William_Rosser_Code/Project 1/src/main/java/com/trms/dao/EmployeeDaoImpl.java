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
			
			if (info.next()) {
				int roll = info.getInt(12);
				int empId = info.getInt(1);
				switch(roll) {
					 case 1: { //Direct Supervisor
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
					default: {//Employee
						user = new Employee();
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
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee(first_name,last_name,supervisor_id,dep_head_id,benco_id,department,job_title,username,password,roll) "+
					"VALUES (?,?,?,?,?,?,?,?,?,3)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bc.getFirstName());
			ps.setString(2, bc.getLastName());
			ps.setInt(3, bc.getSupervisorId());
			ps.setInt(4, bc.getDepHeadId());
			ps.setInt(5, bc.getBenCoId());
			ps.setString(6, bc.getDepartment());
			ps.setString(7, bc.getJobTitle());
			ps.setString(8, bc.getUsername());
			ps.setString(9, bc.getPassword());
			int val = ps.executeUpdate();
			if (val > 0) {
				System.out.println("inserted employee");
				sql = "SELECT employee_id FROM employee WHERE username = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, bc.getUsername());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					System.out.print("Got Employee, ");
					int id = rs.getInt(1);
					bc.setId(id);
					System.out.println("ID=" + id);
					sql = "INSERT INTO benco(employee_id) VALUES (?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					val = ps.executeUpdate();
					if (val > 0) {
						System.out.println("Added supervisor");
						sql = "SELECT benco_id FROM benco WHERE employee_id = ?";
						ps = conn.prepareStatement(sql);
						ps.setInt(1, id);
						rs = ps.executeQuery();
						if (rs.next()) {
							id = rs.getInt(1);
							System.out.println("Got benco id, id = "+ id);
							bc.setMyBenCoId(id);
							conn.commit();
						}
					}
				}
				conn.close();
				return bc;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public User addDepHead(DepartmentHead dh) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee(first_name,last_name,supervisor_id,dep_head_id,benco_id,department,job_title,username,password,roll) "+
					"VALUES (?,?,?,?,?,?,?,?,?,2)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dh.getFirstName());
			ps.setString(2, dh.getLastName());
			ps.setInt(3, dh.getSupervisorId());
			ps.setInt(4, dh.getDepHeadId());
			ps.setInt(5, dh.getBenCoId());
			ps.setString(6, dh.getDepartment());
			ps.setString(7, dh.getJobTitle());
			ps.setString(8, dh.getUsername());
			ps.setString(9, dh.getPassword());
			int val = ps.executeUpdate();
			if (val > 0) {
				System.out.println("inserted employee");
				sql = "SELECT employee_id FROM employee WHERE username = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, dh.getUsername());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					System.out.print("Got Employee, ");
					int id = rs.getInt(1);
					dh.setId(id);
					System.out.println("ID=" + id);
					sql = "INSERT INTO department_head(employee_id) VALUES (?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					val = ps.executeUpdate();
					if (val > 0) {
						System.out.println("Added supervisor");
						sql = "SELECT dep_head_id FROM department_head WHERE employee_id = ?";
						ps = conn.prepareStatement(sql);
						ps.setInt(1, id);
						rs = ps.executeQuery();
						if (rs.next()) {
							id = rs.getInt(1);
							System.out.println("Got Dep head id, id = "+ id);
							dh.setMyDepHeadId(id);
							conn.commit();
						}
					}
				}
				conn.close();
				return dh;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public User addDirectSupervisor(DirectSupervisor ds) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee(first_name,last_name,supervisor_id,dep_head_id,benco_id,department,job_title,username,password, roll) "+
					"VALUES (?,?,?,?,?,?,?,?,?,1)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ds.getFirstName());
			ps.setString(2, ds.getLastName());
			ps.setInt(3, ds.getSupervisorId());
			ps.setInt(4, ds.getDepHeadId());
			ps.setInt(5, ds.getBenCoId());
			ps.setString(6, ds.getDepartment());
			ps.setString(7, ds.getJobTitle());
			ps.setString(8, ds.getUsername());
			ps.setString(9, ds.getPassword());
			int val = ps.executeUpdate();
			if (val > 0) {
				System.out.println("inserted employee");
				sql = "SELECT employee_id FROM employee WHERE username = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, ds.getUsername());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					System.out.print("Got Employee, ");
					int id = rs.getInt(1);
					ds.setId(id);
					System.out.println("ID=" + id);
					sql = "INSERT INTO direct_supervisor(employee_id) VALUES (?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					val = ps.executeUpdate();
					if (val > 0) {
						System.out.println("Added supervisor");
						sql = "SELECT supervisor_id FROM direct_supervisor WHERE employee_id = ?";
						ps = conn.prepareStatement(sql);
						ps.setInt(1, id);
						rs = ps.executeQuery();
						if (rs.next()) {
							id = rs.getInt(1);
							System.out.println("Got Supervisor id - id = "+ id);
							ds.setDsId(id);
							conn.commit();
						}
					}
				}
				conn.close();
				return ds;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public User addEmployee(Employee e) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee(first_name,last_name,supervisor_id,dep_head_id,benco_id,department,job_title,username,password,roll) "+
					"VALUES (?,?,?,?,?,?,?,?,?,0)";
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
			e1.printStackTrace();
		}
		return null;
	}
	
	//TODO: Other DB access methods
	
}
