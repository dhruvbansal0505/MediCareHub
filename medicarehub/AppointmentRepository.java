package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Appointment;
import com.example.entity.Department;
import com.example.entity.UserDetail;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByUser(UserDetail user);
    
    List<Appointment> findByDoctor(UserDetail doctor);
    
    List<Appointment> findByDoctorAndStatus(UserDetail doctor, String status);
    
    List<Appointment> findByStatus(String status);
    
    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status = :status WHERE a.id = :id")
    void updateAppointmentStatus(int id, String status);

    @Query("SELECT a FROM Appointment a WHERE a.doctor IS NOT NULL AND a.status = 'APPROVED'")
    List<Appointment> findAllApprovedAppointments();

	List<UserDetail> findDoctorsByDepartment(Department department);
}
