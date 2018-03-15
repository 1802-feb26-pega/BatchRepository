package com.trms.dao;

public class ReimbursmentDao {
	private static ReimbursmentDao me;
	private ReimbursmentDao() {
		me = this;
	}

	public static ReimbursmentDao getInstance() {
		return me;
	}
}
