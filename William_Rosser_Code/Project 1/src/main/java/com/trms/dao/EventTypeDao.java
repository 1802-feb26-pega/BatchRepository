package com.trms.dao;

public class EventTypeDao {
	private static EventTypeDao me;
	private EventTypeDao() {
		me = this;
	}

	public static EventTypeDao getInstance() {
		return me;
	}
}
