package com.example.service;

import com.example.entity.Department;
import java.util.List;

public interface DepartmentService {

	Department createDepartment(Department department);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
	
}
