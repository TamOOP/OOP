package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

import cookingNotebook.models.*;

public interface FoodDAO extends DAO<LogicFood> {
	public List<LogicFood> getFoodInCategory(String category) throws SQLException;
	public LogicFood getFood(String name) throws SQLException;
	public int saveFood(LogicFood food, List<Ingredient> ingredients, List<Step> steps, List<FoodAndIngredient> fais);

	public List<Ingredient> getIngredientList(LogicFood food) throws SQLException;
	public int addIngredient(LogicFood food, Ingredient ingredient, double amount);
	public int updateIngredient(LogicFood food, Ingredient ingredient, double amount);
	public int removeIngredient(LogicFood food, Ingredient ingredient);
	
	public List<Step> getStepList(LogicFood food) throws SQLException;
	public int addStep(Step step);
	public int updateStep(Step step);
	public int removeStep(Step step);
	public int saveStep(Step step);
	
	public static FoodDAO createInstance() {
		return new FoodDAOImpl();
	}
}
