package cookingNotebook.DAO;

public class Ingredient {
	
	private Integer id;
	private String name;
	private int hasQuantity;
	private String unit;
	
	public Ingredient(int id, String name, int hasQuantity, String unit) {
		this.id = id;
		this.name = name;
		this.hasQuantity = hasQuantity;
		this.unit = unit;
	}

    public Ingredient() {
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHasQuantity() {
		return hasQuantity;
	}

	public void setHasQuantity(int hasQuantity) {
		this.hasQuantity = hasQuantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	// Testing purpose only
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", hasQuantity=" + hasQuantity + ", unit=" + unit + "]";
	}
	
}
