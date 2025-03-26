-- Insert sample doctors
INSERT INTO doctors (first_name, last_name, email, phone, specialty, license_number, date_of_birth, active) 
VALUES 
    ('John', 'Smith', 'john.smith@example.com', '+1-555-123-4567', 'Cardiology', 'MD12345', '1975-05-15', true),
    ('Emily', 'Johnson', 'emily.johnson@example.com', '+1-555-234-5678', 'Neurology', 'MD23456', '1980-08-22', true),
    ('Michael', 'Williams', 'michael.williams@example.com', '+1-555-345-6789', 'Orthopedics', 'MD34567', '1972-11-10', true),
    ('Sarah', 'Brown', 'sarah.brown@example.com', '+1-555-456-7890', 'Pediatrics', 'MD45678', '1985-03-30', true),
    ('David', 'Jones', 'david.jones@example.com', '+1-555-567-8901', 'Dermatology', 'MD56789', '1978-12-05', true);
