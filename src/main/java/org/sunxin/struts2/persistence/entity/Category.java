package org.sunxin.struts2.persistence.entity;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1346312857873213071L;
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}