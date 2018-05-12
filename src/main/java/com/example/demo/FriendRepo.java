package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepo extends CrudRepository<Friends, String> {
	List<Friends> findAll();
	public List<Friends> findByMyID(String myID);

	

	
}
