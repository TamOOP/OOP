package cookingNotebook.models;

import java.util.List;

public class Food {
	private int id;
	private String name;
	private int time;
	private String type;
	private String linkImg;
	private List<Step> steps;
	private List<Ingredient> ingredients;

	public Food() {
	}

    public Food(int id, String name, int time, String type, String linkImg) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.type = type;
        this.linkImg = linkImg;
    }

	public Food(String name, int time, String type, String linkImg, List<Step> steps, List<Ingredient> ingredients) {
		this.name = name;
		this.time = time;
		this.type = type;
		this.linkImg = linkImg;
		this.steps = steps;
		this.ingredients = ingredients;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}