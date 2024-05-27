package preExamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSingleton {
	private static Connection con;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/persona";
			String user = "alumno";
			String password = "alumno";

			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}

}