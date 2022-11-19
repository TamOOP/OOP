package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

public interface FoodDAO extends DAO<Food> {

	public List<Food> getFoodInCategory(String category) throws SQLException;
	public Food getFood(String name) throws SQLException;
	
	public List<Ingredient> getIngredientList(Food t) throws SQLException;
	public int addIngredient(Food f, Ingredient i, double amount);
	public int updateIngredient(Food f, Ingredient i, double amount);
	public int removeIngredient(Food f, Ingredient i);
	
	public List<Step> getStepList(Food t) throws SQLException;
	public int addStep(Step s);
	public int updateStep(Step s);
	public int removeStep(Step s);
	
}
