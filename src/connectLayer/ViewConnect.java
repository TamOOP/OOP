package connectLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewConnect {
	
	public ResultSet getFoodAndIngredient() {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String getFoodAndIngredient = "SELECT * FROM GetFoodIngredient";
		
		PreparedStatement statement;
		try {
			statement = conndb.prepareCall(getFoodAndIngredient);
			ResultSet rs = statement.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getFoodAndSteps() {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String getFoodAndSteps = "SELECT * FROM GetFoodSteps";
		
		PreparedStatement statement;
		try {
			statement = conndb.prepareCall(getFoodAndSteps);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getMenuAndFood() {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String getMenuAndFood = "SELECT * FROM GetMenuFood";
		
		PreparedStatement statement;
		try {
			statement = conndb.prepareCall(getMenuAndFood);
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
