package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

public class TestDataAccessObjectMain {

	public static void main(String[] args) throws SQLException {
		FoodDAO fDAO = new FoodDAOImpl();
		List<Food> fl = fDAO.getFoodInCategory("Thit");
		for(Food f : fl) {
			System.out.println(f);
		}
		System.out.println();
		List<Food> afl = fDAO.getAll();
		for(Food f : afl) {
			System.out.println(f);
		}
		
		
	}

}
