package com.revature.trms.dao;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

public class TestGradeDAO
{
	static private GradeDAOImpl gradeDao = new GradeDAOImpl();

	@Test
	public void testGetAllGradeFormats()
	{
		boolean find = false;
		
		Collection<String> testGrades = gradeDao.getAllGradeFormats();
		
		if(!testGrades.isEmpty())
		{
			find = true;
		}
		
		assertTrue(find);
	}

}
