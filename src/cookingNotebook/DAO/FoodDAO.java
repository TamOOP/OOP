package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

import cookingNotebook.models.*;

public interface FoodDAO extends DAO<LogicFood> {
	public List<LogicFood> getFoodInCategory(String category) throws SQLException;
	public LogicFood getFood(String name) throws SQLException;
	public int saveFood(LogicFood food, List<Ingredient> ingredients, List<Step> steps, List<FoodAndIngredient> fais);

	public List<Ingredient> getIngredientList(LogicFood food) throws SQLException;
	
	public List<Step> getStepList(LogicFood food) throws SQLException;
	
	
	public static FoodDAO createInstance() {
		return new FoodDAOImpl();
	}
}
