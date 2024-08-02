package com.example.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.UserDetail;

public class CustomUserDetails implements UserDetails {

	private UserDetail user;

	public CustomUserDetails(UserDetail user) { 
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedauthority = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(simpleGrantedauthority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

}
