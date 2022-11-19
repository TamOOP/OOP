package cookingNotebook.DAO;

public class Menu {
	
	private Integer id;
	private String name;
	
	public Menu(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Testing purpose only
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + "]";
	}
	
}
