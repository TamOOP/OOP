package cookingNotebook.models;

public class Step {
	private String content;
	private String linkImg;

	public Step() {
	}

	public Step(String content, String linkImg) {
		this.content = content;
		this.linkImg = linkImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
}