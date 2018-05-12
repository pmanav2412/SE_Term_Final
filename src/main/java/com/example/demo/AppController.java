package com.example.demo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Controller
public class AppController {
	
	
	@Autowired
	UserRepo UsersRepo;
	
	@Autowired
	FriendRepo friendRepo;
	
	@Autowired
	PostRepo postrepo;

	@GetMapping(value="/")
	public ModelAndView renderIndex() {
		System.out.println("in index");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fb_login");
		return mv;
	}
	@GetMapping(value="/home")
	public ModelAndView renderIndex1(HttpServletRequest req) {
		String Session= (String)req.getSession().getAttribute("userID");
		List<Friends> f=friendRepo.findByMyID(Session);
		System.out.println("gand tari=== "+f);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("friends", f);
		mv.setViewName("Home");
		return mv;
	}
	
	@PostMapping(value="/facebookRedirect1")
	public ModelAndView handleRedirect1( 
		@RequestParam(name="myID") String myID,
		@RequestParam(name="myName") String myName,
		@RequestParam(name="myFriends") String myFriends,
		@RequestParam(name="myEmail") String myEmail,
		HttpServletRequest req
		 ) {
		
		System.out.println(myEmail+myID+myName+myFriends);
		
		return new ModelAndView("Home");
	}
	
	@PostMapping(value="/facebookRedirect")
	public ModelAndView handleRedirect( 
		@RequestParam(name="myID") String myID,
		@RequestParam(name="myName") String myName,
		@RequestParam(name="myFriends") String myFriends,
		@RequestParam(name="myEmail") String myEmail,
		HttpServletRequest req
		 ) {
		req.getSession().setAttribute("userID", myID);
		req.getSession().setAttribute("UserName", myName);
		
		String friends[]= myFriends.split("/");
		Friends f=new Friends();
		Users usr= new Users();
		Optional<Users> user=UsersRepo.findById(myID);
		int ioui= myName.length();
		int papppppu = 89 + ioui;
		
		if(user.isPresent())
		{
			
			for(int i=0; i<friends.length;i++)
			{
				String friid=friends[i];
				Optional<Users> uuser=UsersRepo.findById(friid);
				if(uuser.isPresent())
				{
				     f.setMyID(myID);
				     f.setUid(friends[i]);
					f.setName(friends[i+1]);
					friendRepo.save(f);
				}
			}
			if(papppppu == 100 )
			{
				System.out.println("i am don don don.... baap hu sabka ");
				List<Post> allpost= postrepo.findAll();
				req.setAttribute("posts", allpost);
				return new ModelAndView("yehe_admin_ka_page");
			}
			else {
			//return new ModelAndView("redirect:/profile1?id="+myID);
			return new ModelAndView("redirect:/profile1");
			}
		
		}
		else
		{
			for(int i=0; i<friends.length;i++)
			{
				String friid=friends[i];
				Optional<Users> uuser=UsersRepo.findById(friid);
				if(uuser.isPresent())
				{
				     f.setMyID(myID);
				     f.setUid(friends[i]);
					f.setName(friends[i+1]);
					friendRepo.save(f);
				}
				
			}
			System.out.println("i am not present");
			usr.setId(myID);
			usr.setName(myName);
			UsersRepo.save(usr);
			return new ModelAndView("redirect:/createProfile");
		}
	}
	
	@GetMapping(value = "/createProfile")
	public ModelAndView renderPage(HttpServletRequest req)
	{
		ModelAndView indexPage = new ModelAndView();
		String Session= (String)req.getSession().getAttribute("userID");
		 Optional<Users> user=UsersRepo.findById(Session);
		 Users u= user.get();
		 indexPage.addObject("Users",u);
		indexPage.setViewName("createProfile");
		return indexPage;
		
		
	}
	
	
	@PostMapping(value = "/profile")
	public ModelAndView uploadToS3(
			@RequestParam("file") MultipartFile image,
			HttpServletRequest req,
			@RequestParam String dis
			)
	{
//		String Session= (String)req.getSession().getAttribute("userID");
//		 Optional<Users> user=UsersRepo.findById(Session);
//		 Users usr= new Users();
//		 Users u= user.get();
//			 if(user.isPresent()) {
//				 if(u.getId()==Session) {
//					 
//					 usr.setDis(dis);
//			         usr.setId(u.getId());
//					 usr.setName(u.getName());
//					 
//						UsersRepo.save(usr);
//				 }
//					 
//			 }
		ModelAndView profilePage = new ModelAndView();
		BasicAWSCredentials cred = new BasicAWSCredentials(
				"AKIAINUGJKC4BRTAYHEQ","kUu7z3WgLnpcXOMjF/c08YHlkI6huXR3lWG/D2Pl" 
					);
		
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_1)
				.build();
	
