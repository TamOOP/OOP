package cookingNotebook.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FoodAndIngredient {
	private int idFood;
	private int idIngredient;
	private final DoubleProperty amount;
        private final StringProperty unit;
	
        public String getUnit() {
		return unit.get();
	}
	
	public StringProperty unitProperty() {
		return unit;
	}
	public FoodAndIngredient(int idFood, int idIngredient, double amount, String unit) {
		this.idFood = idFood;
		this.idIngredient = idIngredient;
		this.amount = new SimpleDoubleProperty(amount);
                this.unit = new SimpleStringProperty(unit);
	}

	public int getIdFood() {
		return idFood;
	}

	public void setIdFood(int idFood) {
		this.idFood = idFood;
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public Double getAmount() {
		return amount.get();
	}

	public DoubleProperty amountProperty() {
		return amount;
	}
	
	@Override
	public String toString() {
		return "FoodIngr [idFood=" + idFood + ", idIngredient=" + idIngredient + ", amount=" + amount + "]";
	}
}
