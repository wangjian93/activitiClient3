package com.ivo.activiticlient.common.upload;



import com.ivo.activiticlient.domain.Employee;
import com.ivo.activiticlient.domain.ModelAtom;

import javax.persistence.*;

@Entity
@Table(name = "AT_M_Attachment")
public class Attachment extends ModelAtom {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String order;

	private String fileName;

	private long size;

	private Employee user;

	private Long edition;
	
	private String edocId;

	public Attachment() {
	}

	public Attachment(String order, String fileName, long size, Employee user,
			Long edition) {
		this.order = order;
		this.fileName = fileName;
		this.size = size;
		this.user = user;
		this.edition = edition;
	}
	
	public Attachment(String order, String fileName, long size, Employee user,
			Long edition, String edocId) {
		this.order = order;
		this.fileName = fileName;
		this.size = size;
		this.user = user;
		this.edition = edition;
		this.edocId = edocId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ORDER_NUMBER")
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_SIZE")
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_FK")
	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	@Column(name = "EDITION")
	public Long getEdition() {
		return edition;
	}

	public void setEdition(Long edition) {
		this.edition = edition;
	}
	
	@Column(name = "Edoc_FK")
	public String getEdocId() {
		return edocId;
	}

	public void setEdocId(String edocId) {
		this.edocId = edocId;
	}

}
