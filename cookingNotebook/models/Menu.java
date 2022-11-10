package cookingNotebook.models;

import java.util.List;

public class Menu {
	private String name;
	private List<Food> foods;
	private List<Ingredient> ingerdients;

	public Menu() {
	}

	public Menu(String name, List<Food> foods, List<Ingredient> ingerdients) {
		this.name = name;
		this.foods = foods;
		this.ingerdients = ingerdients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public List<Ingredient> getIngerdients() {
		return ingerdients;
	}

	public void setIngerdients(List<Ingredient> ingerdients) {
		this.ingerdients = ingerdients;
	}
	
	//create menu auto
	//calculate ingredient
}