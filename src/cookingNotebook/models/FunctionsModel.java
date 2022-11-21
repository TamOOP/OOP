package cookingNotebook.models;

import java.sql.SQLException;
import java.util.List;

import cookingNotebook.DAO.FoodDAO;

public interface FunctionsModel {
	public static List<LogicFood> initFoodInDB() throws SQLException {
		return FoodDAO.createInstance().getAll();
	}
}