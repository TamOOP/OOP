package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cookingNotebook.models.FoodAndIngredient;
import cookingNotebook.models.Ingredient;
import cookingNotebook.models.LogicFood;
import cookingNotebook.models.Step;

public class FoodDAOImpl implements FoodDAO {
        public static FoodDAOImpl getIns(){
            return new FoodDAOImpl();
        }
	@Override
	public LogicFood get(int idFood) throws SQLException {
		LogicFood food = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb1_Food WHERE FoodID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idFood);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			food = new LogicFood(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getInt("CookingTime"),
					rs.getString("Category"), rs.getString("FoodIMG"));
		}
		rs.close();
		ps.close();
		con.close();
		return food;
	}

	@Override
	public List<LogicFood> getAll() throws SQLException {
		List<LogicFood> foods = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb1_Food";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs != null) {
			foods = new ArrayList<>();
			while (rs.next()) {
				foods.add(new LogicFood(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getInt("CookingTime"),
						rs.getString("Category"), rs.getString("FoodIMG")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return foods;
	}
        public List<LogicFood> getAll(String category) throws SQLException {
		List<LogicFood> foods = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb1_Food WHERE Category=?";
		PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		if (rs != null) {
			foods = new ArrayList<>();
			while (rs.next()) {
				foods.add(new LogicFood(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getInt("CookingTime"),
						rs.getString("Category"), rs.getString("FoodIMG")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return foods;
	}
	@Override
	public int insert(LogicFood food) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO Tb1_Food (FoodName, CookingTime, Category, FoodIMG) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, food.getName());             
                ps.setInt(2, food.getCookTime());
		ps.setString(3, food.getCategory());
		ps.setString(4, food.getLinkImg());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(LogicFood t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		String sql = "UPDATE Tb1_Food SET FoodName = ?, CookingTime = ?, FoodIMG = ?, Category = ? WHERE FoodID = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getName());
		ps.setInt(2, t.getCookTime());
		ps.setString(3, t.getLinkImg());
                ps.setString(4, t.getCategory());
		ps.setInt(5, t.getId());
		int result = ps.executeUpdate();
		ps.close();
		c.close();
		return result;
	}

	@Override
	public int save(LogicFood t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		List<Integer> l = null;
		String sql = "SELECT FoodID FROM Tb1_Food";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			l = new ArrayList<Integer>();
			while(rs.next()) {
				l.add(rs.getInt(1));
			}
		}
		rs.close();
		ps.close();
		c.close();
		if(l.contains(t.getId())) update(t);
		else insert(t);
		return 1;
	}

	@Override
	public int delete(LogicFood food) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql1 = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ?";
		String sql2 = "DELETE FROM Tb3_Step WHERE FoodID = ?";
		String sql3 = "DELETE FROM R2_Menu_Food WHERE Fid = ?";
		String sql = "DELETE FROM Tb1_Food WHERE FoodID = ?";
		int result;
		PreparedStatement ps = con.prepareStatement(sql1);
		ps.setInt(1, food.getId());
		result = ps.executeUpdate();
		ps.close();
		ps = con.prepareStatement(sql2);
		ps.setInt(1, food.getId());
		result = ps.executeUpdate();
		ps.close();
		ps = con.prepareStatement(sql3);
		ps.setInt(1, food.getId());
		result = ps.executeUpdate();
		ps.close();
		ps = con.prepareStatement(sql);
		ps.setInt(1, food.getId());
		result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public List<LogicFood> getFoodInCategory(String categoryName) throws SQLException {
		List<LogicFood> foods = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb1_Food WHERE Category = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, categoryName);
		ResultSet rs = ps.executeQuery();
		if (rs != null) {
			foods = new ArrayList<>();
			while (rs.next()) {
				foods.add(new LogicFood(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getInt("CookingTime"),
						rs.getString("Category"), rs.getString("FoodIMG")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return foods;
	}

	@Override
	public LogicFood getFood(String nameFood) throws SQLException {
		LogicFood food = null;
            try (Connection con = FunctionsDatabase.getConnection()) {
                String sql = "SELECT * FROM Tb1_Food WHERE FoodName = ?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, nameFood);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            food = new LogicFood(rs.getInt("FoodID"), rs.getString("FoodName"), rs.getInt("CookingTime"),
                                    rs.getString("Category"), rs.getString("FoodIMG"));
                        }
                    }
                    }
            }
		return food;
	}

	@Override
	public int saveFood(LogicFood food, List<Ingredient> ingredients, List<Step> steps, List<FoodAndIngredient> fais) {
		try {
			FoodDAO fDAO = FoodDAO.createInstance();
			IngredientDAO iDAO = IngredientDAO.createInstance();
			StepDAO sDAO = StepDAO.createInstance();
			FoodAndIngredientDAO faiDAO = FoodAndIngredientDAO.createInstance();

			fDAO.save(food);
			for (Ingredient ingredient : ingredients) {
				iDAO.save(ingredient);
			}
			for (FoodAndIngredient fai : fais) {
				faiDAO.save(fai);
			}
			for (Step step : steps) {
				sDAO.save(step);
			}
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Ingredient> getIngredientList(LogicFood food) throws SQLException {
		IngredientDAO iDAO = IngredientDAO.createInstance();
		return iDAO.getIngredientList(food.getId());
	}

	@Override
	public List<Step> getStepList(LogicFood food) throws SQLException {
		StepDAO sDAO = StepDAO.createInstance();
		return sDAO.getSteps(food.getId());
	}
}