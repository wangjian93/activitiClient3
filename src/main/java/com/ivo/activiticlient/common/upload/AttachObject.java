package com.ivo.activiticlient.common.upload;

public class AttachObject {

	private long id;

	private String name;

	private String size;

	private String user;

	public AttachObject(long id, String name, String size, String user) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
