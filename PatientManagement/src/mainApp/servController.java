package mainApp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void getDataSchedule() throws SQLException {
        String prep = "select ap.appointmentID , ap.patientID , ap.employeeID , ap.appointmentDate, p.firstName + ' ' + p.lastName  from clinic.appointment as ap inner join person.person as p on (ap.patientID = p.personID)";
        PreparedStatement stat = connect.prepareStatement(prep, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stat.executeQuery();
        connect.commit();
        result.last();
        int rowcount = result.getRow();
        result.beforeFirst();
        data = new Object[rowcount][5];
        while(result.next()) {
	    	for (int i = 0 ; i < rowcount ; i++) {
                for(int j = 0 ; j < 5 ; j++){
	    		    data[i][j] = result.getObject(j+1);
                }
	    	}
    	}
        DoctorMenu.dataSchedule = data;
      }
}
