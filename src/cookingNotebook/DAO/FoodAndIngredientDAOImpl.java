package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cookingNotebook.models.FoodAndIngredient;

public class FoodAndIngredientDAOImpl implements FoodAndIngredientDAO {

	@Override
	public FoodAndIngredient get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodAndIngredient> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(FoodAndIngredient fai) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO R1_Food_Ingredient VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, fai.getIdFood());
		ps.setInt(2, fai.getIdIngredient());
		ps.setDouble(3, fai.getAmount());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(FoodAndIngredient fai) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "UPDATE R1_Food_Ingredient SET Amount = ? WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, fai.getAmount());
		ps.setInt(2, fai.getIdFood());
		ps.setInt(3, fai.getIdIngredient());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int save(FoodAndIngredient fai) throws SQLException {
		List<Integer> idFoods = null;
		List<Integer> idIngredients = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT FoodID, IngrID FROM R1_Food_Ingredient";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs != null) {
			idFoods = new ArrayList<Integer>();
			idIngredients = new ArrayList<Integer>();
			while (rs.next()) {
				idFoods.add(rs.getInt("FoodID"));
				idIngredients.add(rs.getInt("IngrID"));
			}
		}
		rs.close();
		ps.close();
		con.close();
		if (idFoods.contains(fai.getIdFood()) && idIngredients.contains(fai.getIdIngredient()))
			update(fai);
		else
			insert(fai);
		return 1;
	}

	@Override
	public int delete(FoodAndIngredient fai) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, fai.getIdFood());
		ps.setInt(2, fai.getIdIngredient());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public FoodAndIngredient get(int idFood, int idIngredient) throws SQLException {
		FoodAndIngredient fai = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM R1_Food_Ingredient WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idFood);
		ps.setInt(2, idIngredient);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Double amount = rs.getDouble("Amount");
			fai = new FoodAndIngredient(rs.getInt("FoodID"), rs.getInt("IngrID"), amount);
		}
		rs.close();
		ps.close();
		con.close();
		return fai;
	}

	@Override
	public int insertAmount(int idFood, int idIngredient, double amount) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO R1_Food_Ingredient VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idFood);
		ps.setInt(2, idIngredient);
		ps.setDouble(3, amount);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int removeAmount(int idFood, int idIgredient) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idFood);
		ps.setInt(2, idIgredient);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int updateAmount(int idFood, int idIgredien, double amount) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "UPDATE R1_Food_Ingredient SET Amount = ? WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, amount);
		ps.setInt(2, idIgredien);
		ps.setInt(3, idFood);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

}
