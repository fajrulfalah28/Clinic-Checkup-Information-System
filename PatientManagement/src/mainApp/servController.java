package mainApp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class servController {
    //Connection Controller
    private final static String servUser ="toka";
	private final static String servPassword = "toka123";
	private final static String servDB = "clinic";
	private final static String url = "jdbc:sqlserver://localhost;DatabaseName=" + servDB + ";user="+ servUser +";password="+ servPassword+";encrypt=true;trustServerCertificate=true";
    static Connection connect;
    static String[] resultarray;

    //Server-Client Connection
    public static void servInit() throws SQLException{
        Driver serverDriver = new com.microsoft.sqlserver.jdbc.SQLServerDriver(); 
        DriverManager.registerDriver(serverDriver);
        connect = DriverManager.getConnection(url);
        connect.setAutoCommit(false);
        // return connect;
    }
    
    public static String[] getLoginCred(String username, String password) throws SQLException{
        PreparedStatement stat= connect.prepareStatement("select * from person.logincred where username = ? and userpassword = ?");
        stat.setString(1, username);
        stat.setString(2, password);
        ResultSet result = stat.executeQuery();
        connect.commit();
        resultarray = new String[2];
        while(result.next()) {      
            resultarray[0] = result.getString(2);
            resultarray[1] = result.getString(3);
        }
        return resultarray;
     }
}
