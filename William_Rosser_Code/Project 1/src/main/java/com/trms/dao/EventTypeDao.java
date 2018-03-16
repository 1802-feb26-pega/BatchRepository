package com.trms.dao;

public class EventTypeDao {
	private static EventTypeDao me;
	private EventTypeDao() {
		me = this;
	}

	public static EventTypeDao getInstance() {
		if (me == null) me = new EventTypeDao();
		return me;
	}
}
