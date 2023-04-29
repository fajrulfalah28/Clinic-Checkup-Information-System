package mainApp;
import java.sql.SQLException;


public class App {
	//connection controller
	
	public static void main(String[] args) {
		//Establish server-client connection
		try {
			servController.servInit();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		LoginMenu loginMenu = new LoginMenu();
	}
}
