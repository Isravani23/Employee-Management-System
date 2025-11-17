-- Create Database
CREATE DATABASE IF NOT EXISTS employee_directory;
USE employee_directory;

-- Drop Tables if they exist (for clean start)
DROP TABLE IF EXISTS kycdetails;
DROP TABLE IF EXISTS employee_details;
DROP TABLE IF EXISTS employee_roles;

-- Create Role Table
CREATE TABLE employee_roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    level VARCHAR(50)
);

-- Create Employee Table
CREATE TABLE employee_details (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(150) NOT NULL UNIQUE,
    work_location VARCHAR(100),
    employment_type VARCHAR(50),
    joining_date DATE,
    role_id BIGINT,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES employee_roles(id)
);

-- Create KYC Details Table (One-to-One with Employee)
CREATE TABLE kycdetails (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    document_type VARCHAR(100) NOT NULL,
    document_num VARCHAR(100) NOT NULL,
    address TEXT,
    status VARCHAR(50) NOT NULL,
    submission_date DATE,
    employee_id BIGINT UNIQUE,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee_details(id)
);

-- Insert Sample Roles
INSERT INTO employee_roles (role, department, level)
VALUES
('Software Engineer', 'Engineering', 'L1'),
('Senior Developer', 'Engineering', 'L2'),
('HR Manager', 'Human Resources', 'M1'),
('Tech Lead', 'Engineering', 'L3');

-- Insert Sample Employees
INSERT INTO employee_details (
    first_name, last_name, email, work_location,
    employment_type, joining_date, role_id
)
VALUES
('John', 'Doe', 'john.doe@example.com', 'Hyderabad', 'Full-Time', '2023-07-01', 2),
('Jane', 'Smith', 'jane.smith@example.com', 'Bangalore', 'Contract', '2024-01-10', 1);

-- Insert Sample KYC Record
INSERT INTO kycdetails (
    document_type, document_num, address,
    status, submission_date, employee_id
)
VALUES
('Aadhar', '1234-5678-9012', 'Hyderabad, India', 'Pending', '2024-02-01', 1);
