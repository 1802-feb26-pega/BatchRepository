package com.revature.trms.dao;

import com.revature.trms.pojos.TypeOfEvent;

public interface EventTypeLookUpDAO {

	public int getCoverageById(int id);
	public TypeOfEvent getEventType(int id);
}
