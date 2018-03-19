package com.revature.trms.service;

import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.dao.GradeDAO;
import com.revature.trms.dao.GradeDAOImpl;

public class GradeService
{
	private static GradeDAO gradeDao = new GradeDAOImpl();
	
	public ArrayList<String> getAllGrades()
	{
		Collection<String> transfer = gradeDao.getAllGradeFormats();
		ArrayList<String> output = (ArrayList<String>) transfer;
		
		return output;
	}
}
