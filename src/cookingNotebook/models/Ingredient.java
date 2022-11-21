package cookingNotebook.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ingredient {
	private Integer id = null;
	private final StringProperty name;
	private int hasQuantity;
	

	public Ingredient(Integer id, String name, int hasQuantity) {
		this.id = id;
		this.hasQuantity = hasQuantity;
		this.name = new SimpleStringProperty(name);	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public int getHasQuantity() {
		return hasQuantity;
	}

	public void setHasQuantity(int hasQuantity) {
		this.hasQuantity = hasQuantity;
	}
	
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", hasQuantity=" + hasQuantity + "]";
	}
}