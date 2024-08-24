package com.example.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.repository.*;
import org.springframework.ui.Model;

import com.example.service.*;
import com.example.entity.*;

@Controller
@RequestMapping("/doctor/")
public class DoctorController {

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDetail user = userRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("username", user.getFullname());
		return "doctor/home";
	}

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<UserDetail> users = userRepository.findByRole("ROLE_USER");
		model.addAttribute("users", users);
		return "doctor/user-list";
	}

	@GetMapping("/appointment-list")
	public String showDoctorAppointments(Model model, Principal principal) {
		UserDetail doctor = userRepository.findByEmail(principal.getName());
		List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor);
		model.addAttribute("appointments", appointments);
		return "doctor/appointment-list";
	}

	@Autowired
	private UserService userService;

	@PostMapping("/update-appointment-status/{appointmentId}")
	public String updateAppointmentStatus(@PathVariable("appointmentId") int appointmentId,
			@RequestParam("status") String status, Principal principal) {
		appointmentService.updateAppointmentStatus(appointmentId, status);
		UserDetail user = userService.findByEmail(principal.getName());

		if (userService.isAdmin(user)) {
			return "redirect:/doctor/admin-list";
		} else if (user.getRole().equals("ROLE_DOCTOR")) {
			return "redirect:/doctor/appointment-list";
		} else {
			return "redirect:/doctor/user-list";
		}
	}
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/profile")
	public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
	    UserDetail user = userRepo.findByEmail(userDetails.getUsername());
	    model.addAttribute("user", user);
	    return "doctor/profile";
	}


}