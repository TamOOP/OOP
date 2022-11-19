package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

public interface MenuDAO extends DAO<Menu> {

	public List<Food> getFoodList(Menu t) throws SQLException;
	public int addFood(Menu t, Food f);
	public int removeFood(Menu t, Food f);
	
}
