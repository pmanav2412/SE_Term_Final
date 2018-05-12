package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Friends {
	@Id
	private String Uid;
	private String myID;
	
	private String name;
	private String email;
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMyID() {
		return myID;
	}
	public void setMyID(String myID) {
		this.myID = myID;
	}
	public Friends()
	{
	}
	

	

}
