package com.example.service;

import com.example.entity.UserDetail;
public interface UserService {

	public UserDetail createUser(UserDetail user);
	public boolean checkEmail(String email);
}
