package cookingNotebook.models;

import cookingNotebook.controllers.CategoryController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Step {
	private Integer idFood = null;
	private Integer idStep= null;
	private StringProperty content;
	private String linkImg;

	public Step(Integer idFood, Integer idStep, String content, String linkImg) {
		this.setIdFood(idFood);
		this.setIdStep(idStep);
		this.content = new SimpleStringProperty(content);
		this.linkImg = linkImg;
	}
        
	public Integer getIdFood() {
                return idFood;
	}

	public void setIdFood(Integer idFood) {
		this.idFood = idFood;
	}
	public void setContent(String content){
           this.content = new SimpleStringProperty(content);
        }
	public Integer getIdStep() {
		return idStep;
	}

	public void setIdStep(Integer idStep) {
		this.idStep = idStep;
	}

	public String getContent() {
		return content.get();
	}

	public StringProperty contentProperty() {
		return content;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
	
	@Override
	public String toString() {
		return "Step [idStep=" + idStep + ", content=" + content + ", linkImg=" + linkImg + "]";
	}
        public Image convertImg(){
            Image image = null;
            FileInputStream inputstream = null;
            if(this.linkImg != null){
                try {
                    inputstream = new FileInputStream(this.linkImg);
                } catch (FileNotFoundException ex) {
                    java.util.logging.Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }else{
                try {
                    inputstream = new FileInputStream("./src/resource/photo.png");
                } catch (FileNotFoundException ex) {
                    java.util.logging.Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            image = new Image(inputstream);
            return image;
        }
}