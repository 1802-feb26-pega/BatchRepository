package com.trms.dao;

public class EmployeeDao {
	private static EmployeeDao me;
	private EmployeeDao() {
		me = this;
	}

	public static EmployeeDao getInstance() {
		return me;
	}
}
