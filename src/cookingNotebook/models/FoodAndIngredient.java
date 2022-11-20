package cookingNotebook.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class FoodAndIngredient {
	private int idFood;
	private int idIngredient;
	private DoubleProperty amount;

	public FoodAndIngredient() {
	}

	public FoodAndIngredient(int idFood, int idIngredient, double amount) {
		this.idFood = idFood;
		this.idIngredient = idIngredient;
		this.amount = new SimpleDoubleProperty(amount);
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
