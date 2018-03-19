package com.revature.trms.service;

import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.dao.FormDAO;
import com.revature.trms.dao.FormDAOImpl;
import com.revature.trms.pojos.Form;

public class FormService
{
	private static FormDAO formDao = new FormDAOImpl(); 
	
	// BenCo ONLY
	public ArrayList<Form> getAllForms()
	{
		Collection<Form> transfer = formDao.getAll();
		ArrayList<Form> output = (ArrayList<Form>) transfer;
		
		return output;
	}
	
	// Regular Employees
	public ArrayList<Form> getEmpForms(Integer empId)
	{
		Collection<Form> transfer = formDao.getAllByEmployeeId(empId);
		ArrayList<Form> output = (ArrayList<Form>) transfer;
		
		return output;
	}
	
	// Direct Supervisors/Department Heads?
	
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
