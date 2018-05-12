package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	

	@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
	 private String id;
	private String name;
	private String dis;
	private String imageProfile;
public String getImageProfile() {
		return imageProfile;
	}
	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}
	//	private String FbId;
//	
//	public String getFbId() {
//		return FbId;
//	}
//	public void setFbId(String FbId) {
//		FbId = fbId;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public Users()
	{
	}
	
	}
