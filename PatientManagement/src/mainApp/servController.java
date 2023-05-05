package mainApp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
        String prep = "select firstName + ' '+ lastName as 'Doctor Name' from person.person where roleID = 2";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        doctors=new String[rowcount];
        int i = 0;
        while(result.next()) {
            doctors[i] = result.getString(i+1);
        i++;
        }
        EditAppointmentFrame.doctors = doctors;
    }
}
