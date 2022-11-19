package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MenuFoodDAOImpl implements MenuFoodDAO {

	// DAO's methods
	@Override
	public MenuFood get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuFood> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(MenuFood t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MenuFood t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "INSERT INTO R2_Menu_Food (FoodID) VALUES (?) WHERE MenuID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, t.getFid());
		ps.setInt(2, t.getMid());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(MenuFood t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(MenuFood t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "DELETE FROM R2_Menu_Food WHERE MenuID = ?, FoodID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, t.getMid());
		ps.setInt(2, t.getFid());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

}
