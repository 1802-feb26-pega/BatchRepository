package com.trms.dao;

public class AttachmentDao {
	private static AttachmentDao me;
	private AttachmentDao() {
		me = this;
	}

	public static AttachmentDao getInstance() {
		if (me == null) me = new AttachmentDao();
		return me;
	}
}
