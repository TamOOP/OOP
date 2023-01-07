package cookingNotebook.DAO;

import cookingNotebook.models.Menu_Food;
import java.sql.SQLException;
import java.util.List;

public interface Menu_FoodDAO extends DAO<Menu_Food> {
    public List<String> getAllFoodName(int mid) throws SQLException;
}