package com.example.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Appointment;
import com.example.entity.UserDetail;
import com.example.service.AppointmentService;
import com.example.service.DepartmentService;
import com.example.service.UserService;
import com.example.repository.UserRepository;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

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

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/request-form")
	public String showRequestAppointmentForm(Model model) {
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("doctors", userService.findAllDoctors());
		model.addAttribute("departments", departmentService.getAllDepartments());
		return "user/request-form";
	}

	@GetMapping("/appointment-list")
	public String showUserAppointments(Model model, Principal principal) {
		UserDetail user = userService.findByEmail(principal.getName());
		List<Appointment> appointments = appointmentService.getAppointmentsByUser(user);

		/*
		 * System.out.println("User: " + user); System.out.println("Appointments: " +
		 * appointments);
		 */

		model.addAttribute("appointments", appointments);
		return "user/appointment-list";
	}

	@GetMapping("/profile")
	public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		UserDetail user = userRepo.findByEmail(userDetails.getUsername());
		model.addAttribute("user", user);
		return "user/profile";
	}

	@GetMapping("/doctors")
	public String viewDoctors(Model model) {
		List<UserDetail> doctors = userService.getAllDoctors();
		model.addAttribute("doctors", doctors);
		return "user/doctor-list";
	}
	
}
