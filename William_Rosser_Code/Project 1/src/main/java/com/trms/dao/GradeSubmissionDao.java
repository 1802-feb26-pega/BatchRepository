package com.trms.dao;

public class GradeSubmissionDao {
	private static GradeSubmissionDao me;
	private GradeSubmissionDao() {
		me = this;
	}

	public static GradeSubmissionDao getInstance() {
		if (me == null) me = new GradeSubmissionDao();
		return me;
	}
}
