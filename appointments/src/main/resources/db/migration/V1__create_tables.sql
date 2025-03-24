-- Create doctor table
CREATE TABLE doctors (
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

-- Index for doctor search by name
CREATE INDEX idx_doctor_name ON doctors(first_name, last_name);

-- Index for doctor search by specialty
CREATE INDEX idx_doctor_specialty ON doctors(specialty);

-- Index for doctor search by email
CREATE INDEX idx_doctor_email ON doctors(email); 