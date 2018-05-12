package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {
	
	
	private String uid;
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	public String PostID;
	private String AudioURL;
	private String PhotoURL;
	private String PostDis;
	
	public String getPostDis() {
		return PostDis;
	}

	public void setPostDis(String postDis) {
		PostDis = postDis;
	}

	public Post()
	{
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPostID() {
		return PostID;
	}

	public void setPostID(String postID) {
		PostID = postID;
	}

	public String getAudioURL() {
		return AudioURL;
	}

	public void setAudioURL(String audioURL) {
		AudioURL = audioURL;
	}

	public String getPhotoURL() {
		return PhotoURL;
	}

	public void setPhotoURL(String photoURL) {
		PhotoURL = photoURL;
	}
}
