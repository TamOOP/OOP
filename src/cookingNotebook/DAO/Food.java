package cookingNotebook.DAO;

public class Food {

	private Integer id;
	private String name;
	private double cooktime;
	private String category;
	private String img;

    public Food() {
    }
        
	
	public Food(int id, String name, double cooktime, String category, String img) {
		this.id = id;
		this.name = name;
		this.cooktime = cooktime;
		this.category = category;
		this.img = img;
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

	public double getCooktime() {
		return cooktime;
	}

	public void setCooktime(double cooktime) {
		this.cooktime = cooktime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	// Testing purpose only
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", cooktime=" + cooktime + ", category=" + category + ", img="
				+ img + "]";
	}
	
}
