create database BusBookingSystem;
use BusBookingSystem;

create table BusAdmin (
Name varchar(30),
Email varchar(30) unique not null,
Password varchar(30) not null);
DELETE from busadmin WHERE AdminID = 1;

alter table BusAdmin
add column AdminID int primary key auto_increment first;

select * from BusAdmin;

INSERT INTO BusAdmin (Name,Email,Password) values('tejas','tejas@gmail.com','tejas@123');

create table BusCostomers(
Name varchar(30),
Email varchar(30) unique,
PhoneNo varchar(18),
Password varchar(20) not null);

ALTER table BusCostomers 
ADD column CustID int primary key auto_increment first;

select * from BusCostomers;

create table BusDetails(
	BusID int primary key auto_increment,
	BusNo varchar(20) unique,
	BusName varchar(20) not null
);
ALTER TABLE busdetails
add column Source varchar(30);
ALTER TABLE busdetails
add column Destination varchar(30);
update busdetails set Source = 'bangalore', Destination = 'ladakh' WHERE BusID = 1;

SELECT * FROM Busdetails;

create table Bookings(
	CustID int,
    Name varchar(30),
    SourcePlace varchar(30),
    Destination varchar(30),
    Fare double,
    PayStatus varchar(15), -- ex 'Paid', 'NotPaid'
    foreign key (CustID) references BusCostomers(CustID)
	);

ALTER TABLE Bookings
ADD column BusID int,
add foreign key (BusID)
references BusDetails(BusID);

ALTER TABLE bookings
add column BookID int primary key auto_increment first;
ALTER TABLE Bookings
DROP COLUMN Name;

select * from Bookings;

ALTER TABLE Bookings
drop column name;