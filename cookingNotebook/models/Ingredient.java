package cookingNotebook.models;

public class Ingredient {
	private String name;
	private String unit;

	public Ingredient() {
	}

	public Ingredient(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}