package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.entity.UserDetail;
import com.example.entity.Department;

public interface UserRepository extends JpaRepository<UserDetail, Integer> {

	public boolean existsByEmail(String email);

	public UserDetail findByEmail(String email);

	long countByRole(String role);
	
	List<UserDetail> findByRole(String role);

	@Modifying
	@Transactional
	@Query("UPDATE UserDetail u SET u.role = :role WHERE u.id = :id")
	void updateUserRole(int id, String role);

	List<UserDetail> findByDepartment(Department department);

	public List<UserDetail> findByDepartmentAndRole(Department department, String role);
	
}