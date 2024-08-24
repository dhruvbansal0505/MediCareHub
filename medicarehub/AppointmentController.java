
package com.example.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Appointment;
import com.example.entity.Department;
import com.example.entity.UserDetail;
import com.example.service.AppointmentService;
import com.example.service.DepartmentService;
import com.example.service.UserService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/request-form")
    public String showRequestAppointmentForm(Model model, HttpSession session) {
        if (session.getAttribute("msg") != null) {
            model.addAttribute("message", session.getAttribute("msg"));
            session.removeAttribute("msg");
        }
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", userService.findAllDoctors());
        model.addAttribute("departments", departmentService.getAllDepartments()); 
        return "user/request-form";
    }

    @PostMapping("/request")
    public String createAppointmentRequest(@RequestParam("doctorId") int doctorId,   @RequestParam("departmentId") int departmentId,
                                            @RequestParam("appointmentDate") LocalDateTime appointmentDate,
                                            Principal principal,
                                            HttpSession session) {
        UserDetail user = userService.findByEmail(principal.getName());
        UserDetail doctor = userService.findById(doctorId);
        Department department = departmentService.getDepartmentById(departmentId);

        if (doctor == null || department == null) {
            session.setAttribute("msg", "Doctor/Department not found");
            return "redirect:/appointments/request-form";
        }

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setDepartment(department);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus("PENDING");

        appointmentService.createAppointment(appointment);

        session.setAttribute("msg", "Appointment requested successfully");
        return "redirect:/appointments/user-list";
    }

    @GetMapping("/user-list")
    public String showUserAppointments(Model model, Principal principal) {
        UserDetail user = userService.findByEmail(principal.getName());
        List<Appointment> appointments = appointmentService.getAppointmentsByUser(user);
        
        model.addAttribute("appointments", appointments);
        return "user/appointment-list";
    }

    @GetMapping("/doctor-list")
    public String showDoctorAppointments(Model model, Principal principal) {
        UserDetail doctor = userService.findByEmail(principal.getName());
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor);
        
        model.addAttribute("appointments", appointments);
        return "doctor/appointment-list";
    }

    @PostMapping("/update-appointment-status/{appointmentId}")
    public String processAppointmentStatusUpdate(@PathVariable("appointmentId") int appointmentId,
                                                  @RequestParam("status") String status,
                                                  Principal principal) {
        appointmentService.updateAppointmentStatus(appointmentId, status);
        UserDetail user = userService.findByEmail(principal.getName());

        if (userService.isAdmin(user)) {
            return "redirect:/appointments/admin-list";
        } else if (user.getRole().equals("ROLE_DOCTOR")) {
            return "redirect:/appointments/doctor-list";
        } else {
            return "redirect:/appointments/user-list";
        }
    }

    @GetMapping("/admin-list")
    public String showAllAppointmentsForAdmin(Model model) {
        List<Appointment> appointments = appointmentService.getAllApprovedAppointments();
        model.addAttribute("appointments", appointments);
        return "admin/appointment-list";
    }
    
   
    }

