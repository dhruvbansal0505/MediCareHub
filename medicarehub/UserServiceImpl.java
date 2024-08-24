package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Department;
import com.example.entity.UserDetail;
import com.example.repository.UserRepository;
import com.example.repository.DepartmentRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public List<UserDetail> findDoctorsByDepartment(Department department) {
		
		return userRepo.findByDepartmentAndRole(department, "ROLE_DOCTOR");
	}

	@Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetail createUser(UserDetail user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepo.save(user);
    }

    
    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

	@Override
	public UserDetail findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<UserDetail> getAllDoctors() {
	
		return userRepo.findByRole("ROLE_DOCTOR");
	}

	@Override
	public UserDetail findById(int id) {
		
		return userRepo.findById(id).orElse(null);
	}

	
	@Override
	public List<UserDetail> findAllDoctors() {
		return userRepo.findByRole("ROLE_DOCTOR");
	}

	@Override
	public boolean isAdmin(UserDetail user) {
		return "ROLE_ADMIN".equals(user.getRole());
	}

	  @Autowired
	    private DepartmentService departmentService;
	
	@Override
	public void assignDepartmentToDoctor(int doctorId, int departmentId) {
		 UserDetail doctor = userRepo.findById(doctorId).orElse(null);
	        Department department = departmentService.getDepartmentById(departmentId);
	        if (doctor != null && department != null) {
	            doctor.setDepartment(department);
	            userRepo.save(doctor);
	        }
	    }
		
}