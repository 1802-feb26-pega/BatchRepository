package com.trms.dao;

public class ReimbursmentDao {
	private static ReimbursmentDao me;
	private ReimbursmentDao() {
		me = this;
	}

	public static ReimbursmentDao getInstance() {
		if (me == null) me = new ReimbursmentDao();
		return me;
	}
}
