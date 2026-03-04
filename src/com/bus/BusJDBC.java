package bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BusJDBC {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/BusBookingSystem","root","Vijay@777");
		return con;
		
	}

}