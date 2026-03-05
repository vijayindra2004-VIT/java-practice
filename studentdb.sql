CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    application_no VARCHAR(20),
    name VARCHAR(50),
    address VARCHAR(100),
    gender VARCHAR(10),
    subjects VARCHAR(100),
    qualification VARCHAR(20)
);