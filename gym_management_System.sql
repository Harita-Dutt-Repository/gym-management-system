-- Create Database
CREATE DATABASE IF NOT EXISTS gym_management_db;
USE gym_management_db;

-- USERS TABLE (LOGIN + ROLE)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','MEMBER') NOT NULL
);

-- ADMIN DETAILS
CREATE TABLE IF NOT EXISTS admin_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    profile_image VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- TABLE TO STORE ADMIN LEAVE / NON-WORKING DAYS
CREATE TABLE IF NOT EXISTS admin_unavailability (
    id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    date DATE NOT NULL,
    reason VARCHAR(255),
    FOREIGN KEY (admin_id) REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(admin_id, date) -- Prevent duplicate leave on same date
);

-- FEE PACKAGES
CREATE TABLE IF NOT EXISTS fee_packages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    duration_months INT NOT NULL
);

-- MEMBER DETAILS
CREATE TABLE IF NOT EXISTS members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    package_id INT,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    join_date DATE NOT NULL,
    status ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES fee_packages(id)
);

-- PAYMENTS / RECEIPTS
CREATE TABLE IF NOT EXISTS payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    package_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATE NOT NULL,
    status ENUM('PAID', 'DUE') DEFAULT 'PAID',
    next_due_date DATE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (package_id) REFERENCES fee_packages(id)
);

-- NOTIFICATIONS
CREATE TABLE IF NOT EXISTS notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
);

-- SUPPLEMENTS STORE INVENTORY
CREATE TABLE IF NOT EXISTS supplements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0
);

-- SUPPLEMENT ORDERS
CREATE TABLE IF NOT EXISTS supplement_orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    supplement_id INT NOT NULL,
    admin_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10,2),
    order_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (supplement_id) REFERENCES supplements(id),
    FOREIGN KEY (admin_id) REFERENCES users(id)
);

-- DIET PLANS
CREATE TABLE IF NOT EXISTS diet_plans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    admin_id INT NOT NULL,
    diet_description TEXT NOT NULL,
    assigned_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (admin_id) REFERENCES users(id)
);
