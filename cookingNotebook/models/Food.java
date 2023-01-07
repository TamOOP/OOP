package cookingNotebook.models;

import cookingNotebook.controllers.CategoryController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

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
                return cookTime.get();
	}
	
	public IntegerProperty cookTimeProperty() {
		return cookTime;
	}
        public void setCookTime(Integer cookTime) {
		this.cookTime.set(cookTime);
	}
	public void setName(String name) {
		this.name.set(name);
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
	public Image convertImg(){
            Image image = null;
            FileInputStream inputstream = null;
            if(this.linkImg != null){
                try {
                    inputstream = new FileInputStream(this.linkImg);
                } catch (FileNotFoundException ex) {
                    try {
                        inputstream = new FileInputStream("./src/resource/photo.png");
                    } catch (FileNotFoundException ex1) {
                        return null;
                    }
                }                
            }else{
                try {
                    inputstream = new FileInputStream("./src/resource/photo.png");
                } catch (FileNotFoundException ex) {return null;}
            }
            image = new Image(inputstream);
            return image;
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