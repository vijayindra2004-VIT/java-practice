create database BusBookingSystem;
use BusBookingSystem;

create table BusAdmin (
Name varchar(30),
Email varchar(30) unique not null,
Password varchar(30) not null);

alter table BusAdmin
add column AdminID int primary key auto_increment first;

select * from BusAdmin;

INSERT INTO BusAdmin (Name,Email,Password) values('vijay','vijay@gmail.com','vijay@123');

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
select * from busdetails;

update busdetails set Source='Bangalore',
destination='Mysore' where BusID=1;
SELECT * FROM Busdetails;

create table Bookings(
	CustID int,
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
describe bookings;
describe buscostomers;
