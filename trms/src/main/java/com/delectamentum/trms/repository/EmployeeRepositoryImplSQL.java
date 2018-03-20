package com.delectamentum.trms.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.util.ConnectionFactory;

public class EmployeeRepositoryImplSQL implements EmployeeRepository {

	@Override
	public List<Employee> getAll() {
		List<Employee> emps = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM employee";
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery(query);
			
			while(res.next()) {
				Employee cur = new Employee();
				
				cur.setId(res.getInt("employee_id"));
				cur.setId(res.getInt("emptype_id"));
				cur.setAmtPending(res.getDouble("reimbursementpending"));
				cur.setAmtReimbursed(res.getDouble("reimbursementtotal"));
				cur.setEmail(res.getString("email"));
				cur.setFirstname(res.getString("firstname"));
				cur.setLastname(res.getString("lastname"));
				cur.setPassword(res.getString("pwhash"));
				cur.setSupervisorid(res.getInt("reportsto"));
				cur.setDepartment(res.getInt("departmentid"));
				
				emps.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? emps : null;
	}

	@Override
	public Employee getById(int id) {
		Employee target = new Employee();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM employee " +
                           "WHERE employee_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet res = ps.executeQuery();
            while(res.next()) {
            	
            	target.setId(res.getInt("employee_id"));
            	target.setType(res.getInt("emptype_id"));
            	target.setAmtPending(res.getDouble("reimbursementpending"));
            	target.setAmtReimbursed(res.getDouble("reimbursementtotal"));
            	target.setEmail(res.getString("email"));
            	target.setFirstname(res.getString("firstname"));
            	target.setLastname(res.getString("lastname"));
            	target.setPassword(res.getString("pwhash"));
            	target.setSupervisorid(res.getInt("reportsto"));
            	target.setDepartment(res.getInt("departmentid"));
            	
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? target : null;
	}

	@Override
	public Employee save(Employee t) {
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			System.out.println(t);
			c.setAutoCommit(false);
            String query = "INSERT INTO employee (firstname,lastname,email,pwhash,reportsto,emptype_id,reimbursementtotal,reimbursementpending,departmentid)" +
                           "VALUES (?,?,?,?,?,?,?,?,?)";
            String[] key = {"employee_id"};
            PreparedStatement ps = c.prepareStatement(query, key);
            
            ps.setString(1, t.getFirstname());
            ps.setString(2, t.getLastname());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPassword());
            ps.setInt(5,t.getSupervisorid());
            ps.setInt(6,t.getType());
            ps.setDouble(7, t.getAmtReimbursed());
            ps.setDouble(8, t.getAmtPending());
            ps.setInt(9,t.getDepartment());

            int updateValue = ps.executeUpdate();
            System.out.println(updateValue + " updated in EMPLOYEE.");
            
            ResultSet keys = ps.getGeneratedKeys();
            while(keys.next()) {
            	System.out.println(keys.getInt(1));
            	t.setId(keys.getInt(1));
            }
            c.commit();
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
	public Employee update(Employee t) {
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "{CALL update_employee(?,?,?,?,?,?,?,?)}";
            CallableStatement ps = c.prepareCall(query);
            
            ps.setString(1, t.getFirstname());
            ps.setString(2, t.getLastname());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPassword());
            ps.setInt(5,t.getSupervisorid());
            ps.setInt(6,t.getType());
            ps.setDouble(7, t.getAmtReimbursed());
            ps.setDouble(8, t.getAmtPending());
            ps.setInt(9, t.getId());
            
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
	public Employee delete(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getByEmail(String email) {
		Employee target = new Employee();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM employee " +
                           "WHERE email = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1,email);

            ResultSet res = ps.executeQuery();
            while(res.next()) {
                
            	target.setId(res.getInt("employee_id"));
            	target.setType(res.getInt("emptype_id"));
            	target.setAmtPending(res.getDouble("reimbursementpending"));
            	target.setAmtReimbursed(res.getDouble("reimbursementtotal"));
            	target.setEmail(res.getString("email"));
            	target.setFirstname(res.getString("firstname"));
            	target.setLastname(res.getString("lastname"));
            	target.setPassword(res.getString("pwhash"));
            	target.setSupervisorid(res.getInt("reportsto"));
            	target.setDepartment(res.getInt("departmentid"));
            	
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? target : null;
	}

	@Override
	public List<Employee> getAllStandardEmployees() {
		List<Employee> emps = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM employee WHERE emptype_id = 1";
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery(query);
			
			while(res.next()) {
				Employee cur = new Employee();
				
				
				emps.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? emps : null;
	}

	@Override
	public List<Employee> getAllSupervisors() {
		List<Employee> emps = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM employee WHERE emptype_id = 2";
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery(query);
			
			while(res.next()) {
				Employee cur = new Employee();
				
				cur.setId(res.getInt("employee_id"));
				cur.setType(res.getInt("emptype_id"));
				cur.setAmtPending(res.getDouble("reimbursementpending"));
				cur.setAmtReimbursed(res.getDouble("reimbursementtotal"));
				cur.setEmail(res.getString("email"));
				cur.setFirstname(res.getString("firstname"));
				cur.setLastname(res.getString("lastname"));
				cur.setPassword(res.getString("pwhash"));
				cur.setSupervisorid(res.getInt("reportsto"));
				
				emps.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? emps : null;
	}

	@Override
	public List<Employee> getAllDepartmentHeads() {
		List<Employee> emps = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM employee WHERE emptype_id = 3";
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery(query);
			
			while(res.next()) {
				Employee cur = new Employee();
				
				cur.setId(res.getInt("employee_id"));
				cur.setType(res.getInt("emptype_id"));
				cur.setAmtPending(res.getDouble("reimbursementpending"));
				cur.setAmtReimbursed(res.getDouble("reimbursementtotal"));
				cur.setEmail(res.getString("email"));
				cur.setFirstname(res.getString("firstname"));
				cur.setLastname(res.getString("lastname"));
				cur.setPassword(res.getString("pwhash"));
				cur.setSupervisorid(res.getInt("reportsto"));
				
				emps.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? emps : null;
	}

	@Override
	public List<Employee> getAllBenCos() {
		List<Employee> emps = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM employee WHERE emptype_id = 4";
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery(query);
			
			while(res.next()) {
				Employee cur = new Employee();
				
				cur.setId(res.getInt("employee_id"));
				cur.setType(res.getInt("emptype_id"));
				cur.setAmtPending(res.getDouble("reimbursementpending"));
				cur.setAmtReimbursed(res.getDouble("reimbursementtotal"));
				cur.setEmail(res.getString("email"));
				cur.setFirstname(res.getString("firstname"));
				cur.setLastname(res.getString("lastname"));
				cur.setPassword(res.getString("pwhash"));
				cur.setSupervisorid(res.getInt("reportsto"));
				
				emps.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? emps : null;
	}

	@Override
	public Employee getByName(String firstname, String lastname) {
		Employee target = new Employee();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM employee " +
                           "WHERE firstname = ? AND lastname = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1,firstname);
            ps.setString(2,lastname);

            ResultSet res = ps.executeQuery();
            while(res.next()) {
            	target.setId(res.getInt("employee_id"));
            	target.setType(res.getInt("emptype_id"));
            	target.setAmtPending(res.getDouble("reimbursementpending"));
            	target.setAmtReimbursed(res.getDouble("reimbursementtotal"));
            	target.setEmail(res.getString("email"));
            	target.setFirstname(res.getString("firstname"));
            	target.setLastname(res.getString("lastname"));
            	target.setPassword(res.getString("pwhash"));
            	target.setSupervisorid(res.getInt("reportsto"));
            	
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? target : null;
	}

	@Override
	public List<Employee> getAllForOneDepartment(int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllUnderSupervisor(int supervisorId) {
		List<Employee> emps = new ArrayList<>();
		boolean updated = false;
		try(Connection c = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM employee WHERE reportsto = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, supervisorId);
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				Employee cur = new Employee();
				
				cur.setId(res.getInt("employee_id"));
				cur.setType(res.getInt("emptype_id"));
				cur.setAmtPending(res.getDouble("reimbursementpending"));
				cur.setAmtReimbursed(res.getDouble("reimbursementtotal"));
				cur.setEmail(res.getString("email"));
				cur.setFirstname(res.getString("firstname"));
				cur.setLastname(res.getString("lastname"));
				cur.setPassword(res.getString("pwhash"));
				cur.setSupervisorid(res.getInt("reportsto"));
				cur.setDepartment(res.getInt("departmentid"));
				
				emps.add(cur);
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return updated ? emps : null;
	}

}
