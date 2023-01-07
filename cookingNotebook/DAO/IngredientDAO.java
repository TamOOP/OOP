package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

import cookingNotebook.models.Ingredient;

public interface IngredientDAO extends DAO<Ingredient> {
	public List<Ingredient> getIngredientList(int idFood) throws SQLException;
	public Ingredient getIngredient(String name) throws SQLException;

	public static IngredientDAO createInstance() {
		return new IngredientDAOImpl();
	}
}