package cookingNotebook.models;

public class MenuAndIngredient {
	private int idMenu;
	private int idIngredient;
	private int quantity;

	public MenuAndIngredient() {
	}

	public MenuAndIngredient(int idMenu, int idIngredient, int quantity) {
		this.idMenu = idMenu;
		this.idIngredient = idIngredient;
		this.quantity = quantity;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
