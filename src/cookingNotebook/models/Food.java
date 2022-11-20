package cookingNotebook.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Food {
	private Integer id = null;
	private final StringProperty name;
	private final IntegerProperty cookTime;
	private String category;
	private String linkImg;
	
	public Food(Integer id, String name, Integer cookTime, String category, String linkImg) {
		this.id = id;
		this.name = new SimpleStringProperty(name);
                this.cookTime = new SimpleIntegerProperty(cookTime);           
		this.category = category;
		this.linkImg = linkImg;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public Integer getCookTime() {
            if(this.cookTime==null){
                return null;
            }else{
                return cookTime.get();
            }
		
	}
	
	public IntegerProperty cookTimeProperty() {
		return cookTime;
	}

	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public String getLinkImg() {
		return linkImg;
	}
	
	public void setlinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
	
	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", cooktime=" + cookTime + ", category=" + category + ", linkImg="
				+ linkImg + "]";
	}
}