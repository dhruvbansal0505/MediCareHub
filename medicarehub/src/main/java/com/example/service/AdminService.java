package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.*;
import com.example.repository.*;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DepartmentRepository departmentRepository;

	public long getDoctorCount() {
		return userRepo.countByRole("ROLE_DOCTOR");
	}

	public long getPatientCount() {
		return userRepo.countByRole("ROLE_USER");
	}

	public List<UserDetail> getAllDoctors() {
		return userRepo.findByRole("ROLE_DOCTOR");
	}

	public List<UserDetail> getAllUsers() {
		return userRepo.findByRole("ROLE_USER");
	}

	public void updateUserRole(int id, String role) {
		userRepo.updateUserRole(id, role);
	}

	@Autowired
	private DepartmentRepository departmentRepo;

	public List<Department> getAllDepartments() {

		return departmentRepo.findAll();
	}

	public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

	
	public Department findDepartmentById(int id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
	public List<UserDetail> getDoctorsByDepartment(Department department) {
		return userRepo.findByDepartment(department);
	}

}
