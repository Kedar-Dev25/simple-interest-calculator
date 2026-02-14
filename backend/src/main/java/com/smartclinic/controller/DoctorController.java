package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.service.DoctorService;
import com.smartclinic.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    // 1️⃣ Get all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // 2️⃣ Get doctor availability by ID and date with token validation
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam("userRole") String userRole,
            @RequestParam("token") String token,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        // Validate token
        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        // Only allow doctor/admin/patient roles
        if (!userRole.equalsIgnoreCase("doctor") &&
            !userRole.equalsIgnoreCase("admin") &&
            !userRole.equalsIgnoreCase("patient")) {
            return ResponseEntity.status(403).body("Access denied for this role");
        }

        List<String> availableTimes = doctorService.getAvailableTimes(doctorId, date);
        return ResponseEntity.ok(availableTimes);
    }

    // 3️⃣ Optional: Get doctors by specialty
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialty(@RequestParam String specialty) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }
}
