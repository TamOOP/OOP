package connectLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TableConnect {
    @FXML TextField ten_mon;
// Tb1_Food
	
	// ADD FOOD
	public void addFood(String name, int cooktime, String category) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String addFood = "INSERT INTO Tb1_Food VALUES (?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(addFood);
			statement.setString(2, name);
			statement.setInt(3, cooktime);
			statement.setString(4, category);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addFood1(ActionEvent e){
            addFood(ten_mon.getText(),0,"0");
        }
	// UPDATE FOOD (ONLY FOOD NAME AND COOKING TIME WILL BE UPDATED)
	public void updateFood(Integer foodid, String name, int time) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String updateFood = "UPDATE Tb1_Food SET FoodName = ?, CookingTime = ? WHERE FoodID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(updateFood);
			statement.setString(1, name);
			statement.setInt(2, time);
			statement.setInt(3, foodid);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DELETE FOOD USING ID
	public void deleteFood(Integer id) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String deleteFood = "DELETE FROM Tb1_Food WHERE FoodID = ?";
		String deleteIngredient = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ?";
		String deleteStepsOfFood = "DELETE FROM Tb3_Steps WHERE FoodID = ?";
		String deleteFoodFromMenu = "DELETE FROM R2_Menu_Food WHERE FoodID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(deleteIngredient);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			statement = conndb.prepareStatement(deleteStepsOfFood);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			statement = conndb.prepareStatement(deleteFoodFromMenu);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			statement = conndb.prepareStatement(deleteFood);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Tb2_Ingredient
	
	// ADD INGREDIENT
	public void addIngredient(String name, int haveAmount, String unit) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String addIngredient = "INSERT INTO Tb2_Ingredient VALUES (?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(addIngredient);
			statement.setString(2, name);
			statement.setInt(3, haveAmount);;
			statement.setString(4, unit);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	// UPDATE INGREDIENT (ONLY INGREDIENT NAME, HAS_AMOUNT AND UNIT WILL BE UPDATED)
	public void updateIngredient(Integer foodid, Integer id, String name, int hasAmount, String unit) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String updateIngredient = "UPDATE Tb2_Ingredient SET Ingredient = ?, HasAmount = ?, Unit = ? WHERE FoodID = ? AND IngredientID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(updateIngredient);
			statement.setString(1, name);
			statement.setInt(2, hasAmount);
			statement.setString(3, unit);
			statement.setInt(4, foodid);
			statement.setInt(5, id);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DELETE INGREDIENT USING ID
	public void deleteIngredient(Integer id) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String deleteIngredient = "DELETE FROM Tb2_Ingredient WHERE IngredientID = ?";
		String deleteIngredientOfFood = "DELETE FROM R1_Food_Ingredient WHERE IngredientID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(deleteIngredientOfFood);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			statement = conndb.prepareStatement(deleteIngredient);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Tb3_Steps
		
	// ADD STEP
	public void addStep(Integer foodid, int index, String content, String img) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String addStep = "INSERT INTO Tb3_Steps VALUES (?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(addStep);
			statement.setInt(1, foodid);
			statement.setInt(2, index);
			statement.setString(3, content);
			statement.setString(4, img);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// UPDATE STEP (ONLY STEP CONTENT AND IMAGE WILL BE UPDATED)
	public void updateStep(Integer foodid, int index, String content, String img) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String updateStep = "UPDATE Tb3_Steps SET StepContent = ?, StepImage = ? WHERE FoodID = ? AND StepIndex = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(updateStep);
			statement.setString(1, content);
			statement.setString(2, img);
			statement.setInt(3, foodid);
			statement.setInt(4, index);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DELETE STEP USING FOODID AND STEPINDEX
	public void deleteStep(int foodid, int index) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String deleteStep = "DELETE FROM Tb3_Step WHERE FoodID = ? AND StepIndex = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(deleteStep);
			statement.setInt(1, foodid);
			statement.setInt(2, index);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
// Tb4_Menu
		
	// ADD MENU
	public void addMenu(Integer menuid, String name) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String addMenu = "INSERT INTO Tb4_Menu VALUES (?, ?)";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(addMenu);
			statement.setInt(1, menuid);
			statement.setString(2, name);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// UPDATE MENU (CHANGE MENU NAME TO A NEW ONE)
		public void updateFood(Integer menuid, String name) {
			Connect conn = new Connect();
			Connection conndb = conn.getDBconnection();
			
			String updateMenu = "UPDATE Tb4_Menu SET MenuName = ? WHERE MenuID = ?";
			
			try {
				
				PreparedStatement statement = conndb.prepareStatement(updateMenu);
				statement.setString(1, name);
				statement.setInt(2, menuid);
				statement.executeUpdate();
				statement.close();
				
				conndb.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	// DELETE MENU
	public void deleteMenu(Integer menuid) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String deleteMenu = "DELETE FROM Tb4_Menu WHERE MenuID = ?";
		String deleteMenuFood = "DELETE FROM R2_Menu_Food WHERE MenuID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(deleteMenuFood);
			statement.setInt(1, menuid);
			statement.executeUpdate();
			statement.close();
			
			statement = conndb.prepareStatement(deleteMenu);
			statement.setInt(1, menuid);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// R1_Food_Ingredient
	
	// ADD INGREDIENT OF FOOD
	public void addIngredientOfFood(Integer foodid, Integer ingredientid, double amount) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String addIngredientOfFood = "INSERT INTO R1_Food_Ingredient VALUES (?, ?, ?)";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(addIngredientOfFood);
			statement.setInt(1, foodid);
			statement.setInt(2, ingredientid);
			statement.setDouble(3, amount);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// UPDATE INGREDIENT OF FOOD
		public void updateAmountOfIngredient(Integer foodid, Integer id, Double amount) {
			Connect conn = new Connect();
			Connection conndb = conn.getDBconnection();
			
			String updateAmountOfIngredient = "UPDATE R1_Food_Ingredient SET Amount = ? WHERE FoodID = ? AND IngredientID = ?";
			
			try {
				
				PreparedStatement statement = conndb.prepareStatement(updateAmountOfIngredient);
				statement.setDouble(1, amount);
				statement.setInt(2, foodid);
				statement.setInt(3, id);
				statement.executeUpdate();
				statement.close();
				
				conndb.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	// DELETE INGREDIENT OF FOOD
	public void deleteIngredientOfFood(Integer foodid, Integer id) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String deleteIngredientOfFood = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ? AND IngredientID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(deleteIngredientOfFood);
			statement.setInt(1, foodid);
			statement.setInt(2, id);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// R2_Menu_Food
	
	// ADD FOOD TO MENU
	public void addFoodToMenu(Integer menuid, Integer foodid) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String addFoodToMenu = "INSERT INTO R2_Menu_Food(FoodID) VALUES (?) WHERE MenuID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(addFoodToMenu);
			statement.setInt(1, foodid);
			statement.setInt(2, menuid);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// UPDATE FOOD OF MENU
		public void updateFoodOfMenu(Integer menuid, Integer foodid) {
			Connect conn = new Connect();
			Connection conndb = conn.getDBconnection();
			
			String updateFoodOfMenu = "UPDATE R2_Menu_Food SET FoodID = ? WHERE MenuID = ?";
			
			try {
				
				PreparedStatement statement = conndb.prepareStatement(updateFoodOfMenu);
				statement.setInt(1, foodid);
				statement.setInt(2, menuid);
				statement.executeUpdate();
				statement.close();
				
				conndb.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	// DELETE FOOD FROM MENU
	public void deleteFoodFromMenu(Integer menuid, Integer foodid) {
		Connect conn = new Connect();
		Connection conndb = conn.getDBconnection();
		
		String deleteFoodFromMenu = "DELETE FROM R2_Menu_Food WHERE MenuID = ? AND FoodID = ?";
		
		try {
			
			PreparedStatement statement = conndb.prepareStatement(deleteFoodFromMenu);
			statement.setInt(1, menuid);
			statement.setInt(2, foodid);
			statement.executeUpdate();
			statement.close();
			
			conndb.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}
