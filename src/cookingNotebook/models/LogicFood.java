package cookingNotebook.models;

import java.sql.SQLException;
import java.util.List;

import cookingNotebook.DAO.FoodDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LogicFood extends Food {
	private FoodDAO fDAO = FoodDAO.createInstance();
	final ObservableList<Step> steps = FXCollections.observableArrayList();
	final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
	final ObservableList<FoodAndIngredient> fais = FXCollections.observableArrayList();
	
	public LogicFood(Integer id, String name, Integer cookTime, String category, String linkImg) {
		super(id, name, cookTime, category, linkImg);
	}

	public ObservableList<Step> getSteps() {
		return steps;
	}
        
	public ObservableList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public ObservableList<FoodAndIngredient> getFais() {
		return fais;
	}
	
	public List<Ingredient> getIngredientsDB() throws SQLException  {
		return fDAO.getIngredientList(this);
	}
        public List<Step> getStepsDB() throws SQLException  {
		return fDAO.getStepList(this);
	}
}
