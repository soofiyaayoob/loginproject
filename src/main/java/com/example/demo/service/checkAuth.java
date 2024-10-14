package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserMakbig;



import com.example.demo.repositryLayer.RepoMakbig;
@Service
public class checkAuth implements UserDetailsService{
	@Autowired
	RepoMakbig repoMakbig;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMakbig user=repoMakbig.findByUsername(username);
		if (user==null){
			System.out.println("user not found");
			throw new UsernameNotFoundException ("oh ithink your are not existed");
		}
		
		  return User.withUsername(user.getUsername())
                  .password(user.getPassword())
                  .build();
		}
		
	}


