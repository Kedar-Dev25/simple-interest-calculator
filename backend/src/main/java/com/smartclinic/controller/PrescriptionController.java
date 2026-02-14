package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    // Dummy POST API to save prescription
    @PostMapping
    public ResponseEntity<String> savePrescription(@RequestBody Prescription prescription) {
        // Normally yahan service call hoti (DB save)
        return ResponseEntity.ok("Prescription saved successfully");
    }
}
