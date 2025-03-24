package com.medical.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Medical Appointments Management System.
 * This class serves as the entry point for the Spring Boot application.
 * 
 * The application provides functionality for managing medical appointments,
 * including scheduling, cancellation, and tracking of patient appointments.
 * 
 * @author Freddy Johan Bautista Baquero
 * @version 1.0.0
 */
@SpringBootApplication
public class MedicalAppointmentsApplication {

	/**
	 * Main method that starts the Spring Boot application.
	 * 
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(MedicalAppointmentsApplication.class, args);
	}

}
