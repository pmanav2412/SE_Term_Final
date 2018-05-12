package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepo extends CrudRepository<Post, String> {
	public List<Post> findAll();
	
	public List<Post> findByUid(String Uid);
	
   //  public Post findByPostID(String PostID);
	
}




