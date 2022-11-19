package connectLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public Connection link;
	public Connection getDBconnection() {
		
		String url = "jdbc:sqlite:CookNotebookDB.db";
		try {
			link = DriverManager.getConnection(url);
			System.out.println("DB connected");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return link;
	}
}
