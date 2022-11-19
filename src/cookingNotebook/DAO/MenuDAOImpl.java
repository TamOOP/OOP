package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

	// DAO's methods
	@Override
	public Menu get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Menu m = null;
		String sql = "SELECT * FROM Tb4_Menu WHERE MenuID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int mid = rs.getInt("MenuID");
			String name = rs.getString("MenuName");
			m = new Menu(mid, name);
		}
		rs.close();
		ps.close();
		con.close();
		return m;
	}

	@Override
	public List<Menu> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Menu> ml = null;
		String sql = "SELECT * FROM Tb4_Menu";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			ml = new ArrayList<Menu>();
			while(rs.next()) {
				int mid = rs.getInt("MenuID");
				String mname = rs.getString("MenuName");
				Menu m = new Menu(mid, mname);
				ml.add(m);
			}
		}
		rs.close();
		ps.close();
		con.close();
		return ml;
	}

	@Override
	public int save(Menu t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Menu t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "INSERT INTO Tb4_Menu VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, t.getId());
		ps.setString(2, t.getName());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(Menu t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "UPDATE Tb4_Menu SET MenuName = ? WHERE MenuID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, t.getName());
		ps.setInt(2, t.getId());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int delete(Menu t) throws SQLException {
		Connection con = Database.getConnection();
		String sql1 = "DELETE FROM R2_Menu_Food WHERE MenuID = ?";
		String sql = "DELETE FROM Tb4_Menu WHERE MenuID = ?";
		PreparedStatement ps = con.prepareStatement(sql1);
		ps.setInt(1, t.getId());
		int result = ps.executeUpdate();
		ps.close();
		ps = con.prepareStatement(sql);
		ps.setInt(1, t.getId());
		result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	// Added methods
	@Override
	public List<Food> getFoodList(Menu t) throws SQLException{
		Connection con = Database.getConnection();
		List<Food> fl = null;
		String sql = "SELECT FoodID, FoodName, CookingTime, Category, FoodIMG FROM GetMenuFood WHERE MenuID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, t.getId());
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			fl = new ArrayList<Food>();
			while(rs.next()) {
				int fid = rs.getInt("FoodID");
				String fname = rs.getString("FoodName");
				int fct = rs.getInt("CookingTime");
				String ftype = rs.getString("Category");
				String fimg = rs.getString("FoodIMG");
				Food f = new Food(fid, fname, fct, ftype, fimg);
				fl.add(f);
			}
		}
		rs.close();
		ps.close();
		con.close();
		return fl;
	}

	@Override
	public int addFood(Menu t, Food f) {
		MenuFoodDAO mnDAO = new MenuFoodDAOImpl();
		MenuFood mn = new MenuFood(t.getId(), f.getId());
		try {
			mnDAO.insert(mn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeFood(Menu t, Food f) {
		MenuFoodDAO mnDAO = new MenuFoodDAOImpl();
		MenuFood mn = new MenuFood(t.getId(), f.getId());
		try {
			mnDAO.delete(mn);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
