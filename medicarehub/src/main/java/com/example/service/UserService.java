package com.example.service;

import java.util.List;
import com.example.entity.Department;
import com.example.repository.DepartmentRepository;

import com.example.entity.UserDetail;


public interface UserService {

    UserDetail createUser(UserDetail user);

    boolean checkEmail(String email);
    
    UserDetail findByEmail(String email); 
    
    List<UserDetail> getAllDoctors();
    
    UserDetail findById(int id); 

    List<UserDetail> findAllDoctors();

    boolean isAdmin(UserDetail user);

    void assignDepartmentToDoctor(int doctorId, int departmentId);

    public List<UserDetail> findDoctorsByDepartment(Department department);

    

}
 