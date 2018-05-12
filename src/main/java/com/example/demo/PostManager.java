package com.example.demo;


import java.util.List;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.util.Base64.Decoder;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import java.io.ObjectOutputStream;

@Controller
public class PostManager {
	
	@Autowired
	uploadToS3 s3;
	
	@Autowired
	PostRepo postrepo;
	
	@Autowired
	UserRepo UsersRepo;
	
	@Autowired
	NotiRepo notirepo;
	
	@Autowired
	NotiRepo1 notirepo1;
	
	@Autowired
	CommentRepo commentrepo;
	
	@Autowired
	FriendRepo friendRepo;
	
	Random rand= new Random();
	
	@GetMapping(value="/recordAudio")
	public ModelAndView renderIndex() {
		
		 return new ModelAndView("recordAudio");
		
		
	}
	
	@PostMapping(value="/base64Audio")
	public ModelAndView saveAudio(HttpServletRequest req, @RequestParam("123") String recording,@RequestParam("1234") String photo,@RequestParam String PostDis) throws Exception  {
		System.out.println("this is the value of recording"+recording);
		
		System.out.println("this is the value of photo"+ photo);
		ModelAndView mv = new ModelAndView();
		
		if(recording.isEmpty()) throw new Exception("recording data is null");
		Decoder decoder= Base64.getDecoder();
		System.out.println("recording");
		byte[] decodedByte= decoder.decode(recording.split(",")[1]);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("MyAudio.webm");
			fos.write(decodedByte);
			fos.close();
		} catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
		////////////////////////////////// THIS IS PHOTO TO STORE INTO THE DATABASE OF S3 /////////////////////////
		
		Decoder decoder1= Base64.getDecoder();
		System.out.println("recording");
		byte[] decodedByte1= decoder1.decode(photo.split(",")[1]);
		FileOutputStream fos1;
		try {
			fos1 = new FileOutputStream("bhainoPhoto.png");
			fos1.write(decodedByte1);
			fos1.close();
		} catch(IOException e) 
		{
			e.printStackTrace();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int value = rand.nextInt(500);
		String ssid= (String)req.getSession().getAttribute("userID");
		String uname=(String)req.getSession().getAttribute("UserName");
		String PostID= Integer.toString(value);
		
		String uploadURI = s3.upload(ssid +PostID +".webm", new FileInputStream("MyAudio.webm"));
		String uploadPHOTO= s3.upload(ssid + PostID + ".png", new FileInputStream("bhainoPhoto.png"));
		/////////////////////////////////////////// STORE THE DATA INTO DATABASE  /////////////////////////////////////////////////////////////////////
		
	
		
		
		Post p=new Post();
		p.setPhotoURL(uploadPHOTO);
		p.setAudioURL(uploadURI);
		p.setPostID(PostID);
		p.setUid(ssid);
		p.setPostDis(PostDis);
		postrepo.save(p);
		////////////////////////////////// ADD Data to Notification Table ////////////////////
		List<Friends> f=friendRepo.findByMyID(ssid);
		Friends fri=new Friends();
		
		for(int i=0;i<f.size();i++)
		{
			fri=f.get(i);
			
		Noti noti = new Noti();
		System.out.println("number of friends===== "+f.size());
		noti.setPid(PostID);
		noti.setUid(ssid);
		noti.setFid(fri.getUid());
		noti.setUname(uname);
		notirepo.save(noti);
		}
		mv.addObject("manav",uploadURI);
		mv.setViewName("Success");
		return mv;
	}
	
	@GetMapping(value="/postList")
	public ModelAndView getallPost(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String ssid= (String)req.getSession().getAttribute("userID");
		List<Post> user=postrepo.findByUid(ssid);
		
		mv.addObject("manav",user);
		 mv.setViewName("Post_List");
		 return mv;
		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		 ModelAndView mv = new ModelAndView();
//			String ssid= (String)req.getSession().getAttribute("userID");
////			List<Post> user=postrepo.findByUid(ssid);
//			List<Post> p1= new ArrayList<Post>();
//			for(Post px: postrepo.findByUid(ssid)) {
//				
//				p1.add(px);
//			}
//			
//			mv.addObject("manav",p1.get(1));
//			 mv.setViewName("Post_List");
//			 return mv;
		 
			 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	}
	
	
	
