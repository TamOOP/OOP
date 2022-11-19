package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IngredientDAO extends DAO<Ingredient> {

	public List<Ingredient> getIngredientList(int fid) throws SQLException;
	
}
