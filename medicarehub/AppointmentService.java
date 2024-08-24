package com.example.service;

import com.example.entity.Appointment;
import com.example.entity.*;

import java.util.List;

public interface AppointmentService {

	Appointment createAppointment(Appointment appointment);

	List<Appointment> getAppointmentsByUser(UserDetail user);

	List<Appointment> getAppointmentsByDoctor(UserDetail doctor);

	List<Appointment> getAppointmentsByDoctorAndStatus(UserDetail doctor, String status);

	void updateAppointmentStatus(int id, String status);

	List<Appointment> getAllApprovedAppointments();
	
	Appointment findById(int appointmentId);
	
	List<UserDetail> findDoctorsByDepartment(Department department);
}