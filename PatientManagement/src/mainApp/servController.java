package mainApp;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

public class servController {
    //Connection Controller
    private final static String servUser ="toka";
	private final static String servPassword = "toka123";
	private final static String servDB = "clinic";
	private final static String url = "jdbc:sqlserver://localhost;DatabaseName=" + servDB + ";user="+ servUser +";password="+ servPassword+";encrypt=true;trustServerCertificate=true";
    static Connection connect;
    static Object[] resultarray;
    static Object[] userTemp;
    static Object[][] data;
    static String[] columnname;
    static String[] doctors;
    static Object[][] MedicalRecord;
    static String[] MedRecordColumn;
    static Object[][] Credentials;
    static String[] credColumn;
    static int[] servDoctorID;

    //Server-Client Connection
    public static void servInit() throws SQLException{
        Driver serverDriver = new com.microsoft.sqlserver.jdbc.SQLServerDriver(); 
        DriverManager.registerDriver(serverDriver);
        connect = DriverManager.getConnection(url);
        connect.setAutoCommit(false);
        // return connect;
    }
    
    public static Object[] getLoginCred(int username, String password) throws SQLException{
        PreparedStatement stat= connect.prepareStatement("select * from clinic.loginCredential where userID = ? and userPassword = ?");
        stat.setInt(1, username);
        stat.setString(2, password);
        ResultSet result = stat.executeQuery();
        connect.commit();
        resultarray = new Object[2];
        while(result.next()) {      
            resultarray[0] = result.getObject(1);
            resultarray[1] = result.getObject(2);
        }
        return resultarray;
     }
    
    public static void getUser(int username) throws SQLException {
    	PreparedStatement stat = connect.prepareStatement("select * from person.person where personID = ?");
    	stat.setInt(1, username);
    	ResultSet user = stat.executeQuery();
    	connect.commit();
    	userTemp = new Object[16];
    	while(user.next()) {
	    	for (int i = 0 ; i < 16 ; i++) {
	    		userTemp[i] = user.getObject(i+1);
	    	}
    	}
    	DoctorMenu.user = userTemp;
        AdminMenu.user = userTemp;
    }

