package com.tesco.springBatchExample.model;

import java.sql.Timestamp;

public class User {
	 
	private String name;
	private Timestamp createdDate;
	
	@Override
	public String toString() {
		return "User [name=" + name + ", createdDate=" + createdDate + "]";
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	 
}