use clinic
exec createRole
@jobPosition = 'Admin'
select * from person.roles

use clinic
exec createRole
@jobPosition = 'Doctor'
select * from person.roles

use clinic
exec createRole
@jobPosition = 'Nurse'
select * from person.roles

use clinic
exec createRole
@jobPosition = 'Cashier'
select * from person.roles

use clinic
exec createRole
@jobPosition = 'Patient'
select * from person.roles

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
select * from person.person

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
select * from person.person

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
select * from person.person

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
select * from person.person

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
select * from person.person

use clinic
exec createLoginCredential
@userID = 1,
@userPassword = 'admin1_'
select * from clinic.loginCredential

use clinic
exec createLoginCredential
@userID = 2,
@userPassword = 'doctor1_'
select * from clinic.loginCredential

use clinic
exec createLoginCredential
@userID = 3,
@userPassword = 'nurse1_'
select * from clinic.loginCredential

use clinic
exec createLoginCredential
@userID = 4,
@userPassword = 'cashier1_'
select * from clinic.loginCredential

use clinic
exec createAppointment
@patientID = 5,
@employeeID = 3,
@appointmentDate = '2023/05/04 14:00:00 '
select * from clinic.appointment

use Clinic
exec createHealthRecord
@patientID = 5,
@employeeID = 3,
@systolic = 1000,
@diastolic = 500,
@heartRate = 3200,
@bodyTemperature = 180,
@bodyheight = 10,
@bodyWeight = 1200
select * from clinic.healthRecord

use clinic
exec createDiagnosis
@patientID = 5,
@employeeID = 2,
@diagnosisResult = 'thou shalt not eat sugar',
@actionStatus = 'prescript'
select * from clinic.diagnosis

use clinic
exec createPrescription
@patientID = 5,
@employeeID = 2,
@medicineName = 'med',
@medicineQTY = 12,
@medicationDosage = '2 tablet per day for 6 day'
select * from clinic.prescription

use clinic
exec createReferral
@diagnosisID = 1,
@hospitalName = 'd hospital',
@medicalSpecialties = 'bones',
@referralTime = '2023/05/04 18:00:00'
select * from clinic.referral

use clinic
exec createPayment
@appointmentID = 1,
@paymentMethod = 'Insurance',
@chargedAmmount = 2000,
@paymentStatus = 'Paid'
select * from clinic.payment