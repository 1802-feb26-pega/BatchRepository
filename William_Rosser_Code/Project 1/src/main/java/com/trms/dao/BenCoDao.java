package com.trms.dao;

public class BenCoDao {
	private static BenCoDao me;
	private BenCoDao() {
		me = this;
	}
	
	public static BenCoDao getInstance() {
		return me;
	}
}
