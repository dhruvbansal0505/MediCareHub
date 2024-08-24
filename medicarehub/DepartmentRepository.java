package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findAll();

	Department findByName(String name);

}
