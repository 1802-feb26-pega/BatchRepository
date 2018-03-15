package com.trms.dao;

public class TRFormDao {
	private static TRFormDao me;
	private TRFormDao() {
		me = this;
	}

	public static TRFormDao getInstance() {
		return me;
	}
}
