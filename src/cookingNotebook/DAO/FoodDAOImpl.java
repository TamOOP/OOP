package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAOImpl implements FoodDAO {
        
// DAO's methods
	@Override
	public Food get(int id) throws SQLException {
		Connection con = Database.getConnection();
		Food f = null;
		String sql = "SELECT * FROM Tb1_Food WHERE FoodID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int fid = rs.getInt("FoodID");
			String name = rs.getString("FoodName");
			int time = rs.getInt("CookingTime");
			String type = rs.getString("Category");
			String img = rs.getString("FoodIMG");
			f = new Food(fid, name, time,  type, img);
		}
		rs.close();
		ps.close();
		con.close();
		return f;
	}

	@Override
	public List<Food> getAll() throws SQLException {
		Connection con = Database.getConnection();
		List<Food> fl = null;
		String sql = "SELECT * FROM Tb1_Food";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			fl = new ArrayList<Food>();
			while(rs.next()) {
				int id = rs.getInt("FoodID");
				String name = rs.getString("FoodName");
				int ct = rs.getInt("CookingTime");
				String type = rs.getString("Category");
				String img = rs.getString("FoodIMG");
				Food f = new Food(id, name, ct, type, img);
				fl.add(f);
			}
		}
		rs.close();
		ps.close();
		con.close();
		return fl;
	}

	@Override
	public int save(Food t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
        @Override
	public int insert(Food t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "INSERT INTO Tb1_Food (FoodName, CookingTime, Category, FoodIMG) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, t.getName());
		ps.setDouble(2, t.getCooktime());
		ps.setString(3, t.getCategory());
		ps.setString(4, t.getImg());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(Food t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "UPDATE Tb1_Food SET FoodName = ?, CookingTime = ?, FoodIMG = ? WHERE FoodID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, t.getName());
		ps.setDouble(2, t.getCooktime());
		ps.setString(3, t.getImg());
		ps.setInt(4, t.getId());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int delete(Food t) throws SQLException {
		Connection con = Database.getConnection();
		String sql1 = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ?";
		String sql2 = "DELETE FROM Tb3_Step WHERE FoodID = ?";
		String sql3 = "DELETE FROM R2_Menu_Food WHERE FoodID = ?";
		String sql = "DELETE FROM Tb1_Food WHERE FoodID = ?";
		int result;
		PreparedStatement ps = con.prepareStatement(sql1);
		ps.setInt(1, t.getId());
		result = ps.executeUpdate();
		ps.close();
		ps = con.prepareStatement(sql2);
		ps.setInt(1, t.getId());
		result = ps.executeUpdate();
		ps.close();
		ps = con.prepareStatement(sql3);
		ps.setInt(1, t.getId());
		result = ps.executeUpdate();
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
	public List<Ingredient> getIngredientList(Food t) throws SQLException{
		IngredientDAO ingrDAO = new IngredientDAOImpl();
		return ingrDAO.getIngredientList(t.getId());
	}
	
	@Override
	public List<Step> getStepList(Food t) throws SQLException{
		StepDAO stepDAO = new StepDAOImpl();
		return stepDAO.getSteps(t.getId());
	}

	@Override
	public List<Food> getFoodInCategory(String category) throws SQLException {
		Connection con = Database.getConnection();
		List<Food> fl = null;
		String sql = "SELECT * FROM Tb1_Food WHERE Category = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			fl = new ArrayList<Food>();
			while(rs.next()) {
				int fid = rs.getInt("FoodID");
				String name = rs.getString("FoodName");
				int time = rs.getInt("CookingTime");
				String type = rs.getString("Category");
				String img = rs.getString("FoodIMG");
				Food f = new Food(fid, name, time,  type, img);
				fl.add(f);
			}
		}
		rs.close();
		ps.close();
		con.close();
		return fl;
	}

	@Override
	public int addIngredient(Food f, Ingredient i, double amount) {
		IngredientDAO ingrDAO = new IngredientDAOImpl();
		FoodIngrDAO fiDAO = new FoodIngrDAOImpl();
		try {
			ingrDAO.insert(i);
			fiDAO.insertAmount(f.getId(), i.getId(), amount);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeIngredient(Food f, Ingredient i) {
		FoodIngrDAO fiDAO = new FoodIngrDAOImpl();
		try {
			fiDAO.removeAmount(f.getId(), i.getId());
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int addStep(Step s) {
		StepDAO stepDAO = new StepDAOImpl();
		try {
			stepDAO.insert(s);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeStep(Step s) {
		StepDAO stepDAO = new StepDAOImpl();
		try {
			stepDAO.delete(s);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateStep(Step s) {
		StepDAO stepDAO = new StepDAOImpl();
		try {
			stepDAO.update(s);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateIngredient(Food f, Ingredient i, double amount) {
		IngredientDAO ingrDAO = new IngredientDAOImpl();
		FoodIngrDAO fiDAO = new FoodIngrDAOImpl();
		try {
			ingrDAO.update(i);
			fiDAO.updateAmount(f.getId(), i.getId(), amount);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Food getFood(String name) throws SQLException {
		Connection con = Database.getConnection();
		Food f = null;
		String sql = "SELECT * FROM Tb1_Food WHERE FoodName = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int fid = rs.getInt("FoodID");
			String fname = rs.getString("FoodName");
			int time = rs.getInt("CookingTime");
			String type = rs.getString("Category");
			String img = rs.getString("FoodIMG");
			f = new Food(fid, fname, time,  type, img);
		}
		rs.close();
		ps.close();
		con.close();
		return f;
	}
}
