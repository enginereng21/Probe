eclipse workspace - https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2025-09/R/eclipse-inst-jre-win64.exe&mirror_id=1273

mysql workbench - https://dev.mysql.com/downloads/file/?id=546418

xampp - https://www.apachefriends.org/download.html

sql connector: https://dev.mysql.com/downloads/connector/j/

mysql code: 
-- Create database
drop database probe_management_system;
CREATE DATABASE IF NOT EXISTS probe_management_system;
USE probe_management_system;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    position ENUM('Technician', 'Supervisor', 'Manager', 'Admin') NOT NULL,
    superior_id VARCHAR(20) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (superior_id) REFERENCES users(employee_id)
);

-- Insert sample users
INSERT INTO users (employee_id, full_name, password, position) VALUES 
('ADM001', 'System Administrator', 'admin123', 'Admin'),
('MGR001', 'John Manager', 'manager123', 'Manager'),
('SUP001', 'Sarah Supervisor', 'supervisor123', 'Supervisor'),
('TEC001', 'Mike Technician', 'tech123', 'Technician');

-- Probes table
CREATE TABLE IF NOT EXISTS probes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_id VARCHAR(50) UNIQUE NOT NULL,
    probe_type VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    model VARCHAR(100),
    serial_number VARCHAR(100),
    calibration_date DATE,
    next_calibration_date DATE,
    status ENUM('Active', 'Inactive', 'Calibration Due', 'Maintenance Required') DEFAULT 'Active',
    location VARCHAR(100),
    notes TEXT,
    created_by VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(employee_id)
);

-- Insert sample probes
INSERT INTO probes (probe_id, probe_type, manufacturer, model, serial_number, calibration_date, next_calibration_date, status, location) VALUES
('PRB001', 'Temperature Probe', 'Omega Engineering', 'TJ-100', 'SNT001', '2024-01-15', '2024-07-15', 'Active', 'Lab A'),
('PRB002', 'Pressure Probe', 'Dwyer Instruments', 'P-200', 'SNP001', '2024-02-01', '2024-08-01', 'Active', 'Lab B'),
('PRB003', 'pH Probe', 'Hanna Instruments', 'HI-300', 'SNP002', '2024-01-20', '2024-07-20', 'Calibration Due', 'Lab C');

-- Insertion tracking table
CREATE TABLE IF NOT EXISTS probe_insertions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_id VARCHAR(50) NOT NULL,
    inserted_by VARCHAR(20) NOT NULL,
    insertion_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location VARCHAR(100) NOT NULL,
    purpose VARCHAR(200),
    measurements JSON,
    notes TEXT,
    FOREIGN KEY (probe_id) REFERENCES probes(probe_id),
    FOREIGN KEY (inserted_by) REFERENCES users(employee_id)
);

-- Maintenance records table
CREATE TABLE IF NOT EXISTS maintenance_records (
    id INT PRIMARY KEY AUTO_INCREMENT,
    probe_id VARCHAR(50) NOT NULL,
    maintenance_type ENUM('Calibration', 'Cleaning', 'Repair', 'Replacement') NOT NULL,
    performed_by VARCHAR(20) NOT NULL,
    maintenance_date DATE NOT NULL,
    next_maintenance_date DATE,
    description TEXT,
    parts_used TEXT,
    cost DECIMAL(10,2),
    status ENUM('Completed', 'Scheduled', 'In Progress') DEFAULT 'Completed',
    verification_by VARCHAR(20),
    verification_date DATE,
    FOREIGN KEY (probe_id) REFERENCES probes(probe_id),
    FOREIGN KEY (performed_by) REFERENCES users(employee_id),
    FOREIGN KEY (verification_by) REFERENCES users(employee_id)
);
