package com.tailock.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ATTACHMENT")
public class AdditionalAttachments {
	
	//generate an id
	@Id @GeneratedValue
	@Column(name = "attachment_id")
	private int attachId;
	
	//reference a request, but a request can have multiple documents
	@ManyToOne
	@JoinColumn(name = "request_id")
	private ReimbursementRequest reimReq;
	
	//files can have various types -- Lookup Table?
	@Column(name = "type_of_file")
	private String fileType;
	
	//file obviously needs a name. [[Keep extension? Or determine by type]]
	@Column(name = "file_name")
	private String fileName;
	
	//file needs to be stored as a blob, may need to add more || SWITCHING TO LOCATION OF ATTACHMENT
	@Column(name = "file_blob")
	@Lob
	private byte[] fileBlob;
	
	/**
	 * need to reference the approval needed since the type of attachment can affect the
	 * necessary approval
	 * 
	 * Will Reference approval type needed look-up table [[DONT FORGET TO DO THIS]]
	 * 
	 * 
	 */
	
}