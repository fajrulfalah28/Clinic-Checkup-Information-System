package mainApp;
import java.sql.Connection;
import java.sql.SQLException;


public class App{
	
	public static void main(String[] args) {
		try {
			servController.servInit(); //Establish server-client connection
			servController.getLoginCred("admin", "admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LoginMenu loginMenu = new LoginMenu();
	}
}
