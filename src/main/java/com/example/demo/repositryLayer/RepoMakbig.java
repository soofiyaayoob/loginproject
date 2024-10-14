package com.example.demo.repositryLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserMakbig;

@Repository
public interface RepoMakbig extends JpaRepository<UserMakbig,Integer>{

	public UserMakbig findByUsername(String username);

}
