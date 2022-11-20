package cookingNotebook.DAO;

import java.sql.SQLException;

import cookingNotebook.models.FoodAndIngredient;

public interface FoodAndIngredientDAO extends DAO<FoodAndIngredient>{
	public FoodAndIngredient get(int idFood, int idIngredient) throws SQLException;
	public int insertAmount(int idFood, int idIngredient, double amount) throws SQLException;
	public int removeAmount(int idFood, int idIgredient) throws SQLException;
	public int updateAmount(int idFood, int idIgredien, double amount) throws SQLException;

	public static FoodAndIngredientDAO createInstance() {
		return new FoodAndIngredientDAOImpl();
	}
}