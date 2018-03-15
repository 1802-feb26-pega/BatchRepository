package com.trms.dao;

public class DirectSupervisorDao {
	private static DirectSupervisorDao me;
	private DirectSupervisorDao() {
		me = this;
	}

	public static DirectSupervisorDao getInstance() {
		return me;
	}
}