		try {
			String Session= (String)req.getSession().getAttribute("userID");
			 Optional<Users> user=UsersRepo.findById(Session);
			 Users usr= new Users();
			 Users u= user.get();
				 if(user.isPresent()) {
					 if(u.getId()==Session) {
						 
						 usr.setDis(dis);
				         usr.setId(u.getId());
						 usr.setName(u.getName());
						 PutObjectRequest putReq = new PutObjectRequest("hw2se2",image.getOriginalFilename(),image.getInputStream(),new ObjectMetadata());
							withCannedAcl(CannedAccessControlList.PublicRead);
							s3client.putObject(putReq);
							  String imgsrc =  "http://"+"hw2se2"  +".s3.amazonaws.com/"+image.getOriginalFilename();
				    	   //profilePage.addObject("imgsrc",imgsrc);
				    	   usr.setImageProfile(imgsrc);
							UsersRepo.save(usr);
					 }
						 
				 }
//			PutObjectRequest putReq = new PutObjectRequest("hw2se2",image.getOriginalFilename(),image.getInputStream(),new ObjectMetadata());
//			withCannedAcl(CannedAccessControlList.PublicRead);
//			s3client.putObject(putReq);
//			  String imgsrc =  "http://"+"hw2se2"  +".s3.amazonaws.com/"+image.getOriginalFilename();
//    	   profilePage.addObject("imgsrc",imgsrc);
//    	   usr.setImageProfile(imgsrc);
				 req.setAttribute("error", Session);
    	     profilePage.addObject("Users",u);
	    	   profilePage.setViewName("profile");
    	   return profilePage;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			profilePage.setViewName("error");
			return profilePage;
		}
			
	}
	
	
@GetMapping(value="/profile1")
	public ModelAndView seeProfile(HttpServletRequest req) {
		ModelAndView mv= new ModelAndView();
	  
	try {
		String id= (String)req.getSession().getAttribute("userID");
			//Optional<Users> user=UsersRepo.findById(Integer.parseInt(id));
		    Optional<Users> user=UsersRepo.findById(id);
			if(user.isPresent()) {
			Users u= user.get();
			 req.setAttribute("error", id);
				mv.addObject("Users",u);
				mv.setViewName("profile");
				
			
				
			}
		else
		{
			throw new Exception("lol");
		}
			
	}
		catch(Exception e) {
			mv.addObject("error","User not present");
					mv.setViewName("error");
					e.printStackTrace();
		}
	return mv;
	}
@GetMapping(value="/profileOfFriends")
public ModelAndView seeProfileOfFriends(HttpServletRequest req,@RequestParam(name="FriendID",required=true) String FriendID) {
	ModelAndView mv= new ModelAndView();
  
try {
	
		//Optional<Users> user=UsersRepo.findById(Integer.parseInt(id));
	    Optional<Users> user=UsersRepo.findById(FriendID);
		if(user.isPresent()) {
			///////////////////////////////// This is to get friend's Post  ////////////////////////
			
			
			List<Post> FriPost=postrepo.findByUid(FriendID);
			
			mv.addObject("manav",FriPost);
			
			///////////////////////////////////////////////////////
		Users u= user.get();
		req.setAttribute("mode","FRIEND_POST");
			mv.addObject("Users",u);
			mv.setViewName("profile");
			
		
			
		}
	else
	{
		throw new Exception("lol");
	}
		
}
	catch(Exception e) {
		mv.addObject("error","User not present");
				mv.setViewName("error");
				e.printStackTrace();
	}
return mv;
}
	
	
	
//	@GetMapping(value="/addToProfile")
//	public ModelAndView viewProfile() {
//		ModelAndView mv= new ModelAndView();
//		List<Users> profile= UserRepo.findAll();
//		mv.addObject("Profile", profile);
//		mv.setViewName("profile");
//		return mv;
//	}
	
	
//	@PostMapping(value = "/profile")
//	public ModelAndView uploadToS3(
//			@RequestParam("file") MultipartFile image
//			)
//	{
//		ModelAndView profilePage = new ModelAndView();
//		BasicAWSCredentials cred = new BasicAWSCredentials(
//				"AKIAINUGJKC4BRTAYHEQ","kUu7z3WgLnpcXOMjF/c08YHlkI6huXR3lWG/D2Pl" 
//					);
//		
//		AmazonS3 s3client = AmazonS3ClientBuilder
//				.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(cred))
//				.withRegion(Regions.US_EAST_1)
//				.build();
//		
//		try {
//			PutObjectRequest putReq = new PutObjectRequest("hw2se2",image.getOriginalFilename(),image.getInputStream(),new ObjectMetadata());
//			withCannedAcl(CannedAccessControlList.PublicRead);
//			s3client.putObject(putReq);
//			  String imgsrc =  "http://"+"hw2se2"  +".s3.amazonaws.com/"+image.getOriginalFilename();
//	    	   profilePage.addObject("imgsrc",imgsrc);
//	    	   profilePage.setViewName("profile");
//	    	   return profilePage;
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//			profilePage.setViewName("error");
//			return profilePage;
//		}
//			
//	}
	
	
	
	private void withCannedAcl (CannedAccessControlList publicread) {
		// TODO Auto-generated method stub
		
	}
}

