create database employees;
use employees;
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(100),
    designation VARCHAR(100),
    salary DOUBLE,
    email VARCHAR(100),
    phone VARCHAR(15),
    joining_date DATE,
    location VARCHAR(100)
);
INSERT INTO employees 
(name, department, designation, salary, email, phone, joining_date, location)
VALUES
('Vijay', 'IT', 'Software Engineer', 60000, 'vijay@gmail.com', '9876543210', '2023-06-12', 'Bangalore'),
('Indra', 'HR', 'HR Manager', 55000, 'indra@gmail.com', '9123456780', '2022-02-10', 'Hyderabad'),
('Tejas', 'Finance', 'Accountant', 50000, 'tejas@gmail.com', '9988776655', '2021-11-20', 'Mumbai'),
('Vamshik', 'Marketing', 'Manager', 52000, 'vamshik@gmail.com', '9112233445', '2022-08-15', 'Chennai'),
('Rohith', 'Producation', 'assistant', 40000, 'rahul@gamil.com', 9080706050, '2021-12-01', 'Lucknow');

SELECT * FROM employees WHERE name=?;

select * from employees;