	@PostMapping(value="/addComment")
	public ModelAndView addPost(HttpServletRequest req,@RequestParam String comment,@RequestParam(name="pid",required=true) String pid) {
		String userId= (String)req.getSession().getAttribute("userID");
		ModelAndView mv= new ModelAndView();
		System.out.println("length"+comment.length());
		if(comment.length()==0)
		{
			
			 List<Comments> ComeList= commentrepo.findByPostid(pid);
			    mv.addObject("Comments", ComeList);
			req.setAttribute("error", "Enter the comment");
			mv.addObject("pid",pid);
			mv.setViewName("SeePost");
			 return mv;
		}
		else
		{
			
		Optional<Users> user=UsersRepo.findById(userId);
		System.out.println("this is pid khaber padi=== "+comment);
		///////////////////////////// comment noti ///////////
		String ssid= (String)req.getSession().getAttribute("userID");
		String uname=(String)req.getSession().getAttribute("UserName");
		List<Friends> f=friendRepo.findByMyID(ssid);
		Friends fri=new Friends();
		
		for(int i=0;i<f.size();i++)
		{
			fri=f.get(i);
			
		noti1 noti11 = new noti1();
		System.out.println("number of friends===== "+f.size());
		noti11.setPid(pid);
		noti11.setUid(ssid);
		noti11.setFid(fri.getUid());
		noti11.setUname(uname);
		notirepo1.save(noti11);
		}
		////////////////////////////////////////////////////////
		Users u=user.get();
		
		Comments c=new Comments();
		c.setComment(comment);
		c.setUsername(u.getName());
		c.setUserid(userId);
		c.setPostid(pid);
		commentrepo.save(c);
		//mv.setViewName("SeePost");
		mv.setViewName("redirect:/getComments?pid="+pid);
		
		return mv;
		}
	}
	
	@GetMapping(value="/getComments")
	public ModelAndView seeThisPost(HttpServletRequest req,@RequestParam(name="pid",required=true) String pid) {
		ModelAndView mv= new ModelAndView();
		//Comments c=new Comments();
		
		List<Comments> ComeList= commentrepo.findByPostid(pid);
		System.out.println("hihihihihihhihhihihihih u r ginius manav patel=== "+ComeList);
		mv.addObject("Comments", ComeList);
		mv.addObject("pid",pid);
		mv.setViewName("SeePost");
		return mv;
	}
	
	@GetMapping(value="/seePost")
	public ModelAndView seePost(HttpServletRequest req, @RequestParam(name="pid",required=true) String pid) {
		ModelAndView mv= new ModelAndView();
	  
		//String id= (String)req.getSession().getAttribute("userID");
			System.out.println("this is fucking post id=== "+pid);
	     
//		Post p1= postrepo.findByPostID(pid);
			//Post p2=postrepo.findByPostID(pid);
			Optional<Post> p3=postrepo.findById(pid);
			Post p=p3.get();
			String po= p.getPhotoURL();
			System.out.println("bc photo avi java de ==== "+ po);
		String  s=p.getAudioURL();
	    mv.addObject("manavA",s);
	    mv.addObject("manavP",po);
	    mv.addObject("pid",p.getPostID());
	    mv.addObject("dis",p.getPostDis());
	    mv.addObject("photoURL",p.getPhotoURL());
	    System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqq== "+p.getAudioURL());
	    ///////////////////////// This loads Comments //////////////////////////////
	    List<Comments> ComeList= commentrepo.findByPostid(pid);
	    mv.addObject("Comments", ComeList);
	    ////////////////////////////////////////////////////////////////////////////////
	     mv.setViewName("SeePost");
			return mv;
	}
	
	@GetMapping(value="/getnotification")
	public ModelAndView getnotification(HttpServletRequest req) {
		ModelAndView mv= new ModelAndView();
		//Comments c=new Comments();
		String uid= (String)req.getSession().getAttribute("userID");
		List<Noti> notList= notirepo.findByFid(uid);
		System.out.println("Are bhai bhai bhai.......===== "+  notList);
		 Optional<Users> user=UsersRepo.findById(uid);
		Users u= user.get();
		req.setAttribute("mode","NOTIFICATION");
			mv.addObject("Users",u);
			mv.addObject("NOTE",notList);
			
		mv.setViewName("profile");
		return mv;
	}
}
	

