package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiRepo extends CrudRepository<Noti, Integer>{
public List<Noti> findAll();
	
	public List<Noti> findByFid(String Fid);

}
