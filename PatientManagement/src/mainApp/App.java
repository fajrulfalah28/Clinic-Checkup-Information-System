package mainApp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;


public class App{
	
	public static void main(String[] args) {
		try {
			servController.servInit(); //Establish server-client connection
			try {
				servController.servAppointmentUpdate(2, "2023-05-06 12:00:00", 1 );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LoginMenu loginMenu = new LoginMenu();
		String date = "2023-05-06 12:00:00";
		
	}
}
