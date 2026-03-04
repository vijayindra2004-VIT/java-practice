create database comapnydb;
use companydb;

CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    status ENUM('Present','Absent') NOT NULL,
    date DATE NOT NULL
);
SHOW TABLES;
select *from attendance;

create database customer_management;
use customer_management;
CREATE TABLE customers (
cust_id INT PRIMARY KEY AUTO_INCREMENT,
name varchar(20) NOT NULL,
email varchar(20) UNIQUE,
password varchar(20) NOT NULL
);
insert into customers values(1, 'raj', 'abc@gmail.com', '12345');
insert into customers values(2, 'ram', 'xyz@gmail.com', '67890');

CREATE TABLE accounts (
acc_no INT PRIMARY KEY AUTO_INCREMENT,
cust_id INT,
acc_type varchar(20), -- ex, 'Savings', 'Current'
balance DOUBLE default 0.0,
foreign key (cust_id) references customers(cust_id)
);

CREATE TABLE transactions (
 tran_id INT PRIMARY KEY AUTO_INCREMENT,
 acc_no int,
 tran_type varchar(20), -- 'deposit', 'Withdrawl', 'Transfer'
 amount DOUBLE,
 tran_date timestamp default current_timestamp,
 foreign key(acc_no) references accounts(acc_no)
 );