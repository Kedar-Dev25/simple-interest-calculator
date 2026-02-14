package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Basic CRUD operations
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    // Retrieve available time slots for a doctor on a specific date
    public List<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (!doctorOpt.isPresent()) {
            throw new RuntimeException("Doctor not found");
        }

        Doctor doctor = doctorOpt.get();

        // Assuming doctor has a list of LocalTime representing booked appointments
        List<LocalTime> bookedTimes = doctor.getAppointments().stream()
                .filter(a -> a.getAppointmentTime().toLocalDate().equals(date))
                .map(a -> a.getAppointmentTime().toLocalTime())
                .collect(Collectors.toList());

        // Return all available slots (example: 9AM to 5PM, every 30 mins)
        List<LocalTime> allSlots = LocalTime.of(9, 0).toLocalTime().until(LocalTime.of(17, 0), java.time.Duration.ofMinutes(30))
                .stream().map(min -> LocalTime.of(9, 0).plusMinutes(min))
                .collect(Collectors.toList());

        // Filter out booked times
        return allSlots.stream()
                .filter(slot -> !bookedTimes.contains(slot))
