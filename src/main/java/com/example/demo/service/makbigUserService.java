package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserMakbig;

import com.example.demo.repositryLayer.RepoMakbig;

@Service
public class makbigUserService {
	@Autowired
	RepoMakbig repomakbig;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;

	public boolean addUser(UserMakbig user) {
		
		try {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repomakbig.save(user);
		System.out.println("hello");
		return true;
		
		}catch(Exception e) {
		return false;
		}
        
	}
	
	
	public boolean verify(UserMakbig user) {
		try {
	       
	        Authentication authentication = 
	            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
	        
	     
	        Authentication result = authenticationManager.authenticate(authentication);
	        
	       
	        return result.isAuthenticated();
	    } catch (AuthenticationException e) {
	        
	        return false;
	    }
	 }
	
	public List<UserMakbig> users() {
		return repomakbig.findAll();
		
	}


//	public static void update(UserMakbig user) {
//		
//		for(int i=0;i<UserMakbig.size();i++)
//		if(UserMakbig )==user.getUsername()) {
//				product.set( i, prod);
//				break;
//				
//	
//		
//	}
}
