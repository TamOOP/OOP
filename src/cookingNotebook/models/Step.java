package cookingNotebook.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Step {
	private Integer idFood = null;
	private Integer idStep= null;
	private final StringProperty content;
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
}