package com.example.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.UserDetail;
import com.example.repository.*;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@ModelAttribute
	private void userDetails(Model m, Principal p) {

		String email = p.getName();
		UserDetail user = userRepo.findByEmail(email);
		m.addAttribute("user", user);

	}

	@GetMapping("/")
	public String home() {
		return "user/home";

	}

}
