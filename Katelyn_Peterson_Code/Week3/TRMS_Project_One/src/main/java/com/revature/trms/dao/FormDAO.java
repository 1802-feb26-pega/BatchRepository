package com.revature.trms.dao;

import java.util.Collection;

import com.revature.trms.pojos.Form;

public interface FormDAO
{
	public boolean hasForms();
	// For BenCo
	public Collection<Form> getAll();
	public Collection<Form> getAllByEmployeeId(Integer empId);
	public Form getByFormId(Integer formId);
	public Form addForm(Integer empId, Form newForm);
	public int updateForm(Integer formID, Form updatedForm);
}
