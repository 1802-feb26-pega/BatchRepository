package com.pchase95.project1.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchase95.project1.dao.ApprovalDAO;
import com.pchase95.project1.dao.ApprovalTypeDAO;
import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.dao.EmployeeDAO;
import com.pchase95.project1.dao.EmployeeRBMTDao;
import com.pchase95.project1.dao.ReimbursmentDAO;
import com.pchase95.project1.pojos.Approval;
import com.pchase95.project1.pojos.ApprovalType;
import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.Reimbursment;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = -7252379944421863843L;
	
	static EmployeeRBMTDao bridgeDAO = new EmployeeRBMTDao();
	static DAO<Approval> approvalDAO = new ApprovalDAO();
	static EmployeeDAO empDAO = new EmployeeDAO();
	static DAO<ApprovalType> typeDAO = new ApprovalTypeDAO();
	static DAO<Reimbursment> rbmtDAO = new ReimbursmentDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in apply servlet");
		Reimbursment rbmt = new ObjectMapper().readValue(req.getInputStream(), Reimbursment.class);
		Employee emp = (Employee) req.getSession().getAttribute("employee");
		
		System.out.println(rbmt);
		System.out.println(emp);
		
		Approval approvalSupervisor = new Approval();
		approvalSupervisor.setApprover(empDAO.getById(emp.getSuperVisor().getId()));
		approvalSupervisor.setRecipient(emp);
		approvalSupervisor.setReimbursment(rbmt);
		approvalSupervisor.setType(typeDAO.getById(ApprovalType.PENDING));
		
		
		List<Employee> emps = empDAO.getByDepartmentId(emp.getDepartment().getId());
		Employee depHead = null;
		for (Employee e : emps) {
			if (e.isDepartmentHead()) {
				depHead = e;
			}
		}
		Approval approvalDepHead = new Approval();
		approvalDepHead.setApprover(depHead);
		approvalDepHead.setRecipient(emp);
		approvalDepHead.setReimbursment(rbmt);
		approvalDepHead.setType(typeDAO.getById(ApprovalType.PENDING));
		
		Approval approvalBenco = new Approval();
		approvalBenco.setApprover(empDAO.getByEmail(Employee.benCoEmail));
		approvalBenco.setRecipient(emp);
		approvalBenco.setReimbursment(rbmt);
		approvalBenco.setType(typeDAO.getById(ApprovalType.PENDING));
		
		approvalDAO.add(approvalSupervisor);
		approvalDAO.add(approvalDepHead);
		approvalDAO.add(approvalBenco);
		
		bridgeDAO.add(emp.getId(), rbmt.getId());
	}
}
