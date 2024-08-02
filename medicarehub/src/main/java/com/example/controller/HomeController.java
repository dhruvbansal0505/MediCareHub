package com.example.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.UserService;
import com.example.entity.UserDetail;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDetail user, HttpSession session) {

		boolean emailExists = userService.checkEmail(user.getEmail());

		System.out.println(user);

		if (emailExists) {
			session.setAttribute("msg", "Email Id already exist");
			System.out.println("Email Id already exist");
			session.removeAttribute("msg");
		} else {
			UserDetail userDtls = userService.createUser(user);
			if (userDtls != null) {
				System.out.println("Registered Successfully");
				session.setAttribute("msg", "Registered Successfully");
			} else {
				System.out.println("Not Registered Successfully");
				session.setAttribute("msg", " Not Registered");
			}
		}

		session.removeAttribute("msg");

		return "redirect:/register";

	}

}
