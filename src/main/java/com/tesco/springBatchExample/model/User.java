package com.tesco.springBatchExample.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String id;
	private String name;
	private Timestamp createdDate;
	
	public User(){}
	public User(String name) {
		this.name = name;
	}
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}