--CREATE DATABASE
use master
create database clinic
on
(
name = clinic_data,
filename = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\clinic.mdf',
size = 10MB,
maxsize = 100MB,
filegrowth = 5MB
)
log on
(
name = clinic_log,
filename = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\clinic.ldf',
size = 5MB,
maxsize = 100MB,
filegrowth = 5MB
)
go





--CREATE SCHEMA
use clinic
exec ('create schema person')
exec ('create schema clinic')
go





--CREATE TABLE
use clinic
create table person.roles --ROLES
(
roleID int constraint PKjobID primary key identity(1,1) not null,
jobPosition varchar(8) unique not null,
dateModified dateTime default getDate() not null
)
go

create table person.person --PERSON
(
personID int constraint PKpersonID primary key identity(1,1) not null,
roleID int constraint personFKroleID foreign key(roleID) references person.roles(roleID),
IDCardNumber bigint unique not null,
firstName varchar(20) not null,
lastName varchar(20) default '' not null,
dateofBirth date not null,
gender varchar(1) not null,
cityAddress varchar(20) not null,
streetAddress varchar(20) not null,
zipCode int not null,
email varchar(20) unique not null,
phoneNumber bigint unique not null,
contact1Name varchar(20) not null,
contact1phoneNumber bigInt not null,
contact2Name varchar(20),
contact2phoneNumber bigInt,
dateModified dateTime default getDate() not null
)
go

create table clinic.loginCredential -- LOGIN CREDENTIAL
(
userID int constraint loginCredentialFKpersonID foreign key(userID) references person.person(personID) unique not null,
userPassword varchar(20) unique not null,
accountStatus varchar(20) default 'active'
)
go

create table person.schedule --SCHEDULE
(
scheduleID int constraint PKscheduleID primary Key identity(1,1) not null,
personID int constraint scheduleFKpersonID foreign key(personID) references person.person(personID) not null,
daySchedule varchar(10) not null,
startHour time not null,
endHour time not null,
dateModified dateTime default getDate() not null
)
go

create table clinic.appointment --APPOINTMENT
(
appointmentID int constraint PKappointmentID primary key identity(1,1) not null,
patientID int constraint appointmentFKpatientID foreign key(patientID) references person.person(personID) not null,
employeeID int constraint appointmentFKemployeeID foreign key(employeeID) references person.person(personID) not null,
appointmentDate dateTime unique not null,
appointmentStatus varchar(10) default 'In Queue' not null,
dateModified dateTime default getDate() not null
)
go

create table clinic.healthRecord --HEALTH RECORD
(
MRID int constraint PKMRID primary key identity(1,1) not null,
patientID int constraint MRFKpatientID foreign key(patientID) references person.person(personID) not null,
employeeID int constraint MRFKemployeeID foreign key(employeeID) references person.person(personID) not null,
systolic int not null,
diastolic int not null,
heartRate int not null,
bodyTemperature int not null,
bodyheight int not null,
bodyWeight int not null,
dateModified dateTime default getDate() not null
)
go

create table clinic.diagnosis --DIAGNOSIS
(
diagnosisID int constraint PKdiagnosisID primary key identity(1,1) not null,
patientID int constraint diagnosisFKpatientID foreign key(patientID) references person.person(personID) not null,
employeeID int constraint diagnosisFKemployeeID foreign key(employeeID) references person.person(personID) not null,
diagnosisResult varchar (100),
actionStatus varchar(20),
dateModified dateTime default getDate() not null
)
go

create table clinic.prescription --PRESCRIPTION
(
prescriptionID int constraint PKprescriptionID primary key identity(1,1) not null,
patientID int constraint prescriptionFKpatientID foreign key(patientID) references person.person(personID) not null,
employeeID int constraint prescriptionFKemployeeID foreign key(employeeID) references person.person(personID) not null,
medicineName varchar(20) not null,
medicineQTY int not null,
medicationDosage varchar(40) not null,
dateModified dateTime default getDate() not null
)
go

