package com.revature.trms.dao;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

public class TestEventDAO
{
	static private EventDAOImpl eventDao = new EventDAOImpl();

	@Test
	public void testGetAllGradeFormats()
	{
		boolean find = false;
		
		Collection<String> testEvent = eventDao.getAllEventTypes();
		
		if(!testEvent.isEmpty())
		{
			find = true;
		}
		
		assertTrue(find);
	}
}
