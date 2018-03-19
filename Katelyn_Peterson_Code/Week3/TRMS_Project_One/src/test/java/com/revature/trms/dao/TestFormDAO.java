package com.revature.trms.dao;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.revature.trms.pojos.Form;

public class TestFormDAO
{
	static private FormDAOImpl formDao = new FormDAOImpl();
	
	@Test
	public void testGetAll()
	{
		boolean find = false;
		
		Collection<Form> testForms = formDao.getAll();
		
		if(!testForms.isEmpty())
		{
			find = true;
		}
		
		assertTrue(find);
	}

}
