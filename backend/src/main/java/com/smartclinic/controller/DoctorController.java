package com.smartclinic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @GetMapping("/doctors")
    public String getDoctors() {
        return "Doctor Controller Working";
    }
}
