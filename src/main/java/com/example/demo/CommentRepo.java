package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comments, Integer>  {
   public List<Comments> findAll();
   public List<Comments> findByPostid(String Postid);
}





