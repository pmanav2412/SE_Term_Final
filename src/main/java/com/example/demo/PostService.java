//package com.example.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.stereotype.Service;
//
//import com.example.demo.Post;
//import com.example.demo.PostRepo;
//
//
//
//@Service
//@Transactional
//public class PostService {
//	private final PostRepo postrepo;
//	public PostService(PostRepo postrepo) {
//		this.postrepo=postrepo;
//	}
//	public List<Post> findAll(String Uid) {
//	    Post PostRepo = PostRepo.findByUid(Uid);
//	    return findAll(Post.getUid());
//}
//}
