package cookingNotebook.DAO;

import java.sql.SQLException;

public interface FoodIngrDAO extends DAO<FoodIngr> {

	public FoodIngr get(int fid, int iid) throws SQLException;
	public int insertAmount(int fid, int iid, double amount) throws SQLException;
	public int removeAmount(int fid, int iid) throws SQLException;
	public int updateAmount(int fid, int iid, double amount) throws SQLException;
	
}
