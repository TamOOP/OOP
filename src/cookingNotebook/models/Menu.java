package cookingNotebook.models;

import java.util.List;

public class Menu {
	private int id;
	private String name;
	private List<Food> foods;
	private List<Ingredient> ingredients;

	public Menu() {
	}

	public Menu(String name, List<Food> foods, List<Ingredient> ingredients) {
		this.name = name;
		this.foods = foods;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}