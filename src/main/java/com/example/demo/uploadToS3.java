package com.example.demo;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class uploadToS3 {

	
	public String upload(String filename, InputStream fs) {
		
		String fileURI;
		
		BasicAWSCredentials cred = new BasicAWSCredentials(
				"AKIAINUGJKC4BRTAYHEQ","kUu7z3WgLnpcXOMjF/c08YHlkI6huXR3lWG/D2Pl" 
					);
		
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_1)
				.build();
		
		PutObjectRequest putReq = new PutObjectRequest("hw2se2",filename,fs,new ObjectMetadata())
		.withCannedAcl(CannedAccessControlList.PublicRead);
		
		s3client.putObject(putReq);
		
        fileURI =  "http://"+"hw2se2"  +".s3.amazonaws.com/"+filename;
		return fileURI;
	}
}
