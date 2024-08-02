package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.UserDetail;


public interface UserRepository extends JpaRepository<UserDetail,Integer>{
	
	public boolean existsByEmail(String email);
	
	
	public UserDetail findByEmail(String email); 

}
