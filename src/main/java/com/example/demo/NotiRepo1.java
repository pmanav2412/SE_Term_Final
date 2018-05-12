package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiRepo1 extends CrudRepository<noti1, Integer>{
public List<noti1> findAll();
	
	public List<noti1> findByFid(String Fid);


}


