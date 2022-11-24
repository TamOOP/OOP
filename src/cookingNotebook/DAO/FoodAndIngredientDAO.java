package cookingNotebook.DAO;

import java.sql.SQLException;

import cookingNotebook.models.FoodAndIngredient;

public interface FoodAndIngredientDAO extends DAO<FoodAndIngredient>{
	public FoodAndIngredient get(int idFood, int idIngredient) throws SQLException;
	
	public static FoodAndIngredientDAO createInstance() {
		return new FoodAndIngredientDAOImpl();
	}
}