package mainApp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class servController {
    //Connection Controller
    final static String servUser ="toka";
	final static String servPassword = "toka123";
	final static String servDB = "clinic";
	final static String url = "jdbc:sqlserver://localhost;DatabaseName=" + servDB + ";user="+ servUser +";password="+ servPassword+";encrypt=true;trustServerCertificate=true";

    //Server-Client Connection
    public static void servInit() throws SQLException{
        Driver serverDriver = new com.microsoft.sqlserver.jdbc.SQLServerDriver(); 
        DriverManager.registerDriver(serverDriver);
        Connection con = DriverManager.getConnection(url); 
    }
    
}