    public static void getDataSchedule(int username) throws SQLException {
        String prep = "select ap.appointmentID , ap.patientID as 'Patient ID', ap.employeeID as 'Doctor ID' , ap.appointmentDate as 'Appointment Date', p.firstName + ' ' + p.lastName as 'Patient Name'  from clinic.appointment as ap inner join person.person as p on (ap.patientID = p.personID) where ap.employeeID = ?";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stat.setInt(1, username);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        columnname = new String[5];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnname[i-1] = rsmd.getColumnName(i);
        }
        // String[] name = rsmd.getColumnName(1);
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        data = new Object[rowcount][5];
        int i = 0;
        while(result.next()) {
                for(int j = 0 ; j < 5 ; j++){
	    		    data[i][j] = result.getObject(j+1);
                }
	    	i++;
    	}
        DoctorMenu.dataColumnName = columnname;
        DoctorMenu.dataSchedule = data;
    }

    public static void getDiagnosisData() throws SQLException{
        String seek = "select p.firstName + ' ' + p.lastName as 'Patient Name', d.diagnosisResult as 'Diagnosis Result' , d.actionStatus as 'Status', d.dateModified as 'Issue Date' from clinic.diagnosis as d inner join person.person as p on (d.patientID = p.personID)";
        PreparedStatement stat = connect.prepareStatement(seek, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        columnname = new String[4];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnname[i-1] = rsmd.getColumnName(i);
        }
        // String[] name = rsmd.getColumnName(1);
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        data = new Object[rowcount][4];
        int i = 0;
        while(result.next()) {
                for(int j = 0 ; j < 4 ; j++){
	    		    data[i][j] = result.getObject(j+1);
                }
	    	i++;
    	}
        DoctorMenu.diagnosisColumn = columnname;
        AdminMenu.diagnosisColumn = columnname;
        DoctorMenu.DiagnosisTable = data;
        AdminMenu.DiagnosisTable = data;
    }

    public static void getDataScheduleAdmin() throws SQLException {
        String prep = "select ap.appointmentID , p2.firstName + ' ' + p2.lastName as 'Doctor Name', p.firstName + ' ' + p.lastName as 'Patient Name',convert(date,ap.appointmentDate) as 'Appointment Date' , convert(varchar(8),convert(time,ap.appointmentDate)) as 'Appointment Time'  from clinic.appointment as ap inner join person.person as p on (ap.patientID = p.personID) inner join person.person as p2 on (ap.employeeID = p2.personID)";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        columnname = new String[5];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnname[i-1] = rsmd.getColumnName(i);
        }
        // String[] name = rsmd.getColumnName(1);
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        data = new Object[rowcount][5];
        int i = 0;
        while(result.next()) {
                for(int j = 0 ; j < 5 ; j++){
	    		    data[i][j] = result.getObject(j+1);
                }
	    	i++;
    	}
        AdminMenu.dataColumnName = columnname;
        AdminMenu.dataSchedule = data;
    }

    public static void getAllDoctor() throws SQLException{
        String prep = "select personID , firstName + ' '+ lastName as 'Doctor Name' from person.person where roleID = 2";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        doctors = new String[rowcount];
        servDoctorID = new int[rowcount];
        int i = 0;
        while(result.next()) {
            doctors[i] = result.getString(2);
            servDoctorID[i] = result.getInt(1);
        i++;
        }
        AdminMenu.doctors = doctors;
        AdminMenu.servDoctorID = servDoctorID;
        EditAppointmentFrame.servDoctorID = servDoctorID;
        EditAppointmentFrame.doctors = doctors;
    }

    public static void getMedRecord() throws SQLException{
        String prep = "select * from dbo.medicalRecord";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        MedRecordColumn = new String[13];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            MedRecordColumn[i-1] = rsmd.getColumnName(i);
        }
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        MedicalRecord = new Object[rowcount][13];
        int i = 0;
        while(result.next()) {
                for(int j = 0 ; j < 13 ; j++){
	    		    MedicalRecord[i][j] = result.getObject(j+1);
                }
	    	i++;
    	}
        AdminMenu.MedRecordColumn = MedRecordColumn;
        AdminMenu.MedicalRecord = MedicalRecord;
    }

    public static void getCred(int id) throws SQLException{
        String prep = "select userID as 'ID user', userPassword as 'Password' from clinic.loginCredential where not userID = ?";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stat.setInt(1, id);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        credColumn = new String[2];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            credColumn[i-1] = rsmd.getColumnName(i);
        }
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        Credentials = new Object[rowcount][2];
        int i = 0;
        while(result.next()) {
                for(int j = 0 ; j < 2 ; j++){
	    		    Credentials[i][j] = result.getObject(j+1);
                }
	    	i++;
    	}
        AdminMenu.Credentials = Credentials;
        AdminMenu.credColumn = credColumn;
    }

    public static void servAppointmentUpdate(int employee, String date, int id) throws SQLException{
        String prep = "update clinic.appointment set employeeID = ?, appointmentDate = ? where appointmentID = ?";
        PreparedStatement stmt = connect.prepareStatement(prep);
        stmt.setInt(1, employee);
        stmt.setString(2, date);
        stmt.setInt(3, id);
        stmt.execute();
        connect.commit();
    }

    public static void servAddPatient(int idcard, String firstname, String lastname, String dateofbirth, String gender,String city, String street, int zipcode, String email, int phonenumber, String contactname, int contactnum1, String contact2, int contactnum2) throws SQLException{
        String prep = "exec createPerson @IDCardNumber = ?, @roleID = 5, @firstname = ?, @lastname = ?, @dateofBirth = ?, @gender = ? ,@cityAddress = ?,@streetAddress = ?,@zipCode = ?,@email = ?,@phoneNumber = ?,@contact1Name = ?, @contact1phoneNumber = ?, @contact2Name =? , @contact2phoneNumber = ?";
        PreparedStatement stmt = connect.prepareStatement(prep);
        stmt.setInt(1, idcard);
        stmt.setString(2, firstname);
        stmt.setString(3, lastname);
        stmt.setString(4, dateofbirth);
        stmt.setString(5, gender);
        stmt.setString(6, city);
        stmt.setString(7, street);
        stmt.setInt(8, zipcode);
        stmt.setString(9, email);
        stmt.setInt(10, phonenumber);
        stmt.setString(11, contactname);
        stmt.setInt(12, contactnum1);
        stmt.setString(13, contact2);
        stmt.setInt(14, contactnum2);
        stmt.executeUpdate();
        connect.commit();
    }

    public static void listPatient(int user) throws SQLException{
        String prep = "select distinct personID , firstName + ' '+ lastName as 'Doctor Name' from person.person as p right join clinic.appointment as a on (personID = a.patientID) where a.employeeID = ? and  roleID = 5";
        PreparedStatement stmt = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setInt(1, user);
        ResultSet result = stmt.executeQuery();
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        String[] listpatient = new String[rowcount];
        int[] patientID = new int[rowcount];
        int i = 0;
        while(result.next()){
            listpatient[i] = result.getString(2);
            patientID[i] = result.getInt(1);
        }
        AddRecord.listpatient = listpatient;
        AddRecord.patientID = patientID;
    }

    public static void addRecord(int[] id, String[] patient, Object[] patientData, int doctorID)throws SQLException{
        int patientID = id[0];
        for(int i = 0 ; i < patient.length ; i++){
            if(patient[i].equalsIgnoreCase(String.valueOf(patientData[0]))){
               patientID = id[i];
            }
        }
        String prep = "exec createDiagnosis @patientID = ?,@employeeID = ?,@diagnosisResult = ?,@actionStatus = ?";
        PreparedStatement stmt = connect.prepareStatement(prep);
        stmt.setInt(1, patientID);
        stmt.setInt(2, doctorID);
        stmt.setString(3, String.valueOf(patientData[1]));
        stmt.setString(4, String.valueOf(patientData[2]));
        stmt.execute();
        connect.commit();
    }

    public static void deleteCredential(int userid) throws SQLException{
       String prep =  "DELETE FROM clinic.loginCredential WHERE userID = ?";
       PreparedStatement stmt = connect.prepareStatement(prep);
       stmt.setInt(1, userid);
       stmt.execute();
       connect.commit();
    }

    public static void listAllPatient() throws SQLException{
        String prep = "select * from person.person where roleID = 5";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsmd = result.getMetaData();
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        String[] colname= new String[17];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            colname[i-1] = rsmd.getColumnName(i);
        }
        Object[][] patient = new Object[rowcount][17];
        int i = 0;
        while(result.next()) {
            for(int j = 0 ; j < 17 ; j++){
                patient[i][j] = result.getObject(j+1);
            }
        i++;
        }
        AdminMenu.patient = patient;
        AdminMenu.patientcol = colname;
    }
    public static void AddPatientFrame(int id, int nurse, int systolic, int diastolic, int heartRate, int bodytemp, int bodyHeight, int bodyWeight) throws SQLException{
        String prep = "exec createHealthRecord @patientID = ?,@employeeID = ?,@systolic = ?,@diastolic = ?,@heartRate = ?,@bodyTemperature = ?,@bodyheight = ?,@bodyWeight = ?";
        PreparedStatement stmt = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setInt(1, id);
        stmt.setInt(2, nurse);
        stmt.setInt(3, systolic);
        stmt.setInt(4, diastolic);
        stmt.setInt(5, heartRate);
        stmt.setInt(6, bodytemp);
        stmt.setInt(7, bodyHeight);
        stmt.setInt(8, bodyWeight);
        stmt.execute();
        connect.commit();
    }
}
