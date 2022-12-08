package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FunctionsDatabase {
	private static String url = "jdbc:sqlite:CookNotebookDB.db";
	private static String user = "root";
	private static String password = "pass";
	
	private FunctionsDatabase() {
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}
}
