-- V3__sample_data.sql
-- Sample data and table creation for Medical Appointments System

-- Create roles table
CREATE TABLE IF NOT EXISTS role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Create specialties table
CREATE TABLE IF NOT EXISTS specialty (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- Create user_roles table
CREATE TABLE IF NOT EXISTS user_roles (
    user_id INTEGER NOT NULL REFERENCES users(id),
    role_id INTEGER NOT NULL REFERENCES role(id),
    PRIMARY KEY (user_id, role_id)
);

-- Create patient table
CREATE TABLE IF NOT EXISTS patient (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    active BOOLEAN DEFAULT TRUE
);

-- Create doctors table
CREATE TABLE IF NOT EXISTS doctors (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    specialty VARCHAR(50) NOT NULL,
    license_number VARCHAR(50) UNIQUE NOT NULL,
    date_of_birth DATE,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create appointment table
CREATE TABLE IF NOT EXISTS appointment (
    id SERIAL PRIMARY KEY,
    doctor_id INTEGER NOT NULL REFERENCES doctors(id),
    patient_id INTEGER NOT NULL REFERENCES patient(id),
    appointment_date_time TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    notes TEXT
);

-- Indexes for doctors
CREATE INDEX IF NOT EXISTS idx_doctor_name ON doctors(first_name, last_name);
CREATE INDEX IF NOT EXISTS idx_doctor_specialty ON doctors(specialty);
CREATE INDEX IF NOT EXISTS idx_doctor_email ON doctors(email);


-- Insert roles
INSERT INTO role (id, name) VALUES (1, 'ADMIN'), (2, 'DOCTOR'), (3, 'PATIENT') ON CONFLICT DO NOTHING;

-- Insert specialties
INSERT INTO specialty (id, name) VALUES (1, 'General Medicine'), (2, 'Pediatrics'), (3, 'Cardiology'), (4, 'Dermatology') ON CONFLICT DO NOTHING;

-- Insert users
INSERT INTO users (id, username, email, password, enabled) VALUES
  (1, 'admin', 'admin@demo.com', '$2a$10$7Qf8hZy4NnQy1fP1J8ZK8u1Z8Q4Z8Y7K8F8U8P8J8G8M8N8O8P8Q8', true), -- password: admin
  (2, 'drsmith', 'drsmith@demo.com', '$2a$10$gH8k8J8l8M8N8O8P8Q8R8S8T8U8V8W8X8Y8Z8a8b8c8d8e8f8g8h8', true), -- password: doctor
  (3, 'johndoe', 'john@demo.com', '$2a$10$w9Z8Q7K8F8U8P8J8G8M8N8O8P8Q8R8S8T8U8V8W8X8Y8Z8a8b8c8d8', true) -- password: patient
ON CONFLICT DO NOTHING;

-- Insert user roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1), (2, 2), (3, 3) ON CONFLICT DO NOTHING;

-- Insert patients
INSERT INTO patient (id, first_name, last_name, email, phone, active) VALUES
  (1, 'John', 'Doe', 'john@demo.com', '555-1234', true),
  (2, 'Jane', 'Smith', 'jane@demo.com', '555-5678', true)
ON CONFLICT DO NOTHING;

-- Insert appointments
INSERT INTO appointment (id, doctor_id, patient_id, appointment_date_time, status, notes) VALUES
  (1, 1, 1, '2025-04-20 10:00:00', 'SCHEDULED', 'First appointment'),
  (2, 2, 2, '2025-04-21 11:00:00', 'SCHEDULED', 'Follow-up')
ON CONFLICT DO NOTHING;