create table clinic.referral --REFERRAL
(
referralID int constraint PKreferralID primary key identity(1,1) not null,
diagnosisID int constraint referralFKdiagnosisID foreign key(diagnosisID) references clinic.diagnosis(diagnosisID) not null,
hospitalName varchar(20) not null,
medicalSpecialties varchar(20) not null,
referralTime dateTime unique not null,
dateModified dateTime default getDate() not null
)
go

create table clinic.payment --PAYMENT
(
paymentID int constraint PKpaymentID primary key identity(1,1) not null,
appointmentID int constraint paymentFKappointmentID foreign key(appointmentID) references clinic.appointment(appointmentID) not null,
paymentMethod varchar(10) not null,
chargedAmmount int not null,
paymentStatus varchar(10) not null,
dateModified dateTime default getDate() not null
)
go





--CREATE PROCEDURE
create procedure createRole --CREATE ROLE
@jobPosition varchar(8)
as
insert into person.roles (jobPosition)
values(@jobPosition)
go

create procedure createPerson --CREATE PERSON
@IDCardNumber bigint,
@roleID int,
@firstName varchar(20),
@lastName varchar(20),
@dateofBirth date,
@gender varchar(1),
@cityAddress varchar(20),
@streetAddress varchar(20),
@zipCode int,
@email varchar(20),
@phoneNumber bigint,
@contact1Name varchar(20),
@contact1phoneNumber bigint,
@contact2Name varchar(20),
@contact2phoneNumber bigint
as
insert into person.person(IDCardNumber,roleID,firstName,lastName,dateofBirth,gender,cityAddress,streetAddress,zipCode,email,phoneNumber,contact1Name,contact1phoneNumber,contact2Name,contact2phoneNumber)
values (@IDCardNumber,@roleID,@firstName,@lastName,@dateofBirth,@gender,@cityAddress,@streetAddress,@zipCode,@email,@phoneNumber,@contact1Name,@contact1phoneNumber,@contact2Name,@contact2phoneNumber)
go

create procedure createSchedule --CREATE SCHEDULE
@personID int,
@daySchedule varchar(10),
@startHour time,
@endHour time
as
insert into person.schedule(personID,daySchedule,startHour,endHour)
values (@personID,@daySchedule,@startHour,@endHour)
go

create procedure createLoginCredential --CREATE LOGIN CREDENTIAL
@userID bigint,
@userPassword varchar(20)
as
insert into clinic.loginCredential(userID,userPassword)
values (@userID,@userPassword)
go

create procedure readLoginCredential --READ LOGIN CREDENTIAL
@userID bigint,
@userPassword varchar(20)
as
select * from clinic.loginCredential
where userID = @userID and userPassword = @userPassword
go

create procedure readPerson --READ PERSON
@userID bigint
as
select * from person.person 
where 
person.person.personID = @userID OR 
person.person.IDCardNumber = @userID OR
person.person.phoneNumber = @userID
go

create procedure createAppointment --CREATE APPOINTMENT
@patientID int,
@employeeID int,
@appointmentDate dateTime
as
insert into clinic.appointment (patientID,employeeID,appointmentDate)
values (@patientID,@employeeID,@appointmentDate)
go

create procedure createHealthRecord --CREATE HEALTH RECORD
@patientID int,
@employeeID int,
@systolic int,
@diastolic int,
@heartRate int,
@bodyTemperature int,
@bodyheight int,
@bodyWeight int
as
insert into clinic.healthRecord (patientID,employeeID,systolic,diastolic,heartRate,bodyTemperature,bodyheight,bodyWeight)
values (@patientID,@employeeID,@systolic,@diastolic,@heartRate,@bodyTemperature,@bodyheight,@bodyWeight)
go

create procedure createDiagnosis -- CREATE DIAGNOSIS
@patientID int,
@employeeID int,
@diagnosisResult varchar (100),
@actionStatus varchar(20)
as
insert into clinic.diagnosis (patientID,employeeID,diagnosisResult,actionStatus)
values (@patientID,@employeeID,@diagnosisResult,@actionStatus)
go

