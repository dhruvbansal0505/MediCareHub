
package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.AppointmentRepository;

import com.example.entity.Appointment;
import com.example.entity.Department;
import com.example.entity.UserDetail;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Override
	public List<UserDetail> findDoctorsByDepartment(Department department) {
		
		return  appointmentRepository.findDoctorsByDepartment(department);
	}

	@Override
	public Appointment findById(int appointmentId) {
		return appointmentRepository.findById(appointmentId).orElse(null);
	}

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public Appointment createAppointment(Appointment appointment) {
		appointment.setStatus("PENDING");
		return appointmentRepository.save(appointment);

	}

	@Override
	public List<Appointment> getAppointmentsByUser(UserDetail user) {

		return appointmentRepository.findByUser(user);
	}

	@Override
	public List<Appointment> getAppointmentsByDoctor(UserDetail doctor) {

		return appointmentRepository.findByDoctor(doctor);
	}

	@Override
	public List<Appointment> getAppointmentsByDoctorAndStatus(UserDetail doctor, String status) {

		return appointmentRepository.findByDoctorAndStatus(doctor, status);
	}

	@Override
	public void updateAppointmentStatus(int id, String status) {
		if ("PENDING".equals(status) || "APPROVED".equals(status) || "REJECTED".equals(status)) {
			appointmentRepository.updateAppointmentStatus(id, status);
		} else {
			throw new IllegalArgumentException("Invalid status: " + status);
		}

	}

	@Override
	public List<Appointment> getAllApprovedAppointments() {

		return appointmentRepository.findAllApprovedAppointments();
	}
}