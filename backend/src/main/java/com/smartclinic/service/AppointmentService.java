package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import com.smartclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Save a new appointment
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Retrieve appointments for a specific doctor on a specific date
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        // Assuming Appointment entity has 'doctor' and 'appointmentTime' fields
        return appointmentRepository.findByDoctorDoctorIdAndAppointmentTimeBetween(
                doctorId,
                date.atStartOfDay(),
                date.atTime(23, 59, 59)
        );
    }

    // Optional: Retrieve all appointments (for admin or reporting)
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
