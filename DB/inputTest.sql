use clinic
exec createRole
@jobPosition = 'Admin'
go

use clinic
exec createRole
@jobPosition = 'Doctor'
go

use clinic
exec createRole
@jobPosition = 'Nurse'
go

use clinic
exec createRole
@jobPosition = 'Cashier'
go

use clinic
exec createRole
@jobPosition = 'Patient'
go

use clinic
exec createPerson
@IDCardNumber = 8273904719393825,
@roleID = 1,
@firstName = 'Admin',
@lastName = '1',
@dateofBirth = '1983/04/13 00:00:00',
@gender = 'M',
@cityAddress = 'admin1CityAddress',
@streetAddress = 'admin1StreetAddress',
@zipCode = 892750,
@email = 'admin1@gmail.com',
@phoneNumber = 082647328592,
@contact1Name = 'MR. admin1',
@contact1phoneNumber = 086381727489,
@contact2Name = 'MRs. admin1',
@contact2phoneNumber = 086274894058
go

use clinic
exec createPerson
@IDCardNumber = 7194829044819284,
@roleID = 2,
@firstName = 'Doctor',
@lastName = '1',
@dateofBirth = '1968/01/20 00:00:00',
@gender = 'M',
@cityAddress = 'doctor1CityAddress',
@streetAddress = 'doctor1StreetAddress',
@zipCode = 892750,
@email = 'doctor1@gmail.com',
@phoneNumber = 082647372846,
@contact1Name = 'MR. doctor1',
@contact1phoneNumber = 086381784719,
@contact2Name = 'MRs. doctor1',
@contact2phoneNumber = 086274809274
go

use clinic
exec createPerson
@IDCardNumber = 8471948227304817,
@roleID = 3,
@firstName = 'Nurse',
@lastName = '1',
@dateofBirth = '1993/09/03 00:00:00',
@gender = 'M',
@cityAddress = 'nurse1CityAddress',
@streetAddress = 'nurse1StreetAddress',
@zipCode = 892750,
@email = 'nurse1@gmail.com',
@phoneNumber = 082647374810,
@contact1Name = 'MR. nurse1',
@contact1phoneNumber = 086381747285,
@contact2Name = 'MRs. nurse1',
@contact2phoneNumber = 086274872622
go

use clinic
exec createPerson
@IDCardNumber = 471948294801742,
@roleID = 4,
@firstName = 'Cashier',
@lastName = '1',
@dateofBirth = '1984/04/11 00:00:00',
@gender = 'M',
@cityAddress = 'cashier1CityAddress',
@streetAddress = 'cashier1StreetAddress',
@zipCode = 892750,
@email = 'cashier1@gmail.com',
@phoneNumber = 082647384718,
@contact1Name = 'MR. cashier1',
@contact1phoneNumber = 086381755718,
@contact2Name = 'MRs. cashier1',
@contact2phoneNumber = 086274811847
go

use clinic
exec createPerson
@IDCardNumber = 8174928274910284,
@roleID = 5,
@firstName = 'Patient',
@lastName = '1',
@dateofBirth = '2000/09/21 00:00:00',
@gender = 'M',
@cityAddress = 'patient1CityAddress',
@streetAddress = 'patient1StreetAddress',
@zipCode = 892750,
@email = 'patient1@gmail.com',
@phoneNumber = 082647388274,
@contact1Name = 'MR. patient1',
@contact1phoneNumber = 086381781947,
@contact2Name = 'MRs. patient1',
@contact2phoneNumber = 086274890817
go

use clinic
exec createSchedule
@personID = 2,
@daySchedule = 'Monday',
@startHour = '06:00:00',
@endHour = '12:00:00'
go

use clinic
exec createLoginCredential
@userID = 1,
@userPassword = 'admin1_'
go

use clinic
exec createLoginCredential
@userID = 2,
@userPassword = 'doctor1_'
go

use clinic
exec createLoginCredential
@userID = 3,
@userPassword = 'nurse1_'
go

use clinic
exec createLoginCredential
@userID = 4,
@userPassword = 'cashier1_'
go

use clinic
exec createAppointment
@patientID = 5,
@employeeID = 3,
@appointmentDate = '2023/05/04 14:30:00 '
go

use Clinic
exec createHealthRecord
@patientID = 5,
@employeeID = 3,
@systolic = 121,
@diastolic = 79,
@heartRate = 78,
@bodyTemperature = 38,
@bodyheight = 181,
@bodyWeight = 134
go

use clinic
exec createDiagnosis
@patientID = 5,
@employeeID = 2,
@diagnosisResult = 'thou shalt not eat sugar',
@actionStatus = 'prescript'
go

use clinic
exec createPrescription
@patientID = 5,
@employeeID = 2,
@medicineName = 'med',
@medicineQTY = 12,
@medicationDosage = '2 tablet per day for 6 day'
go

use clinic
exec createReferral
@diagnosisID = 1,
@hospitalName = 'd hospital',
@medicalSpecialties = 'bones',
@referralTime = '2023/05/04 18:00:00'
go

use clinic
exec createPayment
@appointmentID = 1,
@paymentMethod = 'Insurance',
@chargedAmmount = 2000,
@paymentStatus = 'Paid'
go

select * from person.roles
select * from person.person
select * from person.schedule
select * from clinic.loginCredential
select * from clinic.appointment
select * from clinic.healthRecord
select * from clinic.diagnosis
select * from clinic.prescription
select * from clinic.referral
select * from clinic.payment