create procedure createPrescription --CREATE PRESCRIPTION
@patientID int,
@employeeID int,
@medicineName varchar(20),
@medicineQTY int,
@medicationDosage varchar(40)
as
insert into clinic.prescription(patientID,employeeID,medicineName,medicineQTY,medicationDosage)
values (@patientID,@employeeID,@medicineName,@medicineQTY,@medicationDosage)
go

create procedure createReferral -- CREATE REFERRAL
@diagnosisID int,
@hospitalName varchar(20),
@medicalSpecialties varchar(20),
@referralTime dateTime
as
insert into clinic.referral (diagnosisID,hospitalName,medicalSpecialties,referralTime)
values (@diagnosisID,@hospitalName,@medicalSpecialties,@referralTime)
go

create procedure createPayment --CREATE PAYMENT
@appointmentID int,
@paymentMethod varchar(10),
@chargedAmmount int,
@paymentStatus varchar(10)
as
insert into clinic.payment(appointmentID,paymentMethod,chargedAmmount,paymentStatus)
values (@appointmentID,@paymentMethod,@chargedAmmount,@paymentStatus)
go

create procedure deleteLoginCredential --DELETE LOGIN CREDENTIAL
@userID int
as
delete from clinic.loginCredential
where userID = @userID
go

create procedure updatePerson -- UPDATE PERSON
@userID bigint,
@IDCardNumber bigint,
@roleID int,
@firstName varchar(20),
@lastName varchar(20),
@dateofBirth date,
@gender varchar(1),
@cityAddress varchar(20),
@streetAddress varchar(20),
@zipCode int,
@email varchar(20),
@phoneNumber bigint,
@contact1Name varchar(20),
@contact1phoneNumber bigint,
@contact2Name varchar(20),
@contact2phoneNumber bigint
as
update person.person
set
IDCardNumber = @IDCardNumber,
roleID = @roleID,
firstName = @firstName,
lastName = @lastName,
dateofBirth = @dateofBirth,
gender = @gender,
cityAddress = @cityAddress,
streetAddress = @streetAddress,
zipCode = @zipCode,
email = @email,
phoneNumber = @phoneNumber,
contact1Name = @contact1Name,
contact1phoneNumber = @contact1phoneNumber,
contact2Name = @contact2Name,
contact2phoneNumber = @contact2phoneNumber
where @userID in (personID,IDCardNumber,phoneNumber)
go

create procedure updateSchedule--UPDATE SCHEDULE
@scheduleID int,
@personID int,
@daySchedule varchar(10),
@startHour time,
@endHour time
as
update person.schedule
set
personID = @personID,
daySchedule = @daySchedule,
startHour = @daySchedule,
endHour = @endHour
where scheduleID = @scheduleID
go

create procedure updateDiagnosis--UPDATE DIAGNOSIS
@diagnosisID int,
@patientID int,
@employeeID int,
@diagnosisResult varchar (100),
@actionStatus varchar(20)
as
update clinic.diagnosis
set
patientID = @patientID,
employeeID = @employeeID,
diagnosisResult = @diagnosisResult,
actionStatus = @actionStatus
where diagnosisID = @diagnosisID
go

create view medicalRecord --MEDICAL RECORD VIEW
as
select 
healthRecord.MRID,healthRecord.patientID,'nurseID'=healthRecord.employeeID,healthRecord.systolic,healthRecord.diastolic,
healthRecord.heartRate,healthRecord.bodyTemperature,healthRecord.bodyheight,healthRecord.bodyWeight,
diagnosis.diagnosisID,'doctorID'=diagnosis.employeeID,diagnosis.diagnosisResult,diagnosis.actionStatus
from clinic.healthRecord
inner join clinic.diagnosis on clinic.healthRecord.patientID = clinic.diagnosis.patientID
go

create view userIDPolymorph --POLYMORPH USER ID VIEW
as
select
person.person.personID,person.person.IDCardNumber,person.person.phoneNumber,clinic.loginCredential.userPassword
from person.person
inner join clinic.loginCredential on person.personID = loginCredential.userID
go
