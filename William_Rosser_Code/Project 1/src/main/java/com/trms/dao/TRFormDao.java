package com.trms.dao;

public class TRFormDao {
	private static TRFormDao me;
	private TRFormDao() {
		me = this;
	}

	public static TRFormDao getInstance() {
		if (me == null) me = new TRFormDao();
		return me;
	}
}
