package com.revature.trms.service;

import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.dao.DepartmentDAO;
import com.revature.trms.dao.DepartmentDAOImpl;
import com.revature.trms.dao.EmployeeDAO;
import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.dao.FormDAO;
import com.revature.trms.dao.FormDAOImpl;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.Form;

public class FormService
{
	private static FormDAO formDao = new FormDAOImpl();
	private static EmployeeDAO empDao = new EmployeeDAOImpl();
	private static DepartmentDAO departDao = new DepartmentDAOImpl();
	
	// BenCo ONLY
	public Collection<Form> getAllForms()
	{
		Collection<Form> transfer = formDao.getAll();
		//ArrayList<Form> output = (ArrayList<Form>) transfer;
		
		return transfer;
	}
	
	// Regular Employees
	public Collection<Form> getEmpForms(Integer empId)
	{
		Collection<Form> transfer = formDao.getAllByEmployeeId(empId);
		//ArrayList<Form> output = (ArrayList<Form>) transfer;
		
		return transfer;
	}
	
	// Direct Supervisor's Employees and their forms
	public Collection<Form> getSubordinateForms(Integer empID)
	{
		//ArrayList<Employee> subEmps = (ArrayList<Employee>) empDao.getDSEmployees(empID);
		Collection<Form> subForms = new ArrayList<>();
		ArrayList<Employee> subEmps = new ArrayList<>();
		
		subEmps.addAll((ArrayList<Employee>) empDao.getDSEmployees(empID));
		
		if(!subEmps.isEmpty())
		{
			for (int x = 0; x < subEmps.size(); x++)
			{
				subForms.addAll(formDao.getAllByEmployeeId(subEmps.get(x).getEmployeeId()));
			}
		}
		
		return subForms;
	}
	
	// Department Head's Employees and their forms
	// Logic: We need to check Employee ID against the Department Table to see if they are a Department Head
	// Logic: If they ARE a Department Head, then pull all Department Employees
	public Collection<Form> getDepartmentForms(Integer empId, Integer departId)
	{
		Integer departHead = departDao.getDepartmentHead(departId);
		Collection<Form> departForms = new ArrayList<>();
		ArrayList<Employee> departEmps = new ArrayList<>();
		
		if (empId == departHead)
		{
			departEmps = (ArrayList<Employee>) empDao.getDHEmployees(departId);
			
			if(!departEmps.isEmpty())
			{
				for (int x = 0; x < departEmps.size(); x++)
				{
					departForms.addAll(formDao.getAllByEmployeeId(departEmps.get(x).getEmployeeId()));
				}
			}
		}
		
		return departForms;
	}
	
	// Getting a particular Form
	public Form getFormById(Integer formId)
	{
		Form output = formDao.getByFormId(formId);
		
		return output;
	}
	
	// Adding Forms
	public boolean addNewForm(Integer empId, Form newForm)
	{
		newForm = formDao.addForm(empId, newForm);
		
		if (newForm.getFormId() != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Updating Forms
	public boolean updateForm(Integer formID, Form updatedForm)
	{
		int rowsChanged = formDao.updateForm(formID, updatedForm);
		
		if (rowsChanged > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
