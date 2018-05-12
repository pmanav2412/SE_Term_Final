package com.example.demo;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends CrudRepository<Users, String> {
	public List<Users> findAll();
	List<Users> findByName(String name);
	public Optional<Users> findById(String id);
	
}


