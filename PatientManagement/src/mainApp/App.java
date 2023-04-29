package mainApp;

import java.sql.Driver;


public class App {
	public static void main(String[] args) {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		LoginMenu loginMenu = new LoginMenu();
	}
}
