package com.pchase95.project1.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.dao.DepartmentDAO;
import com.pchase95.project1.dao.EmployeeDAO;
import com.pchase95.project1.dao.LocationDAO;
import com.pchase95.project1.pojos.Department;
import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.Location;
import com.pchase95.project1.util.Sanitizer;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = -5625315390789121547L;
	
	static DAO<Employee> empDAO = new EmployeeDAO();
	static DAO<Location> locDAO = new LocationDAO();
	static DAO<Department> depDAO = new DepartmentDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In register servlet");
		
		boolean goodEmail = true;
		String email = req.getParameter("email");
		if (!Sanitizer.sanitizeEmail(email)) {
			String response = "Not a valid email";
			String json = new ObjectMapper().writeValueAsString(response);
			resp.getWriter().write(json);
			goodEmail = false;
		}
		
		if (goodEmail) {
			List<Employee> employees = empDAO.getAll();
			boolean duplicate = false;
			for (Employee e : employees) {
				if (e.getEmail().equals(email)) {
					duplicate = true;
				}
			}
			
			if (!duplicate) {
				String pass = req.getParameter("pass");
				long depId = Long.parseLong(req.getParameter("depId"));
				long supId = Long.parseLong(req.getParameter("supId"));
				
				String country = req.getParameter("country");
				String city = req.getParameter("city");
				String province = req.getParameter("province");
				String postal = req.getParameter("postal");
				String address1 = req.getParameter("address1");
				String address2 = req.getParameter("address2");
				String phone = req.getParameter("phone");
				
				Location loc = new Location();
				loc.setAddress1(address1);
				loc.setAddress2(address2);
				loc.setCity(city);
				loc.setCountry(country);
				loc.setProvince(province);
				loc.setPostalCode(postal);
				loc.setPhone(phone);
				locDAO.add(loc);
				
				Employee emp = new Employee();
				emp.setIsDepartmentHead(false);
				emp.setSuperVisor(empDAO.getById(supId));
				emp.setEmail(email);
				emp.setPassword(pass);
				emp.setDepartment(depDAO.getById(depId));
				emp.setLocation(loc);
				emp.setAvaliableReimbursment(1000.00);
				// now figure out who dep head is
				Employee depHead = null;
				for (Employee e : employees) {
					if (e.getDepartment().getId() == depId && e.isDepartmentHead()) {
						depHead = e;
						break;
					}
				}
				emp.setDepartmentHead(depHead);
				
				empDAO.add(emp);
			} else {
				String response = "Email cannot be duplicate";
				String json = new ObjectMapper().writeValueAsString(response);
				resp.getWriter().write(json);
			}
		}
	}	
}
