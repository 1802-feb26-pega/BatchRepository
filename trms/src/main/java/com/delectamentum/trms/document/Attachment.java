package com.delectamentum.trms.document;

import java.sql.Blob;

public class Attachment {
	private int id,
				claim_id;
	
	private Blob content;
	
	public Attachment() {}

	public Attachment(int id, int claim_id, Blob content) {
		super();
		this.id = id;
		this.claim_id = claim_id;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(int claim_id) {
		this.claim_id = claim_id;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}
	
	
}
