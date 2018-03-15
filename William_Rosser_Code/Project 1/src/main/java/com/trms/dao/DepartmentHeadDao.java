package com.trms.dao;

public class DepartmentHeadDao {
	private static DepartmentHeadDao me;
	private DepartmentHeadDao() {
		me = this;
	}
	
	public static DepartmentHeadDao getInstance() {
		return me;
	}
}