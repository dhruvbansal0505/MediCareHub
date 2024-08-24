package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user; 

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserDetail doctor; 
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	private LocalDateTime appointmentDate;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDetail getUser() {
		return user;
	}
	public void setUser(UserDetail user) {
		this.user = user;
	}
	public UserDetail getDoctor() {
		return doctor;
	}
	public void setDoctor(UserDetail doctor) {
		this.doctor = doctor;
	}
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status; // e.g., "PENDING", "APPROVED", "REJECTED"
}



