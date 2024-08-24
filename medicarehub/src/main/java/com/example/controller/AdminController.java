
package com.example.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.service.AdminService;
import java.util.List;
import com.example.service.AppointmentService;
import com.example.service.DepartmentService;
import com.example.service.UserService;
import com.example.repository.*;
import com.example.entity.*;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		long doctorCount = adminService.getDoctorCount();
		long patientCount = adminService.getPatientCount();

		UserDetail user = userRepo.findByEmail(userDetails.getUsername());
		model.addAttribute("doctorCount", doctorCount);
		model.addAttribute("patientCount", patientCount);
		model.addAttribute("username", user.getFullname());

		return "admin/home";
	}

	@GetMapping("/doctors")
	public String viewDoctors(Model model) {
		List<UserDetail> doctors = adminService.getAllDoctors();
		model.addAttribute("doctors", doctors);
		return "admin/doctor-list";
	}

	@GetMapping("/users")
	public String viewUsers(Model model) {
		List<UserDetail> users = adminService.getAllUsers();
		model.addAttribute("users", users);
		return "admin/user-list";
	}

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/manage-users")
	public String manageUsers(Model model) {
		List<UserDetail> users = userRepo.findAll();
		model.addAttribute("users", users);
		return "admin/manage-users";
	}

	@PostMapping("/update-role/{userId}")
	public String updateRole(@PathVariable("userId") int userId, @RequestParam("role") String role) {
		adminService.updateUserRole(userId, role);
		return "redirect:/admin/users";
	}

	@GetMapping("/appointment-list")
	public String showAllAppointmentsForAdmin(Model model) {
		List<Appointment> appointments = appointmentService.getAllApprovedAppointments();
		model.addAttribute("appointments", appointments);
		return "admin/appointment-list";
	}

	@GetMapping("/profile")
	public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		UserDetail user = userRepo.findByEmail(userDetails.getUsername());
		model.addAttribute("user", user);
		return "admin/profile";
	}

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	   @GetMapping("/department-list")
	    public String getDepartmentList(Model model) {
	  
	        List<Department> departments = departmentService.getAllDepartments();
	     
	        model.addAttribute("departments", departments);
	       
	        return "admin/department-list"; 
	    }

	@GetMapping("/create-department")
	public String showCreateDepartmentForm(Model model) {
		model.addAttribute("department", new Department());
		return "admin/create-department";
	}

	@PostMapping("/create-department")
	public String createDepartment(@ModelAttribute Department department) {
		departmentService.createDepartment(department);
		return "redirect:/admin/department-list";
	}

	@GetMapping("/assign-department")
	public String showAssignDepartmentForm(Model model) {
		List<UserDetail> doctors = userService.getAllDoctors();
		List<Department> departments = departmentService.getAllDepartments();
		model.addAttribute("doctors", doctors);
		model.addAttribute("departments", departments);
		return "admin/assign-department";
	}

	@PostMapping("/assign-department")
	public String assignDepartment(@RequestParam("doctorId") int doctorId,
			@RequestParam("departmentId") int departmentId) {
		userService.assignDepartmentToDoctor(doctorId, departmentId);
		return "redirect:/admin/doctors";
	}
}