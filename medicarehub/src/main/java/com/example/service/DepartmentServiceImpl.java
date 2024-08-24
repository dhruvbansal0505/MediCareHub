package com.example.service;

import java.util.List;
import com.example.entity.Department;
import com.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentById(int id) {
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

}
