package com.revature.trms.dao;

import java.util.Collection;

public interface EventDAO
{
	public Collection<String> getAllEventTypes();
	public Double getEventReimbursement(String event);
}
