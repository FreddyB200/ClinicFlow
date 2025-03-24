package com.medical.appointments.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.appointments.doctor.service.DoctorService;

/**
 * Controller for web views
 */
@Controller
@RequestMapping("/web")
public class WebController {

    private final DoctorService doctorService;

    @Autowired
    public WebController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Home page
     * 
     * @param model the model
     * @return the home page
     */
    @GetMapping
    public String home(Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("headContent", "home :: headContent");
        model.addAttribute("mainContent", "home :: mainContent");
        model.addAttribute("scripts", "home :: scripts");
        return "layout/main-layout";
    }

    /**
     * Doctors page
     * 
     * @param model the model
     * @return the doctors page
     */
    @GetMapping("/doctors")
    public String doctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("title", "Doctors");
        model.addAttribute("headContent", "doctors/list :: headContent");
        model.addAttribute("mainContent", "doctors/list :: mainContent");
        model.addAttribute("scripts", "doctors/list :: scripts");
        return "layout/main-layout";
    }

    /**
     * Doctor details page
     * 
     * @param id the doctor id
     * @param model the model
     * @return the doctor details page
     */
    @GetMapping("/doctors/{id}")
    public String doctorDetails(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        model.addAttribute("title", "Doctor Details");
        model.addAttribute("headContent", "doctors/details :: headContent");
        model.addAttribute("mainContent", "doctors/details :: mainContent");
        model.addAttribute("scripts", "doctors/details :: scripts");
        return "layout/main-layout";
    }
} 