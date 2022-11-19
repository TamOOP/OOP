package cookingNotebook.DAO;

public class Step {
	
	private Integer id;
	private int index;
	private String content;
	private String img;
	
	public Step(int id, int index, String content, String img) {
		this.id = id;
		this.index = index;
		this.content = content;
		this.img = img;
	}

    public Step() {
         }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Step [id=" + id + ", index=" + index + ", content=" + content + ", img=" + img + "]";
	}
	
}
